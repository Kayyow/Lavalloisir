package com.lavalloisir.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOException;
import com.lavalloisir.dao.UserDAO;

public final class RegistrationForm {
	private static final String FIELD_NAME = "name"; 
	private static final String FIELD_GIVENNAME = "givenName";
	private static final String FIELD_EMAIL = "email";
	private static final String FIELD_PASSWORD = "password";
	private static final String FIELD_CONFIRMPASSWORD = "confirmPassword";
	private static final String FIELD_PHONE = "phone";
	private static final String FIELD_PICTURE = "picture";
	private static final int BUFFER_SIZE = 10240; // 10 ko

    private static final String ALGO_ENCRYPT = "SHA-256";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();
    private UserDAO userDAO;
    private String path;

    public RegistrationForm( UserDAO userDAO ) {
        this.userDAO = userDAO;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }

    /**
     * 
     * @param request
     * @param path
     * @return
     */
    public User registerUser(HttpServletRequest request, String path) {
    	String name = getFieldValue(request, FIELD_NAME);
    	String givenName = getFieldValue(request, FIELD_GIVENNAME);
    	String email = getFieldValue(request, FIELD_EMAIL);
       	String password = getFieldValue(request, FIELD_PASSWORD);
    	String confirmPassword = getFieldValue(request, FIELD_CONFIRMPASSWORD);
    	String phone = getFieldValue(request, FIELD_PHONE);
    	Part picture = null;
    	this.path = path;
    	
    	try {
    		picture = request.getPart(FIELD_PICTURE);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			setError(FIELD_PICTURE, "L'image ne doit pas peser plus de 320 ko.");
		} catch (IOException e) {
			e.printStackTrace();
			setError(FIELD_PICTURE, "Erreur de configuration du serveur");
		} catch (ServletException e) {
			e.printStackTrace();
			setError(FIELD_PICTURE, "");
		}
    	
        User user = new User();
        try {
            processNames(name, givenName, user);
            processEmail(email, user);
            processPassword(password, confirmPassword, user);
            processPhone(phone, user);
            user.setPicture("\\Lavalloisir\\img\\user\\user_img.png");

            if (errors.isEmpty()) {
            	if (picture.getSize() != 0) {
                	processPicture(picture, user);
                }
                userDAO.create(user);
                result = "Succès de l'inscription.";
            } else {
                result = "Echec de l'inscription.";
            }
        } catch ( DAOException e ) {
            result = "Echec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Appel à la validation de l'adresse email reçue et initialisation de la
     * propriété email du bean
     * @param email
     * @param user
     */
    private void processEmail(String email, User user) {
        try {
            validEmail( email );
        } catch (FormValidationException e) {
        	setError(FIELD_EMAIL, e.getMessage());
        }
        user.setEmail(email);
    }

    /**
     * Appel à la validation des mots de passe reçus, chiffrement du mot de
     * passe et initialisation de la propriété password du bean
     * @param password
     * @param confirmation
     * @param user
     */
    private void processPassword( String password, String confirmation, User user ) {
        try {
            validPasswords( password, confirmation );
        } catch ( FormValidationException e ) {
        	setError(FIELD_PASSWORD, e.getMessage() );
        }

        /*
         * Utilisation de la bibliothèque Jasypt pour chiffrer le mot de passe
         * efficacement.
         * 
         * L'algorithme SHA-256 est ici utilisé, avec par défaut un salage
         * aléatoire et un grand nombre d'itérations de la fonction de hashage.
         * 
         * La String retournée est de longueur 56 et contient le hash en Base64.
         */
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ALGO_ENCRYPT );
        passwordEncryptor.setPlainDigest( false );
        String cryptedPassword = passwordEncryptor.encryptPassword( password );

        user.setPassword(cryptedPassword);
    }

    /**
     * Appel à la validation du nom reçu et initialisation de la
     * propriété nom du bean.
     * @param name
     * @param givenName
     * @param user
     */
    private void processNames( String name, String givenName, User user) {
        try {
        	validName(name);
        } catch ( FormValidationException e ) {
            setError(FIELD_NAME, e.getMessage());
        }
        try {
        	validName(givenName);
        } catch ( FormValidationException e ) {
            setError(FIELD_GIVENNAME, e.getMessage());
        }
        user.setName(name);
        user.setGivenName(givenName);
    }
    
    /**
     * 
     * @param phone
     * @param user
     */
    private void processPhone( String phone, User user) {
    	try {
			validPhone(phone);
		} catch (FormValidationException e) {
			setError(FIELD_PHONE, e.getMessage());
		}
    	user.setPhone(phone);
    }
    
    /**
     * 
     * @param picture
     * @param user
     */
    private void processPicture (Part picture, User user) {
    	String pictureName = null;
    	try {
    		pictureName = getFileName(picture);
    		validPictureName(pictureName);
    		writeFile(picture, pictureName);
        	String localPath = path.substring(path.indexOf("\\img"));
        	user.setPicture("\\Lavalloisir" + localPath + pictureName);
		} catch (Exception e) {
			setError(FIELD_PICTURE, e.getMessage());
		}
    }

    /* Validation de l'adresse email */
    private void validEmail(String email) throws FormValidationException {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            } else if ( userDAO.read(email) != null ) {
                throw new FormValidationException( "Cette adresse email est déjà  utilisée, merci d'en choisir une autre." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }

    /* Validation des mots de passe */
    private void validPasswords(String password, String confirmation) throws FormValidationException {
        if ( password != null && confirmation != null ) {
            if ( !password.equals( confirmation ) ) {
                throw new FormValidationException("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if ( password.length() < 5 ) {
                throw new FormValidationException("Les mots de passe doivent contenir au moins 5 caractères.");
            }
        } else {
            throw new FormValidationException("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    /* Validation du nom / prénom */
    private void validName(String name) throws FormValidationException {        
        if (name != null && !name.isEmpty()) {
        	if (name.length() < 3) {
                throw new FormValidationException("Le nom doit contenir au moins 3 caractères.");
        	}
        } else {
        	throw new FormValidationException("Merci de saisir un nom.");
        }
    }
    
    /* Validation du numéro de téléphone */
    private void validPhone (String phone) throws FormValidationException {
    	if (phone != null && !phone.isEmpty()) {
    		if (!phone.matches("\\d{10}")) {
    			throw new FormValidationException("Le numéro de téléphone doit contenir uniquement 10 chiffres.");
    		} 
    	}
    }
    
    /* Validation de la photo de profil */
    private void validPictureName (String pictureName) throws FormValidationException {
    	if (pictureName != null && !pictureName.isEmpty()) {
    		if (!pictureName.matches("([^\\s]+(\\.(?i)(jpg|png|bmp|gif))$)")) {
    			throw new FormValidationException("Le fichier doit être dans un format d'image.");
    		}
    	}
    }
    
    /* 
     * Méthode utilitaire qui a pour unique but d'analyser l'en-tête "content-disposition",
     * et de vérifier si le paramètre "filename"  y est présent. Si oui, alors le champ traité
     * est de type File et la méthode retourne son nom, sinon il s'agit d'un champ de formulaire 
     * classique et la méthode retourne null. 
     */
    private String getFileName(Part part) {
    	/* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
        	/* Recherche de l'éventuelle présence du paramètre "filename". */
            if ( contentDisposition.trim().startsWith("filename") ) {
                /* Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de fichier. */
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 )
                		.trim().replace( "\"", "" ); // Supprime les guillemets "
            }
        }
        /* Et pour terminer, si rien n'a été trouvé... */
        return null;
    }
    
    /*
     * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
     * sur le disque, dans le répertoire donné et avec le nom donné.
     */
    private void writeFile(Part part, String fileName) throws Exception {
    	//Prépare les flux.
    	BufferedInputStream in = null;
    	BufferedOutputStream out = null;

    	try {
    		// Ouvre les flux
			in = new BufferedInputStream(part.getInputStream(), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(new File(path + fileName)),
					BUFFER_SIZE);
			// Lit le fichier reçu et écrit son contenu dans un fichier sur le disque.
			byte[] buffer = new byte[BUFFER_SIZE];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // Fermeture des flux.
			try {
				if (out != null) out.close();
			} catch (IOException ignore) {
			}
			try {
				if (in != null) in.close();
			} catch (IOException ignore) {
			}
		}
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des errors.
     */
    private void setError(String field, String message) {
        errors.put(field, message);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }
}
