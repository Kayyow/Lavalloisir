package com.lavalloisir.beans.dao;

import java.util.ArrayList;

import com.lavalloisir.beans.business.Rating;

public interface RatingDAO {

	void create (Rating rating) throws DAOException;
	
	ArrayList<Rating> list () throws DAOException;
}
