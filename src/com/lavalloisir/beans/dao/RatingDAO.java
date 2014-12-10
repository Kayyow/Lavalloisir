package com.lavalloisir.beans.dao;

import java.util.ArrayList;

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
	ArrayList<Rating> list () throws DAOException;
}
