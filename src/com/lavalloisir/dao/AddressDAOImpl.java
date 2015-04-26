package com.lavalloisir.dao;

public class AddressDAOImpl implements AddressDAO {
	
	private DAOFactory daoFactory;
	
	AddressDAOImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
}
