package com.lavalloisir.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	


import com.lavalloisir.beans.Category;
import com.lavalloisir.beans.Leisure;
import com.lavalloisir.dao.CategoryDAO;
import com.lavalloisir.dao.DAOFactory;
import com.lavalloisir.dao.LeisureDAO;

/**
 * Servlet implementation class IndexLeisures
 */
public class IndexLeisures extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_FILE_LP = "fileLP";
	public static final String ATT_LEISURES = "leisures";
	public static final String ATT_CATEGORIES = "categories";
	public static final String VIEW = "/JSP/page.jsp";
      
	private LeisureDAO leisureDAO;
	private List<Leisure> leisures = new ArrayList<Leisure>();
	private CategoryDAO categoryDAO;
	private List<Category> categories;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexLeisures() {
        super();
    }
    
    public void init() throws ServletException {
    	this.leisureDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLeisureDAO();
    	this.categoryDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCategoryDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		leisures = leisureDAO.index();
		request.setAttribute(ATT_LEISURES, leisures);
		
		categories = categoryDAO.index();
		request.setAttribute(ATT_CATEGORIES, categories);
		
		request.setAttribute(ATT_FILE_LP, "/restrained/LPIndexLeisures.jsp");
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		leisures = leisureDAO.index(categoryDAO.read(Integer.parseInt(request.getParameter("category"))));
		request.setAttribute("leisures", leisures);
		
		request.setAttribute("categories", categories);
		
		request.setAttribute(ATT_FILE_LP, "/restrained/LPIndexLeisures.jsp");
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

}
