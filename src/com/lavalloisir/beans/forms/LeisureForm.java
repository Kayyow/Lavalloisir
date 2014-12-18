package com.lavalloisir.beans.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lavalloisir.beans.business.Category;
import com.lavalloisir.beans.business.Leisure;
import com.lavalloisir.beans.dao.DAOException;
import com.lavalloisir.beans.dao.LeisureDAO;

public final class LeisureForm {
	private static final String FIELD_NAME = "nameLeisr";
	private static final String FIELD_ADDRESS = "addressLeisr";
	private static final String FIELD_PHONE = "phoneLeisr";
	private static final String FIELD_EMAIL = "emailLeisr";
	private static final String FIELD_DESCRIPTION = "descriptionLeisr";
	private static final String FIELD_CATEGORY = "categoryLeisr";
	
    private String result;
    private Map<String, String> errors = new HashMap<String, String>();
    private LeisureDAO leisureDAO;
    private List<Category> categories;
    
    public LeisureForm( LeisureDAO leisureDAO, List<Category> categories) {
        this.leisureDAO = leisureDAO;
        this.categories = categories;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }
    
    public Leisure addLeisure(HttpServletRequest request) {
    	String name = getFieldValue(request, FIELD_NAME);
    	String address = getFieldValue(request, FIELD_ADDRESS);
    	String phone = getFieldValue(request, FIELD_PHONE);
    	String email = getFieldValue(request, FIELD_EMAIL);
    	String description = getFieldValue(request, FIELD_DESCRIPTION);
    	int idCategory = Integer.parseInt(getFieldValue(request, FIELD_CATEGORY));
    	Category category = null;
    	for (Category cat : categories) {
    		if (cat.getId() == idCategory) {
    			category = cat;
    			break;
    		}
    	}
    	
    	
    	
        Leisure leisure = new Leisure();
        try {
        	processName(name, leisure);
        	processAddress(address, leisure);
        	processDescription(description, leisure);
        	processEmail(email, leisure);
        	processPhone(phone, leisure);
        	leisure.setCategory(category);
        	
            if (errors.isEmpty()) {
            	leisureDAO.create(leisure);
                result = "Succès de l'ajout d'un loisir.";
            } else {
                result = "Echec de l'ajout d'un loisir.";             
            }
        } catch (DAOException e) {
            result = "Echec de l'ajout du loisir : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return leisure;
    }
    
    private void processName (String name, Leisure leisure) {
    	try{ 
    		validName(name);
    	} catch(FormValidationException e) {
    		setError(FIELD_NAME, e.getMessage());
        }
    	leisure.setName(name);
    }
    
    private void processAddress(String address, Leisure leisure){
    	try { 
    		validAddress(address);
    	} catch(FormValidationException e) {
    		setError(FIELD_ADDRESS, e.getMessage());
        }
    	leisure.setAddress(address);
    }

    public void processDescription (String description, Leisure leisure){
    	try {
    		validDescription(description);
    	} catch(FormValidationException e) {
    		setError(FIELD_DESCRIPTION, e.getMessage());
    	}
    	leisure.setDescription(description);
    }
    
    private void processEmail(String email, Leisure leisure) {
        try {
            validEmail( email );
        } catch (FormValidationException e) {
        	setError(FIELD_EMAIL, e.getMessage());
        }
        leisure.setEmail(email);
    }
    
    private void processPhone(String phone, Leisure leisure){
        try {
            validPhone(phone);
        } catch (FormValidationException e) {
        	setError(FIELD_PHONE, e.getMessage());
        }
        leisure.setPhone(phone);
    }
    
    private void validName (String name) throws FormValidationException { 
        if (name != null && !name.isEmpty()) {
        	if (name.length() < 3) {
                throw new FormValidationException("Le nom doit contenir au moins 3 caractères.");
        	}
        } else {
        	throw new FormValidationException("Merci de saisir un nom.");
        }
    }
    
    private void validAddress (String address) throws FormValidationException {
        if (address != null && !address.isEmpty()) {
        	if (address.length() < 4) {
                throw new FormValidationException("L'adresse doit contenir au moins 4 caractères.");
        	}
        } else {
        	throw new FormValidationException("Merci de saisir une adresse.");
        }
    }
     
    private void validDescription (String description) throws FormValidationException { 
        if (description != null && !description.isEmpty()) {
        	if (description.length() < 10) {
                throw new FormValidationException("La description doit contenir au moins 10 caractères.");
        	}
        } else {
        	throw new FormValidationException("Merci de saisir une description.");
        }
    }
    
    private void validEmail(String email) throws FormValidationException {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new FormValidationException("Merci de saisir une adresse mail valide.");
            }
        } else {
            throw new FormValidationException("Merci de saisir une adresse mail.");
        }
    }
    
    private void validPhone(String phone) throws FormValidationException {
    	if(phone != null && !phone.isEmpty()) {
    		if (phone.length() != 10) {
    			throw new FormValidationException("Votre n° de téléphone doit contenir 10 chiffres");
    		}
    	} else {
    		throw new FormValidationException("Merci de saisir un numero de téléphone valide.");
    	}
    }
    
    private void setError(String field, String message) {
        errors.put(field, message);
    }
    
    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }
}
