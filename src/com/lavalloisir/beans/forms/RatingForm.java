package com.lavalloisir.beans.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.lavalloisir.beans.business.Rating;
import com.lavalloisir.beans.business.User;
import com.lavalloisir.beans.dao.DAOException;
import com.lavalloisir.beans.dao.RatingDAO;
import com.lavalloisir.beans.dao.UserDAO;

public class RatingForm {
	private static final String FIELD_LNAME = "registrLName"; 
	private static final String FIELD_FNAME = "registrFName";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();
    private RatingDAO ratingDAO;

    public RatingForm(RatingDAO ratingDAO) {
        this.ratingDAO = ratingDAO;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }

    public Rating addRating(HttpServletRequest request) {
    	String rating = getFieldValue(request, FIELD_LNAME);
    	String idLeisure = getFieldValue(request, FIELD_FNAME);

        User user = new User();
        try {
            processRating(rating, user);

            if (errors.isEmpty()) {
                ratingDAO.create(rating);
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
    private void processRating(String rating, User user) {
        try {
            validRating( rating );
        } catch (FormValidationException e) {
        	setError(FIELD_RATING, e.getMessage());
        }
        user.setEmail(email);
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
