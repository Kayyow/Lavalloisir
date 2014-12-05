package com.lavalloisir.beans.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
	// CONSTANT
	private static final String FICHIER_PROPERTIES = "/com/lavalloisir/dao/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USER_NAME = "username";
	private static final String PROPERTY_PASSWORD = "password";
	
	private String url;
	private String userName;
	private String password;
	
	// CONSTRUCTORS
	DAOFactory(String url, String userName, String password) {
		this.url = url;
		this.userName = userName;
		this.password = password;
	}
	
	/*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
	public static DAOFactory getInstrance() throws DAOConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String userName;
		String password;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);
		
		if (fichierProperties == null) {
			throw new DAOConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
		}
		
		try {
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			userName = properties.getProperty(PROPERTY_USER_NAME);
			password = properties.getProperty(PROPERTY_PASSWORD);
		} catch ( IOException e ) {
			throw new DAOConfigurationException("Impossible de charger le fichier properties");
		}
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
		}
		
		DAOFactory instance = new DAOFactory(url, userName, password);
		return instance;
	}
	
	/* Méthode chargée de fournir une connexion à la base de données */
	/* package*/ Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, userName, password);
	}
	
	/*
     * Méthodes de récupération de l'implémentation des différents DAO (un seul
     * pour le moment)
     */
    public UserDAO getUserDAO() {
        return new UserDAO(this);
    }
}
