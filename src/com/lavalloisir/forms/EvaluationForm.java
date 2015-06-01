package com.lavalloisir.forms;

import javax.servlet.http.HttpServletRequest;

import com.lavalloisir.beans.Evaluation;
import com.lavalloisir.beans.Leisure;
import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOException;
import com.lavalloisir.dao.EvaluationDAO;
import com.lavalloisir.dao.LeisureDAO;

public class EvaluationForm {
	private static final String FIELD_NOTE = "note"; 
	private static final String FIELD_OPINION = "opinion";

    private EvaluationDAO evaluationDAO;
    private Evaluation evaluation;
	
    public EvaluationForm(EvaluationDAO evaluationDAO, LeisureDAO leisureDAO) {
    	this.evaluationDAO = evaluationDAO;
    }
    
	/**
	 * 
	 * @param request
	 * @return
	 */
	public void noteLeisure(HttpServletRequest request, User user, Leisure leisure) {
		int note = Integer.parseInt(getFieldValue(request, FIELD_NOTE));
		String opinion = getFieldValue(request, FIELD_OPINION);
				
		try {
			evaluation = evaluationDAO.read(user, leisure);
			
			if (evaluation == null) {
				evaluation = new Evaluation(note, opinion, user, leisure);
				evaluationDAO.create(evaluation);
			} else {
				evaluationDAO.update(evaluation, note, opinion);
			}			
		} catch (DAOException e) {
            e.printStackTrace();
		}
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
