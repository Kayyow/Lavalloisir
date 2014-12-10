package com.lavalloisir.beans.dao;

import com.lavalloisir.beans.business.Leisure;

public interface LeisureDAO {
	void create (Leisure leisure) throws DAOException;
	
	Leisure find (String name) throws DAOException;
	
	Leisure findById (long id) throws DAOException;
}
