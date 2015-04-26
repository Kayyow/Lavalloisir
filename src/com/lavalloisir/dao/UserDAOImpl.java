package com.lavalloisir.dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

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
	
	//// NEW
	
	public void create (User user) throws DAOException {
		Connection cnct = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		
		try {
			// Récupération d'une connexion depuis la Factory
			cnct = daoFactory.getConnection();
			String query = SQLFactory.insertInto("user", "email, password, name, given_name, phone, picture, registration, last_connection",
					"?, ?, ?, ?, ?, ?, NOW(), NOW()");
			preparedStmt = DAOUtil.initPreparedStatement(cnct, query, true, user.getEmail(), user.getName(), user.getGivenName(),
					user.getPhone(), user.getPicture());
			if (preparedStmt.executeUpdate() == 0) {
				throw new DAOException("Echec de la création de l'utilisateur, aucune ligne ajoutée à la table.");
			}
			
			
			

    		// Analyse du statut retourné par la requête d'insertion
    		if (status == 0) {
    			throw new DAOException("Echec de la création de l'utilisateur, aucune ligne ajoutée à la table.");
    		}
    		
    		// Récupération de l'id auto-généré par la requête d'insertion
    		autoGeneratedValues = preparedStmt.getGeneratedKeys();
    		if (autoGeneratedValues.next()) {
    			// Puis initialisation de la propriété id du bean Utilisateur avec sa valeur
    			user.setId(autoGeneratedValues.getLong(1));
    		} else {
    			throw new DAOException("Echec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
    		}
    		
    		
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.silentsClosing(rs, preparedStmt, cnct);
		}
	}
	
	
	//\\ NEW

	private static final String SQL_SELECT_BY_LOGIN = "SELECT "
			+ "id, nom, prenom, email, login, motDePasse, photoProfil, dateInscription "
			+ "FROM utilisateur WHERE login = ?";
    // Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao
	@Override
    public User find (String login, String password, ConfigurablePasswordEncryptor pwdEncryptor) throws DAOException {
        Connection cnct = null;
        PreparedStatement preparedStmt = null;
        ResultSet autoGeneratedValues = null;
        User user = null;
        
        try {
        	// Récupération d'une connexion depuis la Factory
        	cnct = daoFactory.getConnection();
        	preparedStmt = DAOUtil.initPreparedStatement(cnct, SQL_SELECT_BY_LOGIN, false, login);
        	rs = preparedStmt.executeQuery();
        	
        	// Parcours de la ligne de donnée de l'éventuel ResultSet retourné
        	if (rs.next()) {
                if (pwdEncryptor.checkPassword(password, rs.getString("motDePasse"))) {
                	user = map(rs);
                }
        	}
        } catch (SQLException e) {
        	throw new DAOException(e);
        } finally {
        	DAOUtil.silentsClosing(autoGeneratedValues, preparedStmt, cnct);
        }
        
        return user;
    }
	
    private static final String SQL_SELECT_BY_ID = "SELECT "
			+ "id, nom, prenom, email, login, motDePasse, photoProfil, dateInscription "
			+ "FROM utilisateur WHERE login = ?";
	// Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao
    @Override
    public User find (long id) throws DAOException {
        Connection cnct = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        User user = null;
        
        try {
        	// Récupération d'une connexion depuis la Factory
        	cnct = daoFactory.getConnection();
        	preparedStmt = DAOUtil.initPreparedStatement(cnct, SQL_SELECT_BY_ID, false, id);
        	rs = preparedStmt.executeQuery();
        	
        	// Parcours de la ligne de donnée de l'éventuel ResultSet retourné
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
    
    private static final String SQL_SELECT_BY_EMAIL = "SELECT "
			+ "id, nom, prenom, email, login, motDePasse, photoProfil, dateInscription "
			+ "FROM utilisateur WHERE email = ? ";
    
	// Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao
    @Override
    public User find (String email) throws DAOException {
        Connection cnct = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        User user = null;
        
        try {
        	// Récupération d'une connexion depuis la Factory
        	cnct = daoFactory.getConnection();
        	preparedStmt = DAOUtil.initPreparedStatement(cnct, SQL_SELECT_BY_EMAIL, false, email);
        	rs = preparedStmt.executeQuery();
        	
        	// Parcours de la ligne de donnée de l'éventuel ResultSet retourné
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
    
    private static final String SQL_INSERT = "INSERT INTO utilisateur "
    		+ "(nom, prenom, email, login, motDePasse, photoProfil, dateInscription) "
    		+ "VALUES (?, ?, ?, ?, ?, 'WebContent/img/profil_picture.jpeg', NOW())";
	// Implémentation de la méthode creer() définie dans l'interface UtilisateurDao
    @Override
    public void create (User user) throws IllegalArgumentException, DAOException {
    	Connection cnct = null;
    	PreparedStatement preparedStmt = null;
    	ResultSet autoGeneratedValues = null;
    	
    	try {
    		// Récupération d'une connexion depuis la Factory
    		cnct = daoFactory.getConnection();
    		preparedStmt = DAOUtil.initPreparedStatement(cnct, SQL_INSERT, true,
    				user.getName(), user.getFirstName(), user.getEmail(), user.getLogin(),
    				user.getPassword());
    		int status = preparedStmt.executeUpdate();
    		
    		// Analyse du statut retourné par la requête d'insertion
    		if (status == 0) {
    			throw new DAOException("Echec de la création de l'utilisateur, aucune ligne ajoutée à la table.");
    		}
    		
    		// Récupération de l'id auto-généré par la requête d'insertion
    		autoGeneratedValues = preparedStmt.getGeneratedKeys();
    		if (autoGeneratedValues.next()) {
    			// Puis initialisation de la propriété id du bean Utilisateur avec sa valeur
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
    
    /**
	 * Simple méthode utilitaire permettant de faire la correspondance (le mapping)
	 * entre une ligne issue de la table utilisateur (un ResultSet) et un bean User
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static User map (ResultSet rs) throws SQLException {		
		User user = new User(rs.getLong("id"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setGivenName(rs.getString("given_name"));
		user.setPhone(rs.getString("phone"));
		user.setPicture(rs.getString("picture"));
		user.setRegistration(rs.getTimestamp("registration"));
		user.setLastConnection(rs.getTimestamp("last_connection"))
		return user;
		
	}
}