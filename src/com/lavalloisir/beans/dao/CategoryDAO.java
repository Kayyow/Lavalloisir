package com.lavalloisir.beans.dao;

import com.lavalloisir.beans.business.Category;

public interface CategoryDAO {
	Category find(String title) throws DAOException;
}
