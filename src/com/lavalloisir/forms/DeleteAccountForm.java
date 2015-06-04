package com.lavalloisir.forms;

import javax.servlet.http.HttpServletRequest;

import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOException;
import com.lavalloisir.dao.UserDAO;

public class DeleteAccountForm {
	
	private String result;
    private UserDAO userDAO;
    
	public DeleteAccountForm(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void deleteUser(HttpServletRequest request) {

		User sessionUser = (User)request.getSession().getAttribute("user");
		
		try {
                userDAO.delete(sessionUser.getId());

		} catch (DAOException e) {
			result = "Echec de suppression du compte.";
			e.printStackTrace();
		}
		
	}


}
