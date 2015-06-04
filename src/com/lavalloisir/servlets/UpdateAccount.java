package com.lavalloisir.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOFactory;
import com.lavalloisir.dao.EvaluationDAO;
import com.lavalloisir.dao.UserDAO;
import com.lavalloisir.forms.DeleteAccountForm;
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
	public static final String ATT_BEST_LEISURES = "bestLeisures";
	public static final String VIEW = "/JSP/page.jsp";
	public static final String URL_REDIRECTION = "/Lavalloisir/Home";
	public static final String URL_DECONNECTION = "/Lavalloisir/Deconnection";
	
	private UserDAO userDAO;
	private EvaluationDAO evaluationDAO;
	private List<String> bestLeisures;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccount() {
        super();
    }
    
    public void init() throws ServletException {
    	this.userDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
    	this.evaluationDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getEvaluationDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user != null) {	
			// Les 5 meilleurs loisirs
			bestLeisures = evaluationDAO.getBestLeisures();			
			request.setAttribute(ATT_BEST_LEISURES, bestLeisures);
		}
		request.setAttribute(ATT_FILE_LP, "/restrained/LPUpdateAccount.jsp");
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("whichForm");
		
		switch (action){ 
			case "update":
				
				// Préparation de l'objet formulaire d'inscription
				UpdateAccountForm formUpdate = new UpdateAccountForm(userDAO);
				
				// Traitement de la requête et récupération du bean en résultant
				// Ou si il y a une/des erreur(s) récupère l'objet user déjà en session
				User user = formUpdate.updateUser(request);
				
				// Récupération de la session depuis la requête
				HttpSession session = request.getSession();
				
				session.setAttribute(ATT_SESSION_USER, user);
				
				// Stockage du formulaire et du bean dans l'objet request
				request.setAttribute(ATT_FORM, formUpdate);
				request.setAttribute(ATT_USER, user);
				request.setAttribute(ATT_FILE_LP, "/restrained/LPUpdateAccount.jsp");
				
				this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
				
			break; 
			case "delete": 
				
				DeleteAccountForm formDelete = new DeleteAccountForm(userDAO);
							
				formDelete.deleteUser(request);
				response.sendRedirect(URL_DECONNECTION);				
			break; 
			default:
		}
		
	}

}
