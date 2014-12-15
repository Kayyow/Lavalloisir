package com.lavalloisir.beans.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lavalloisir.beans.business.Leisure;
import com.lavalloisir.beans.dao.DAOException;
import com.lavalloisir.beans.dao.LeisureDAO;

public final class LeisureForm {
	private static final String FIELD_NAME = "nameLeisr";
	private static final String FIELD_NUMBER = "numberLeisr";
	private static final String FIELD_STREET = "streetLeisr";
	private static final String FIELD_ZIPCODE = "zipCodeLeisr";
	private static final String FIELD_CITY = "cityLeisr";
	private static final String FIELD_PHONE = "phoneLeisr";
	private static final String FIELD_EMAIL = "emailLeisr";
	private static final String FIELD_DESCRIPTION = "descriptionLeisure";
	
    private String result;
    private Map<String, String> errors = new HashMap<String, String>();
    private LeisureDAO leisureDAO;
    
    public LeisureForm( LeisureDAO leisureDAO ) {
        this.leisureDAO = leisureDAO;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }
    
    public Leisure addLeisure(HttpServletRequest request) {
    	
    	String name = getFieldValue(request, FIELD_NAME);
    	int number = Integer.parseInt(getFieldValue(request, FIELD_NUMBER));
    	String street = getFieldValue(request, FIELD_STREET);
    	String zipCode = getFieldValue(request, FIELD_ZIPCODE);
    	String city = getFieldValue(request, FIELD_CITY);
    	String phone = getFieldValue(request, FIELD_PHONE);
    	String email = getFieldValue(request, FIELD_EMAIL);
    	String description = getFieldValue(request, FIELD_DESCRIPTION);
    	
        Leisure leisure = new Leisure();
        try {
        	processName(name, leisure);
        	processAddress(number, street, zipCode, city, leisure);
        	processDescription(description, leisure);
        	processEmail(email, leisure);
        	processPhone(phone, leisure);
        	
            if ( errors.isEmpty() ) {
            	leisureDAO.create( leisure );
                result = "Succès de l'ajout d'un loisir.";
            } else {
                result = "Echec de l'ajout d'un loisir.";
            }
        	
        } catch ( DAOException e ) {
            result = "Echec de l'ajout du loisir : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return leisure;
    }
    
    private void processName (String name, Leisure leisure) {
    	try{ 
    		validName( name );
    	} catch(FormValidationException e) {
    		setError(FIELD_EMAIL, e.getMessage());
        }
    	leisure.setName(name);
    }
    
    private void processAddress(int number, String street, String zipCode, String city, Leisure leisure){
    	try { 
    		validAddress( number, street, zipCode, city );
    	} catch(FormValidationException e) {
    		setError(FIELD_NUMBER, e.getMessage());
    		setError(FIELD_STREET, null);
    		setError(FIELD_ZIPCODE, null);
    		setError(FIELD_CITY, null);
        }
    	leisure.setNumber(number);
    	leisure.setStreet(street);
    	leisure.setZipCode(zipCode);
    	leisure.setCity(city);
    }

    public void processDescription (String description, Leisure leisure){
    	try {
    		validDescription( description );
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
    
    private void validName ( String name) throws FormValidationException { 
        if (name != null && !name.isEmpty()) {
        	if (name.length() < 3) {
                throw new FormValidationException("Le nom doit contenir au moins 3 caractères.");
        	}
        } else {
        	throw new FormValidationException("Merci de saisir un nom.");
        }
    }
    
    private void validAddress (int number, String street, String zipCode, String city) throws FormValidationException {
        if (number < 0) {
        	throw new FormValidationException("Merci de saisir un numero de rue valide. (0 si aucun numero)");
        }
        
        if (street != null && !street.isEmpty()) {
        	if (street.length() < 3) {
                throw new FormValidationException("Le nom de rue doit contenir au moins 3 caractères.");
        	}
        } else {
        	throw new FormValidationException("Merci de saisir un nom de rue.");
        }
        
        if (!zipCode.isEmpty()){
        	if( !zipCode.matches("^([0-9]{5})$")){
        		throw new FormValidationException("Merci de saisir un code postal valide.");
        	}
        	
        } else {
        	throw new FormValidationException("Merci de saisir un code postal.");
        }
        
        if (city != null && !city.isEmpty()) {
        	if (city.length() < 3) {
                throw new FormValidationException("Le nom de ville doit contenir au moins 3 caractères.");
        	}
        } else {
        	throw new FormValidationException("Merci de saisir un nom de ville.");
        }
    }
     
    private void validDescription ( String description) throws FormValidationException { 
        if (description != null && !description.isEmpty()) {
        	if (description.length() < 20) {
                throw new FormValidationException("La description doit contenir au moins 20 caractères.");
        	}
        } else {
        	throw new FormValidationException("Merci de saisir un description.");
        }
    }
    
    private void validEmail(String email) throws FormValidationException {
        if (email != null) {
            if (!email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }
    
    private void validPhone(String phone) throws FormValidationException {
    	if(phone.isEmpty()){
    		throw new FormValidationException( "Merci de saisir un numero de téléphone valide." );
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
