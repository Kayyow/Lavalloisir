package com.lavalloisir.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lavalloisir.beans.business.User;
import com.lavalloisir.beans.dao.DAOFactory;
import com.lavalloisir.beans.dao.UserDAO;
import com.lavalloisir.beans.forms.RegistrationForm;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "user";
	public static final String ATT_FORM = "form";
	public static final String ATT_FILE_LP = "fileLP";
	public static final String VIEW = "/JSP/page.jsp";
	public static final String URL_REDIRECTION = "Home";
	
	private UserDAO userDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() throws ServletException {
    	super();
    }

	public void init() throws ServletException {
		// Récupération d'une instance de notre DAO Utilisateur
		this.userDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATT_FILE_LP, "LPRegistration.jsp");
		// Affichage de la page d'inscription
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATT_FILE_LP, "LPRegistration.jsp");
		
		// Préparation de l'objet formulaire d'inscription
		RegistrationForm form = new RegistrationForm(userDAO);
		
		// Traitement de la requête et récupération du bean en résultant
		User user = form.registerUser(request);
		
		// Stockage du formulaire et du bean dans l'objet request
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, user);
		
		if ( user.getId() != 0 ) {
			response.sendRedirect(URL_REDIRECTION);
		} else {
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
