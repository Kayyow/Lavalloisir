package com.lavalloisir.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lavalloisir.beans.Leisure;
import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOFactory;
import com.lavalloisir.dao.LeisureDAO;
import com.lavalloisir.dao.UserDAO;

/**
 * Servlet implementation class ListMember
 */
public class ListMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_FILE_LP = "fileLP";
	public static final String VIEW = "/JSP/page.jsp";
	public static final String ATT_USERS = "users";
	
	private UserDAO userDAO;
	private List<User> users = new ArrayList<User>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListMember() {
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
		
		users = userDAO.index();
		request.setAttribute(ATT_USERS, users);
		
		request.setAttribute(ATT_FILE_LP, "/restrained/LPListMember.jsp");
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
