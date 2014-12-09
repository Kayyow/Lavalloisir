package com.lavalloisir.beans.dao;

import com.lavalloisir.beans.business.User;

public interface UserDAO {
	void create (User user) throws DAOException;
	
	User find (String login, String password) throws DAOException;
	
	User findById (long id)throws DAOException;
}