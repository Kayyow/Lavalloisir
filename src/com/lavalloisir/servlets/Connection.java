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
import com.lavalloisir.forms.ConnectionForm;

/**
 * Servlet implementation class Connection
 */
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "user";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionUser";
	public static final String ATT_FILE_LP = "fileLP";
	public static final String VIEW = "/JSP/page.jsp";
	
	private UserDAO userDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connection() {
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
		request.setAttribute(ATT_FILE_LP, "LPConnection.jsp");
		// Affichage de la page d'inscription
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Préparation de l'objet formulaire de connexion
		ConnectionForm form = new ConnectionForm(userDAO);
		
		// Traitement de la requête et récupération du bean en résultant
		User user = form.connectUser(request);
		
		if (user != null) {
			request.setAttribute(ATT_FILE_LP, "LPHome.jsp");			
		} else {
			request.setAttribute(ATT_FILE_LP, "LPConnection.jsp");			
		}
		
		// Récupération de la session depuis la requête
		HttpSession session = request.getSession();
		
		// Si aucune erreur de validation n'a lieu, alors ajout du bean
		// User à la session, sinon suppression du bean de la session.
		if (form.getErrors().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, user);
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
		}
		
		// Stockage du formulaire et du bean dans l'objet request
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, user);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
}
