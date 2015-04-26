package com.lavalloisir.dao;

import java.util.List;

import com.lavalloisir.beans.Evaluation;
import com.lavalloisir.beans.Leisure;
import com.lavalloisir.beans.User;

public interface EvaluationDAO {
//	/**
//	 * 
//	 * @param rating
//	 * @throws DAOException
//	 */
//	void create (Rating rating) throws DAOException;
//	
//	/**
//	 * 
//	 * @return
//	 * @throws DAOException
//	 */
//	/*Rating find(long idUser, long idLeisure) throws DAOException;*/
//	
//	/**
//	 * 
//	 * @return
//	 * @throws DAOException
//	 */
//	List<Rating> selectAll() throws DAOException;
	
	void create (Evaluation evaluation) throws DAOException;
	
	Evaluation read (Leisure leisure, User user) throws DAOException;
}
