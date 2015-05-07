package com.lavalloisir.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.lavalloisir.beans.Category;
import com.lavalloisir.beans.Leisure;

public class LeisureDAOImpl implements LeisureDAO {
	
	private DAOFactory daoFactory;
	
	LeisureDAOImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public LeisureDAOImpl() {
	}
	
	@Override
	public List<Leisure> index () throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		Leisure leisure = null;
		List<Leisure> leisures = new ArrayList<Leisure>();
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectAll("leisure");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false);
			
			rs = preparedStmt.executeQuery();
        	
        	while (rs.next()) {
        		leisure = map(rs);
        		leisures.add(leisure);
        	}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return leisures;
	}
	
	@Override
	public List<Leisure> index (Category category) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		Leisure leisure = null;
		List<Leisure> leisures = new ArrayList<Leisure>();
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectWhere("leisure", "id_category = ?");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, category.getId());
			
			rs = preparedStmt.executeQuery();
        	
        	while (rs.next()) {
        		leisure = map(rs);
        		leisures.add(leisure);
        	}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return leisures;
	}
	
	@Override
	public Leisure read (long id) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		Leisure leisure = null;
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectWhere("leisure", "id = ?");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, id);
			
			rs = preparedStmt.executeQuery();
        	
        	if (rs.next()) {
        		leisure = map(rs);
        	}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return leisure;
	}
    
	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static Leisure map (ResultSet rs) throws SQLException {
		Leisure leisure = new Leisure();
		
		leisure.setId(rs.getLong("id"));
		leisure.setTitle(rs.getString("title"));
		leisure.setDescription(rs.getString("description"));
		leisure.setEmail(rs.getString("email"));
		leisure.setPhone(rs.getString("phone"));
		leisure.setWebsite(rs.getString("website"));
		
		AddressDAO addressDAO = DAOFactory.getInstance().getAddressDAO();
		leisure.setAddress(addressDAO.read(rs.getLong("id_address")));
		
		CategoryDAO categoryDAO = DAOFactory.getInstance().getCategoryDAO();
		leisure.setCategory(categoryDAO.read(rs.getLong("id_category")));

		return leisure;
	}
}