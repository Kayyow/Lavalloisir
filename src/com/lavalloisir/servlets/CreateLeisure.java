package com.lavalloisir.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lavalloisir.beans.business.Leisure;
import com.lavalloisir.beans.dao.DAOFactory;
import com.lavalloisir.beans.dao.LeisureDAO;
import com.lavalloisir.beans.forms.LeisureForm;

/**
 * Servlet implementation class CreateLeisure
 */
public class CreateLeisure extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_LEISURE = "leisure";
	public static final String ATT_FORM = "form";
	public static final String ATT_FILE_LP = "fileLP";
	public static final String VIEW = "/JSP/page.jsp";
	
	private LeisureDAO leisureDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateLeisure() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init() throws ServletException {
		// Récupération d'une instance de notre DAO Utilisateur
		this.leisureDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLeisureDAO();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATT_FILE_LP, "LPCreateLeisure.jsp");
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATT_FILE_LP, "LPCreateLeisure.jsp");
		
		LeisureForm form = new LeisureForm(leisureDAO);
		
		Leisure leisure = form.addLeisure(request);
		
		// Stockage du formulaire et du bean dans l'objet request
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_LEISURE, leisure);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

}
