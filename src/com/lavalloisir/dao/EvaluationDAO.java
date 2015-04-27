package com.lavalloisir.dao;

import java.util.List;

import com.lavalloisir.beans.Evaluation;
import com.lavalloisir.beans.Leisure;

public interface EvaluationDAO {
	/**
	 * 
	 * @param evaluation
	 * @throws DAOException
	 */
	void create (Evaluation evaluation) throws DAOException;
	
	/**
	 * 
	 * @param leisure
	 * @return
	 * @throws DAOException
	 */
	List<Evaluation> index (Leisure leisure) throws DAOException;
}
