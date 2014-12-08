package com.lavalloisir.beans.dao;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class DAOUtil {
	private DAOUtil() {
	}
	
	/**
	 * Initialise la requête préparée basée sur la cnct passée en argument,
	 * avec la requête SQL et les objets donnés.
	 * @param cnct
	 * @param sql
	 * @param returnGeneratedKeys
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement initPreparedStatement (Connection cnct, String sql, boolean returnGeneratedKeys, Object... objects) throws SQLException {
		PreparedStatement preparedStmt = cnct.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		for ( int i = 0; i < objects.length; i++ ) {
			preparedStmt.setObject(i + 1, objects[i]);
		}
		
		return preparedStmt;
	}
	
	// Fermeture silencieuse du resultSet
	public static void silentClosing(ResultSet rs) {
	    if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException e) {
	            System.out.println("Échec de la fermeture du ResultSet : " + e.getMessage());
	        }
	    }
	}

	// Fermeture silencieuse du statement
	public static void silentClosing(Statement stmt) {
	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException e) {
	            System.out.println("Échec de la fermeture du Statement : " + e.getMessage());
	        }
	    }
	}

	// Fermeture silencieuse de la connexion
	public static void silentClosing(Connection cnct) {
	    if (cnct != null) {
	        try {
	            cnct.close();
	        } catch (SQLException e) {
	            System.out.println("Échec de la fermeture de la cnct : " + e.getMessage());
	        }
	    }
	}

	/**
	 * Fermetures silencieuses du statement et de la connexion
	 * @param stmt
	 * @param cnct
	 */
	public static void silentsClosing(Statement stmt, Connection cnct) {
	    silentClosing(stmt);
	    silentClosing(cnct);
	}

	/**
	 * Fermetures silencieuses du resultSet, du statement et de la connexion
	 * @param rs
	 * @param stmt
	 * @param cnct
	 */
	public static void silentsClosing(ResultSet rs, Statement stmt, Connection cnct) {
	    silentClosing(rs);
	    silentClosing(stmt);
	    silentClosing(cnct);
	}
}
