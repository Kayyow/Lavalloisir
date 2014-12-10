package com.lavalloisir.beans.dao;

import com.lavalloisir.beans.business.User;

public interface UserDAO {
	/**
	 * 
	 * @param user
	 * @throws DAOException
	 */
	void create (User user) throws DAOException;
	
	/**
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws DAOException
	 */
	User find (String login, String password) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	User find (long id)throws DAOException;
	
	/**
	 * 
	 * @param email
	 * @return
	 * @throws DAOException
	 */
	User find (String email)throws DAOException;
}