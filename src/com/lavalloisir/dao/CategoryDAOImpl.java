package com.lavalloisir.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lavalloisir.beans.Category;

public class CategoryDAOImpl implements CategoryDAO{

	private DAOFactory daoFactory;
	
	CategoryDAOImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public CategoryDAOImpl() {
	}
	
	@Override
	public List<Category> index () throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
        Category category = null;
        List<Category> categories = new ArrayList<Category>();
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectAll("category");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false);
			rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				category = map(rs);
				categories.add(category);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return categories;
	}
	
	public Category read(long id) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
        Category category = null;
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectWhere("category", "id = ?");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, id);
			rs = preparedStmt.executeQuery();
			
			if (rs.next()) {
				category = map(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return category;
	}
	
	private static Category map (ResultSet rs) throws SQLException {		
		Category category = new Category();
		category.setId(rs.getLong("id"));
		category.setLabel(rs.getString("label"));
		category.setDescription(rs.getString("description"));
		return category;
	}
}