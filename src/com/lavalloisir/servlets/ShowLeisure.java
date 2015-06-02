package com.lavalloisir.servlets;

import java.text.DecimalFormat;
import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lavalloisir.beans.Evaluation;
import com.lavalloisir.beans.Leisure;
import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOFactory;
import com.lavalloisir.dao.EvaluationDAO;
import com.lavalloisir.dao.LeisureDAO;
import com.lavalloisir.forms.EvaluationForm;

/**
 * Servlet implementation class ShowLeisure
 */
public class ShowLeisure extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String URL_REDIRECTION = "IndexLeisures";
	public static final String ATT_FILE_LP = "fileLP";
	public static final String ATT_LEISURE = "leisure";
	public static final String ATT_AVG_NOTE = "averageNote";
	public static final String ATT_EVALUATIONS = "evaluations";
	public static final String ATT_BEST_LEISURES = "bestLeisures";
	public static final String VIEW = "/JSP/page.jsp";
	
	private LeisureDAO leisureDAO;
	private Leisure leisure;
	private EvaluationDAO evaluationDAO;
	private List<String> bestLeisures;
	private List<Evaluation> leisureEvaluations;
	private float avgNote = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowLeisure() {
        super();
    }
    
    public void init() throws ServletException {
    	this.leisureDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLeisureDAO();
    	this.evaluationDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getEvaluationDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			long id = Long.parseLong(request.getParameter("id"));
			leisure = leisureDAO.read(id);
			
			leisureEvaluations = evaluationDAO.index(leisure);
			
			// Calcul de la note moyenne donnée au loisir par les users
			for (Evaluation e : leisureEvaluations) {
				avgNote += e.getNote();
			}
			avgNote /= leisureEvaluations.size();


			request.setAttribute(ATT_LEISURE, leisure);
			// Get best Leisures
			bestLeisures = evaluationDAO.getBestLeisures();			
			request.setAttribute(ATT_BEST_LEISURES, bestLeisures);

			DecimalFormat df = new DecimalFormat("#.#");
			request.setAttribute(ATT_AVG_NOTE, df.format(avgNote));
			
			request.setAttribute(ATT_EVALUATIONS, leisureEvaluations);
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
		EvaluationForm form = new EvaluationForm(evaluationDAO, leisureDAO);

		form.noteLeisure(request, (User)request.getSession().getAttribute("user"), leisure);
		
		leisureEvaluations = evaluationDAO.index(leisure);
		
		avgNote = 0;
		// Calcul de la note moyenne donnée au loisir par les users
		for (Evaluation e : leisureEvaluations) {
			avgNote += e.getNote();
		}
		avgNote /= leisureEvaluations.size();

		DecimalFormat df = new DecimalFormat("#.#");
		request.setAttribute(ATT_AVG_NOTE, df.format(avgNote));
		request.setAttribute(ATT_LEISURE, leisure);
		request.setAttribute(ATT_EVALUATIONS, leisureEvaluations);
		request.setAttribute(ATT_FILE_LP, "/restrained/LPShowLeisure.jsp");
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}
}
