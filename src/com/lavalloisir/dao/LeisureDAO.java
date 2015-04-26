package com.lavalloisir.dao;

import java.util.List;

import com.lavalloisir.beans.Category;
import com.lavalloisir.beans.Leisure;

public interface LeisureDAO {
//	/**
//	 * 
//	 * @param leisure
//	 * @throws DAOException
//	 */
//	void create (Leisure leisure) throws DAOException;
//	
//	/**
//	 * 
//	 * @param id
//	 * @return
//	 * @throws DAOException
//	 */
//	Leisure find (long id, List<Category> categories) throws DAOException;
//	
//	/**
//	 * 
//	 * @return
//	 * @throws DAOException
//	 */
//	List<Leisure> selectAll() throws DAOException;
//	
//	/**
//	 * 
//	 * @param categories
//	 * @return
//	 * @throws DAOException
//	 */
//	List<Leisure> selectAll(List<Category> categories, List<Rating> ratings) throws DAOException;
//	
//	/**
//	 * 
//	 * @param categories
//	 * @param idCategory
//	 * @return
//	 * @throws DAOException
//	 */
//	List<Leisure> selectByCategory(List<Category> categories, List<Rating> ratings, int idCategory) throws DAOException;
//	
//	/**
//	 * 
//	 * @param id
//	 * @return
//	 * @throws DAOException
//	 */
//	Leisure find (long id) throws DAOException;
	
	List<Leisure> index() throws DAOException;
	
	List<Leisure> index(List<Category> categories) throws DAOException;
	
	Leisure read (long id) throws DAOException;
}
