package com.lavalloisir.beans.dao;

import com.lavalloisir.beans.business.Rating;

public interface RatingDAO {
	/**
	 * 
	 * @param rating
	 * @throws DAOException
	 */
	void create (Rating rating) throws DAOException;
	
	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	Rating find(long idUser, long idLeisure) throws DAOException;
}
