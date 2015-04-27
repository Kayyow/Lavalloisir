package com.lavalloisir.dao;

import java.util.List;

import com.lavalloisir.beans.Category;

public interface CategoryDAO {
	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<Category> index() throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	Category read(long id) throws DAOException;
}