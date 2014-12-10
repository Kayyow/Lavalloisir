package com.lavalloisir.beans.dao;

import com.lavalloisir.beans.business.Category;

public interface CategoryDAO {
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	Category find(long id) throws DAOException;
}