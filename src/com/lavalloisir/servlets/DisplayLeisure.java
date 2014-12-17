package com.lavalloisir.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lavalloisir.beans.business.Category;
import com.lavalloisir.beans.business.Leisure;
import com.lavalloisir.beans.dao.CategoryDAO;
import com.lavalloisir.beans.dao.DAOFactory;
import com.lavalloisir.beans.dao.LeisureDAO;

/**
 * Servlet implementation class DisplayLeisure
 */
public class DisplayLeisure extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_LEISURE = "leisure";
	public static final String ATT_FORM = "form";
	public static final String ATT_FILE_LP = "fileLP";
	public static final String VIEW = "/JSP/page.jsp";
	
	private LeisureDAO leisureDAO;
	private CategoryDAO categoryDAO;
	
	private List<Category> categories;
	private List<Leisure> leisures;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayLeisure() {
        super();
    }
    
	public void init() throws ServletException {
		// Récupération d'une instance de notre DAO Utilisateur
		this.leisureDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLeisureDAO();
		this.categoryDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCategoryDAO();
		
		categories = categoryDAO.selectAll();
		leisures = leisureDAO.selectAll(categories);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATT_FILE_LP, "/restrained/LPLeisure.jsp");
		request.setAttribute("restrained", "../");
		request.setAttribute("categories", categories);		
		request.setAttribute("leisures", leisures);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATT_FILE_LP, "/restrained/LPLeisure.jsp");
		request.setAttribute("categories", categories);		
		
		leisures = leisureDAO.selectByCategory(categories, Integer.parseInt(request.getParameter("categoryLeisr")));
		
		request.setAttribute("leisures", leisures);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
}
