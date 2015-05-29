package com.lavalloisir.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lavalloisir.beans.Leisure;
import com.lavalloisir.dao.DAOFactory;
import com.lavalloisir.dao.LeisureDAO;

/**
 * Servlet implementation class ShowLeisure
 */
public class ShowLeisure extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String URL_REDIRECTION = "IndexLeisures";
	public static final String ATT_FILE_LP = "fileLP";
	public static final String ATT_LEISURE = "leisure";
	public static final String VIEW = "/JSP/page.jsp";
	
	private LeisureDAO leisureDAO;
	private Leisure leisure;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowLeisure() {
        super();
    }
    
    public void init() throws ServletException {
    	this.leisureDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLeisureDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			long id = Long.parseLong(request.getParameter("id"));
			leisure = leisureDAO.read(id);
			request.setAttribute(ATT_LEISURE, leisure);
			request.setAttribute(ATT_FILE_LP, "/restrained/LPShowLeisure.jsp");
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		} else {
			response.sendRedirect(URL_REDIRECTION);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
