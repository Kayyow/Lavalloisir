package com.lavalloisir.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOException;
import com.lavalloisir.dao.UserDAO;

public class UpdateAccountForm {
	private static final String FIELD_NAME = "name";
	private static final String FIELD_GIVEN_NAME = "givenName";
	private static final String FIELD_EMAIL = "email";
	private static final String FIELD_PHONE = "phone";
	
	private String result;
	private Map<String, String> errors = new HashMap<String, String>();
    private UserDAO userDAO;
    
	public UpdateAccountForm(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public Map<String, String> getErrors() {
		return this.errors;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public User updateUser(HttpServletRequest request) {
		String name = getFieldValue(request, FIELD_NAME);
		String givenName = getFieldValue(request, FIELD_GIVEN_NAME);
		String email = getFieldValue(request, FIELD_EMAIL);
		String phone = getFieldValue(request, FIELD_PHONE);
		
		User user = new User();
		try {
			processNames(name, givenName, user);
            processEmail(email, user);
            processPhone(phone, user);

            if (errors.isEmpty()) {
            	User sessionUser = (User)request.getSession().getAttribute("user");
                userDAO.update(sessionUser.getId(), user);
                result = "Succès de l'inscription.";
            } else {
                result = "Echec de l'inscription.";
            }
		} catch (DAOException e) {
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
     * Appel à la validation du nom reçu et initialisation de la
     * propriété nom du bean.
     * @param name
     * @param givenName
     * @param user
     */
    private void processNames( String name, String givenName, User user) {
        try {
        	validName(name);
        	validName(givenName);
        } catch ( FormValidationException e ) {
            setError(FIELD_NAME, e.getMessage());
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
    			throw new FormValidationException("Le numéro de téléphone doit contenir 10 chiffres.");
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
