package com.lavalloisir.dao;

import java.util.List;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.lavalloisir.beans.User;

public interface UserDAO {
	/**
	 * 
	 * @param user
	 * @throws DAOException
	 */
	void create (User user) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	User read (long id) throws DAOException;
	
	/**
	 * 
	 * @param email
	 * @return
	 * @throws DAOException
	 */
	User read (String email) throws DAOException;
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @param pwdEncrypt
	 * @return
	 * @throws DAOException
	 */
	User read (String email, String password, ConfigurablePasswordEncryptor pwdEncrypt) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @param user
	 * @throws DAOException
	 */
	void update (long id, User user) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @param user
	 * @throws DAOException
	 */
	void updateLastConnection (long id) throws DAOException;
	
	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<User> index () throws DAOException;
}