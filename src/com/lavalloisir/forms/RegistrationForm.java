package com.lavalloisir.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOException;
import com.lavalloisir.dao.UserDAO;

public final class RegistrationForm {
	private static final String FIELD_LNAME = "registrLName"; 
	private static final String FIELD_FNAME = "registrFName";
	private static final String FIELD_EMAIL = "registrMail";
	private static final String FIELD_LOGIN = "registrLogin";
	private static final String FIELD_PASSWD = "registrPwd";
	private static final String FIELD_CONFIRM = "registrConfirm";

    private static final String ALGO_ENCRYPT = "SHA-256";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();
    private UserDAO userDAO;

    public RegistrationForm( UserDAO userDAO ) {
        this.userDAO = userDAO;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }

    public User registerUser(HttpServletRequest request) {
    	String lName = getFieldValue(request, FIELD_LNAME);
    	String fName = getFieldValue(request, FIELD_FNAME);
    	String email = getFieldValue(request, FIELD_EMAIL);
    	String login = getFieldValue(request, FIELD_LOGIN);
    	String passwd = getFieldValue(request, FIELD_PASSWD);
    	String confirm = getFieldValue(request, FIELD_CONFIRM);

        User user = new User();
        try {
            processEmail(email, user);
            processPasswords(passwd, confirm, user);
            processNames(lName, fName, login, user);

            if (errors.isEmpty()) {
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
     * @param confirm
     * @param user
     */
    private void processPasswords( String password, String confirm, User user ) {
        try {
            validPasswords( password, confirm );
        } catch ( FormValidationException e ) {
        	setError(FIELD_PASSWD, e.getMessage() );
        	setError(FIELD_CONFIRM, null );
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
        String motDePasseChiffre = passwordEncryptor.encryptPassword( password );

        user.setPassword(motDePasseChiffre);
    }

    /**
     * Appel à la validation du nom reçu et initialisation de la
     * propriété nom du bean.
     * @param lName
     * @param fName
     * @param login
     * @param user
     */
    private void processNames( String lastName, String firstName, String login, User user) {
        try {
        	validNames(lastName, firstName, login);
        } catch ( FormValidationException e ) {
            setError(FIELD_LNAME, e.getMessage());
            setError(FIELD_FNAME, null);
            setError(FIELD_LOGIN, null);
        }
        user.setName(lastName);
        user.setFirstName(firstName);
        user.setLogin(login);
    }

    /* Validation de l'adresse email */
    private void validEmail(String email) throws FormValidationException {
        if (email != null) {
            if (!email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            } else if ( userDAO.find(email) != null ) {
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
            } else if ( password.length() < 3 ) {
                throw new FormValidationException("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new FormValidationException("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    /* Validation du nom / prénom / login */
    private void validNames(String lastName, String firstName, String login) throws FormValidationException {        
        if (lastName != null && !lastName.isEmpty()) {
        	if (lastName.length() < 3) {
                throw new FormValidationException("Le nom doit contenir au moins 3 caractères.");
        	}
        } else {
        	throw new FormValidationException("Merci de saisir un nom.");
        }
        
        if (firstName != null && !firstName.isEmpty()) {
        	if (firstName.length() < 3) {
            	throw new FormValidationException("Le prénom doit contenir au moins 3 caractères.");
        	}
        } else {
        	throw new FormValidationException("Merci de saisir un prénom.");
        }
        
        if (login != null && !login.isEmpty()) {
	        if (login.length() < 3) {
	            throw new FormValidationException("Le login doit contenir au moins 3 caractères.");
	        }
        } else {
        	throw new FormValidationException("Merci de saisir un login.");
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