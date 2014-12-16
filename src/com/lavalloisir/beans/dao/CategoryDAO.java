package com.lavalloisir.beans.dao;

import java.util.List;

import com.lavalloisir.beans.business.Category;

public interface CategoryDAO {
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	Category find(int id) throws DAOException;
	
	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<Category> selectAll() throws DAOException;
}