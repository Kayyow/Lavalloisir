package com.lavalloisir.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.lavalloisir.beans.Leisure;
import com.lavalloisir.beans.Evaluation;


public class EvaluationDAOImpl implements EvaluationDAO {

	private DAOFactory daoFactory;
	
	EvaluationDAOImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create (Evaluation evaluation) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.insertInto("evaluation", "note, opinion, id_leisure, id_user",
					"?, ?, ?, ?");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, evaluation.getNote(),
					evaluation.getOpinion(), evaluation.getLeisure().getId(), evaluation.getUser().getId());
			
			if (preparedStmt.executeUpdate() == 0) {
				throw new DAOException("Echec de la création de l'evaluation, aucune ligne ajoutée à la table.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
	}
	
	@Override
	public List<Evaluation> index (Leisure leisure) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		Evaluation evaluation = null;
		List<Evaluation> evaluations = new ArrayList<Evaluation>();
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectWhere("evaluation", "id_leisure = ?");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, leisure.getId());
			rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				evaluation = map(rs);
				evaluations.add(evaluation);
			}			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return evaluations;
	}
	
	public List<String> getBestLeisures() throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		List<String> bestLeisures = new ArrayList<String>();
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectFiveBestLeisures();
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false);
			rs = preparedStmt.executeQuery();

			while (rs.next()) {
				String str = rs.getString(1) + " : " + rs.getFloat(2);
				bestLeisures.add(str);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return bestLeisures;
	}
	
	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static Evaluation map (ResultSet rs) throws SQLException {
		Evaluation evaluation = new Evaluation();
		
		evaluation.setNote(rs.getInt("note"));
		evaluation.setOpinion(rs.getString("opinion"));
		
		LeisureDAO leisureDAO = DAOFactory.getInstance().getLeisureDAO();
		evaluation.setLeisure(leisureDAO.read(rs.getLong("id_leisure")));
		
		UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
		evaluation.setUser(userDAO.read(rs.getLong("id_user")));
		
		return evaluation;
	}
}