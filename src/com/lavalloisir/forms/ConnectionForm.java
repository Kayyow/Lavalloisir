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
	private static final String FIELD_LOGIN = "login";
	private static final String FIELD_PASSWD = "password";
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
		String login = getFieldValue(request, FIELD_LOGIN);
		String password = getFieldValue(request, FIELD_PASSWD);
		
		User user = null;
		try {
			processLogin(login);
			processPassword(password);
			
			if ( errors.isEmpty() ) {
                user = userDAO.find(login, password, pwdEncryptor);
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
	
	private void processLogin (String login) {
		try {
			validLogin(login);
		} catch (FormValidationException e) {
			setError(FIELD_LOGIN, e.getMessage());
		}
	}
	
	private void processPassword (String password) {
        try {
            validPassword(password);
        } catch (FormValidationException e) {
        	setError(FIELD_PASSWD, e.getMessage());
        }
        
	}
	
	/* Validation du login */
    private void validLogin (String login) throws FormValidationException {
        if (login != null) {
        	if (login.length() < 3) {
        		throw new FormValidationException("Le login doit contenir au moins 3 caractères.");
        	}
        } else {
        	throw new FormValidationException("Merci de saisir votre login.");
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
