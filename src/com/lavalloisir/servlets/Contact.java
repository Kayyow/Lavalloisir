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

/**
 * Servlet implementation class Contact
 */
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_BEST_LEISURES = "bestLeisures";
	public static final String ATT_FILE_LP = "fileLP";
	public static final String VIEW = "/JSP/page.jsp";
       
	private EvaluationDAO evaluationDAO;
	private List<String> bestLeisures;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Contact() {
        super();
    }
    
    public void init() throws ServletException {
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
		request.setAttribute(ATT_FILE_LP, "LPContact.jsp");
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
