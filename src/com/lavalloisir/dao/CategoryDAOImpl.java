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

	private static final String SQL_SELECT_BY_ID = "SELECT "
			+ "Id, Titre, Description "
			+ "FROM categorie WHERE Id = ?";
	//Implémentation de la méthode trouver() dans l'interface CategoryDAO
	@Override
	public Category find (int id) throws DAOException {
		Connection cnct = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        Category category = null;
        
        try {
        	// Récupération d'une connexion depuis la Factory
        	cnct = daoFactory.getConnection();
        	preparedStmt = DAOUtil.initPreparedStatement(cnct, SQL_SELECT_BY_ID, false, id);
        	rs = preparedStmt.executeQuery();
        	
        	if(rs.next()){
        		category = map(rs);
        	}
        	
        } catch (SQLException e) {
        	throw new DAOException(e);
        } finally {
        	DAOUtil.silentsClosing(rs, preparedStmt, cnct);
        }
        
		return category;
	}
	
	private static final String SQL_SELECT_ALL = "SELECT "
			+ "Id, Titre, Description "
			+ "FROM categorie";
	@Override
	public List<Category> selectAll() throws DAOException {
		Connection cnct = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        Category category = null;
        List<Category> categories = new ArrayList<Category>();
        
        try {
        	// Récupération d'une connexion depuis la Factory
        	cnct = daoFactory.getConnection();
        	preparedStmt = DAOUtil.initPreparedStatement(cnct, SQL_SELECT_ALL, false);
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
	
	private static Category map (ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getInt("Id"));
		category.setTitle(rs.getString("Titre"));
		category.setDescription(rs.getString("Description"));
		return category;
	}
}