package com.lavalloisir.beans.dao;

import java.util.List;

import com.lavalloisir.beans.business.Category;
import com.lavalloisir.beans.business.Leisure;
import com.lavalloisir.beans.business.Rating;

public interface LeisureDAO {
	/**
	 * 
	 * @param leisure
	 * @throws DAOException
	 */
	void create (Leisure leisure) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	Leisure find (long id, List<Category> categories) throws DAOException;
	
	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<Leisure> selectAll() throws DAOException;
	
	/**
	 * 
	 * @param categories
	 * @return
	 * @throws DAOException
	 */
	List<Leisure> selectAll(List<Category> categories, List<Rating> ratings) throws DAOException;
	
	/**
	 * 
	 * @param categories
	 * @param idCategory
	 * @return
	 * @throws DAOException
	 */
	List<Leisure> selectByCategory(List<Category> categories, List<Rating> ratings, int idCategory) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	Leisure find (long id) throws DAOException;
}
