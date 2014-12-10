package com.lavalloisir.beans.dao;

import com.lavalloisir.beans.business.Leisure;

public interface LeisureDAO {
	/**
	 * 
	 * @param leisure
	 * @throws DAOException
	 */
	void create (Leisure leisure) throws DAOException;
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws DAOException
	 */
	Leisure find (String name) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	Leisure find (long id) throws DAOException;
}
