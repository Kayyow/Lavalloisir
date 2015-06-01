package com.lavalloisir.dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.lavalloisir.beans.User;
import com.lavalloisir.dao.DAOUtil;
import com.lavalloisir.dao.UserDAO;

public class UserDAOImpl implements UserDAO {
	private DAOFactory daoFactory;
	
	UserDAOImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public UserDAOImpl() {
	}
	
	@Override
	public void create (User user) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet autoGeneratedValues = null;
		
		try {
			// Récupération d'une connexion depuis la Factory
			cnct = daoFactory.getConnection();
			String query = SQLFactory.insertInto("user", "email, password, name, given_name, phone, picture, registration, last_connection",
					"?, ?, ?, ?, ?, ?, NOW(), NOW()");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, true, user.getEmail(), user.getPassword(), user.getName(),
					user.getGivenName(), user.getPhone(), user.getPicture());
			
			if (preparedStmt.executeUpdate() == 0) {
				throw new DAOException("Echec de la création de l'utilisateur, aucune ligne ajoutée à la table.");
			}
			
			autoGeneratedValues = preparedStmt.getGeneratedKeys();
			if(autoGeneratedValues.next()) {
				// Puis initialisation de la propritété du id bean User avec sa valeur
				user.setId(autoGeneratedValues.getLong(1));
			} else {
				throw new DAOException("Echec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(autoGeneratedValues, preparedStmt, cnct);
		}
	}
	
	@Override
	public User read (long id) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectWhere("user", "id = ?");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, id);
			rs = preparedStmt.executeQuery();
			
			if (rs.next()) {
				user = map(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return user;
	}
	
	@Override
	public User read (String email) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectWhere("user", "email = ?");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, email);
			rs = preparedStmt.executeQuery();
			
			if (rs.next()) {
				user = map(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return user;
	}
	
	@Override
	public User read (String email, String password, ConfigurablePasswordEncryptor pwdEncrypt) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.selectWhere("user", "email = ?");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, email);
			rs = preparedStmt.executeQuery();
			
			if (rs.next()) {
				if (pwdEncrypt.checkPassword(password, rs.getString("password"))) {
					user = map(rs);
				}
			}			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
		
		return user;
	}
	
	@Override
	public void update (long id, User user) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.update("user", "email = ?, name = ?, given_name = ?, "
					+ "phone = ?", "id = " + id);
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, user.getEmail(),
					user.getName(), user.getGivenName(), user.getPhone());
			
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
	public void updateLastConnection (long id) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		
		try {
			cnct = daoFactory.getConnection();
			String query = SQLFactory.update("user", "last_connection = ?", "id = " + id);
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, false, new Timestamp(new Date().getTime()));
			
			if (preparedStmt.executeUpdate() == 0) {
				throw new DAOException("Echec de la mise à jour de l'utilisateur, aucune modification prise en compte.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(preparedStmt, cnct);
		}
	}
    
    /**
	 * Simple méthode utilitaire permettant de faire la correspondance (le mapping)
	 * entre une ligne issue de la table utilisateur (un ResultSet) et un bean User
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static User map (ResultSet rs) throws SQLException {		
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setGivenName(rs.getString("given_name"));
		user.setPhone(rs.getString("phone"));
		user.setPicture(rs.getString("picture"));
		user.setRegistration(rs.getTimestamp("registration"));
		user.setLastConnection(rs.getTimestamp("last_connection"));
		return user;
	}
}