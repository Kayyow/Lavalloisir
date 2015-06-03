package com.lavalloisir.dao;

import java.util.List;

import com.lavalloisir.beans.Evaluation;
import com.lavalloisir.beans.Leisure;
import com.lavalloisir.beans.User;

public interface EvaluationDAO {
	/**
	 * 
	 * @param evaluation
	 * @throws DAOException
	 */
	void create (Evaluation evaluation) throws DAOException;
	
	/**
	 * 
	 * @param user
	 * @param leisure
	 * @return
	 * @throws DAOException
	 */
	Evaluation read (User user, Leisure leisure) throws DAOException;
	
	/**
	 * 
	 * @param evaluation
	 * @param note
	 * @param opinion
	 * @throws DAOException
	 */
	void update(Evaluation evaluation, int note, String opinion) throws DAOException;
	
	/**
	 * 
	 * @param leisure
	 * @return
	 * @throws DAOException
	 */
	List<Evaluation> index (Leisure leisure) throws DAOException;
	
	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	float getAverageNote(Leisure leisure) throws DAOException;
	
	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<String> getBestLeisures() throws DAOException;
}
