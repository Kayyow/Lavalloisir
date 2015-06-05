package com.lavalloisir.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.lavalloisir.beans.Leisure;
import com.lavalloisir.beans.Evaluation;
import com.lavalloisir.beans.User;


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
	public Evaluation read (User user, Leisure leisure) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		Evaluation evaluation = null;
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectWhere("evaluation", "id_user = ? AND id_leisure = ?");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, user.getId(), leisure.getId());
			rs = preparedStmt.executeQuery();
			
			if (rs.next()) {
				evaluation = map(rs);
			}			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return evaluation;
	}
	
	@Override
	public void update (Evaluation evaluation, int note, String opinion) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.update("evaluation", "note = ?, opinion = ?", "id_user = " + evaluation.getUser().getId() + " AND id_leisure = " + evaluation.getLeisure().getId());
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, note, opinion);
			
			if (preparedStmt.executeUpdate() == 0) {
				throw new DAOException("Echec de la mise à jour de l'utilisateur, aucune modification prise en compte.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(preparedStmt, cnct);
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
	
	public float getAverageNote(Leisure leisure) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		float averageNote = 0;
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectAverageNote();
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, leisure.getId());
			rs = preparedStmt.executeQuery();
			
			if (rs.next()) {
				averageNote = rs.getFloat(1);
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return averageNote;
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
			DecimalFormat df = new DecimalFormat("#.#");			
			
			while (rs.next()) {
				String str = rs.getString(1) + " : " + df.format(rs.getFloat(2));
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