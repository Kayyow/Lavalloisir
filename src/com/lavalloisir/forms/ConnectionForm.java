package com.lavalloisir.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOException;
import com.lavalloisir.dao.UserDAO;
import com.lavalloisir.forms.FormValidationException;

public final class ConnectionForm {
	private static final String FIELD_EMAIL = "email";
	private static final String FIELD_PASSORWD = "password";
	private static final String ALGO_ENCRYPT = "SHA-256";
	
	private String result;
	private Map<String, String> errors = new HashMap<String, String>();
    private UserDAO userDAO;
    private ConfigurablePasswordEncryptor pwdEncryptor;
    
    public ConnectionForm(UserDAO userDAO) {
    	this.userDAO = userDAO;
    	pwdEncryptor = new ConfigurablePasswordEncryptor();
    	pwdEncryptor.setAlgorithm(ALGO_ENCRYPT);
    	pwdEncryptor.setPlainDigest(false);
    }
	
	public String getResult() {
		return result;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public User connectUser (HttpServletRequest request) {
		String email = getFieldValue(request, FIELD_EMAIL);
		String password = getFieldValue(request, FIELD_PASSORWD);
		
		User user = null;
		try {
			processEmail(email);
			processPassword(password);
			
			if ( errors.isEmpty() ) {
                user = userDAO.read(email, password, pwdEncryptor);
                if (user != null) {
                	result = "Succès de la connexion.";
                } else {
                	result = "Echec de la connexion.";
                }
            } else {
                result = "Echec de la connexion.";
            }
		} catch ( DAOException e ) {
            result = "Echec de la connexion : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
		
		return user;
	}
	
	private void processEmail(String email) {
        try {
            validEmail( email );
        } catch (FormValidationException e) {
        	setError(FIELD_EMAIL, e.getMessage());
        }
    }
	
	private void processPassword (String password) {
        try {
            validPassword(password);
        } catch (FormValidationException e) {
        	setError(FIELD_PASSORWD, e.getMessage());
        }
	}
	
	/* Validation de l'adresse email */
    private void validEmail(String email) throws FormValidationException {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }
	
	/* Validation des mots de passe */
    private void validPassword (String password) throws FormValidationException {
        if (password != null) {
            if (password.length() < 3) {
                throw new FormValidationException("Le mot de passe doit contenir au moins 3 caractères.");
            }
        } else {
            throw new FormValidationException("Merci de saisir votre mot de passe.");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des errors.
     */
    private void setError (String field, String message) {
        errors.put(field, message);
    }
    
    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getFieldValue (HttpServletRequest request, String fieldName) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }
}
