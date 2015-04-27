package com.lavalloisir.dao;

import com.lavalloisir.beans.Address;

public interface AddressDAO {
	/**
	 * 
	 * @param address
	 * @throws DAOException
	 */
	void create (Address address) throws DAOException;
	
	/**
	 * 
	 * @param id
	 * @throws DAOException
	 */
	Address read (long id) throws DAOException;
}
