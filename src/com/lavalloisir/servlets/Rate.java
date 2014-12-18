package com.lavalloisir.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lavalloisir.beans.business.Leisure;
import com.lavalloisir.beans.business.Rating;
import com.lavalloisir.beans.dao.DAOFactory;
import com.lavalloisir.beans.dao.LeisureDAO;
import com.lavalloisir.beans.dao.RatingDAO;

/**
 * Servlet implementation class Rating
 */
public class Rate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_LEISURE = "leisure";
	public static final String ATT_FORM = "form";
	public static final String ATT_FILE_LP = "fileLP";
	public static final String VIEW = "/JSP/page.jsp";
	
	private LeisureDAO leisureDAO;
	private RatingDAO ratingDAO;
	
	private List<Leisure> leisures;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rate() {
        super();
    }
    
	public void init() throws ServletException {
		// Récupération d'une instance de notre DAO Utilisateur
		this.leisureDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLeisureDAO();
		this.ratingDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getRatingDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATT_FILE_LP, "/restrained/LPRating.jsp");
		
		leisures = leisureDAO.selectAll();
		request.setAttribute("leisures", leisures);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}