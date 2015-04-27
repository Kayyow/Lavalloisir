package com.lavalloisir.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lavalloisir.beans.Leisure;
import com.lavalloisir.beans.Rating;
import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOException;
import com.lavalloisir.dao.RatingDAO;

public class RatingForm {
	private static final String FIELD_LEISURE = "leisrId";
	private static final String FIELD_SCORE = "ratingScore";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();
    private RatingDAO ratingDAO;
    private List<Leisure> leisures;
    private User user;

    public RatingForm(RatingDAO ratingDAO, List<Leisure> leisures, User user) {
        this.ratingDAO = ratingDAO;
        this.leisures = leisures;
        this.user = user;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }

    public void addRating(HttpServletRequest request) {
    	int idLeisure = Integer.parseInt(getFieldValue(request, FIELD_LEISURE));
    	Leisure leisure = null;
    	for (Leisure leis : leisures) {
    		if (leis.getId() == idLeisure) {
    			leisure = leis;
    			break;
    		}
    	}
    	
    	int score = Integer.parseInt(getFieldValue(request, FIELD_SCORE));
    	
    	Rating rating = new Rating();
        try {        	
            processScore(score, rating);
            rating.setLeisure(leisure);
            rating.setUser(user);
          
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
    }

    /**
     * Appel à la validation de l'adresse email reçue et initialisation de la
     * propriété email du bean
     * @param email
     * @param user
     */
    private void processScore(int score, Rating rating) {
        try {
            validScore(score);
        } catch (FormValidationException e) {
        	setError(FIELD_SCORE, e.getMessage());
        }
        rating.setScore(score);
    }

    /* Validation de l'adresse email */
    private void validScore(int score) throws FormValidationException {
        if (score < 0 || score > 10) {
        	throw new FormValidationException( "Merci de saisir un note entre 0 et 10." );
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
