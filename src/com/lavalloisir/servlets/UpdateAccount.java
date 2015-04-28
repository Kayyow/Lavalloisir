package com.lavalloisir.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOFactory;
import com.lavalloisir.dao.UserDAO;
import com.lavalloisir.forms.UpdateAccountForm;

/**
 * Servlet implementation class UpdateAccount
 */
public class UpdateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "user";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "user";
	public static final String ATT_FILE_LP = "fileLP";
	public static final String VIEW = "/JSP/page.jsp";
	public static final String URL_REDIRECTION = "/Lavalloisir/Home";
	
	private UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccount() {
        super();
    }
    
    public void init() throws ServletException {
    	this.userDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATT_FILE_LP, "/restrained/LPUpdateAccount.jsp");
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Préparation de l'objet formulaire d'inscription
		UpdateAccountForm form = new UpdateAccountForm(userDAO);
		
		// Traitement de la requête et récupération du bean en résultant
		User user = form.updateUser(request);
		
        user = userDAO.read( ( (User)request.getSession().getAttribute("user") ).getId() );
		
		// Stockage du formulaire et du bean dans l'objet request
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, user);
		request.setAttribute(ATT_FILE_LP, "/restrained/LPUpdateAccount.jsp");
		
		// Récupération de la session depuis la requête
		HttpSession session = request.getSession();
		
		// Si aucune erreur de validation n'a lieu, alors ajout du bean
		// User à la session, sinon suppression du bean de la session.
		if (form.getErrors().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, user);
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
		}
		
		if ( user.getId() != 0 ) {
			response.sendRedirect(URL_REDIRECTION);
		} else {
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
