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
import com.lavalloisir.dao.EvaluationDAO;
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
	public static final String ATT_BEST_LEISURES = "bestLeisures";
	public static final String ATT_SELECTED_CATEGORY = "";
	public static final String VIEW = "/JSP/page.jsp";
      
	private LeisureDAO leisureDAO;
	private List<Leisure> leisures = new ArrayList<Leisure>();
	private CategoryDAO categoryDAO;
	private List<Category> categories;
	private EvaluationDAO evaluationDAO;
	private List<String> bestLeisures;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexLeisures() {
        super();
    }
    
    public void init() throws ServletException {
    	this.leisureDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLeisureDAO();
    	this.categoryDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCategoryDAO();
    	this.evaluationDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getEvaluationDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		categories = categoryDAO.index();
		request.setAttribute(ATT_CATEGORIES, categories);
		
		if (request.getParameter("category") == null) {
			leisures = leisureDAO.index();
		} else {
			int selectedCategory = Integer.parseInt(request.getParameter("category"));
			request.setAttribute(ATT_SELECTED_CATEGORY, selectedCategory);
			leisures = leisureDAO.index(categoryDAO.read(selectedCategory));			
		}
		request.setAttribute(ATT_LEISURES, leisures);
		
		// Les 5 meilleurs loisirs
		bestLeisures = evaluationDAO.getBestLeisures();			
		request.setAttribute(ATT_BEST_LEISURES, bestLeisures);
		
		request.setAttribute(ATT_FILE_LP, "/restrained/LPIndexLeisures.jsp");
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
