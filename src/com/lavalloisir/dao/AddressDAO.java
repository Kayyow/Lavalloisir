package com.lavalloisir.dao;

import com.lavalloisir.beans.Address;

public interface AddressDAO {
	void create (Address address) throws DAOException;
	
	void read (long id) throws DAOException;
}
