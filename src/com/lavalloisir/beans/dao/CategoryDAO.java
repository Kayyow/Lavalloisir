package com.lavalloisir.beans.dao;

import com.lavalloisir.beans.business.Category;

public interface CategoryDAO {
	Category find(long id) throws DAOException;
}
