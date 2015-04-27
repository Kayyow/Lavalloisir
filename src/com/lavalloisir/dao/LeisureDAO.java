package com.lavalloisir.dao;

import java.util.List;

import com.lavalloisir.beans.Category;
import com.lavalloisir.beans.Leisure;

public interface LeisureDAO {
	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<Leisure> index () throws DAOException;
	
	/**
	 * 
	 * @param categories
	 * @return
	 * @throws DAOException
	 */
	List<Leisure> index (Category categories) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	Leisure read (long id) throws DAOException;
}
