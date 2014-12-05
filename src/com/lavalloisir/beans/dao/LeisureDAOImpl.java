package com.lavalloisir.beans.dao;

import java.sql.ResultSet;

import com.lavalloisir.beans.business.Category;
import com.lavalloisir.beans.business.Leisure;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class LeisureDAOImpl {

	public static Leisure find(Connection cnx, String Nom){
		
		Statement stmt = null; 
		Leisure leisure = null; 
		
		try {
			stmt = (Statement)cnx.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT Nom, Num, Rue, CodePostale, Ville, Description, Telephone, Email, Id_Categorie FROM loisir WHERE Nom ='" + Nom + "'");
			
			if(result.next()){
				leisure = new Leisure();
				leisure.setName(result.getString("Nom"));
				leisure.setNumber(result.getInt("Num"));
				leisure.setStreet(result.getString("Rue"));
				leisure.setZipCode(result.getInt("CodePostale"));
				leisure.setCity(result.getString("Ville"));
				leisure.setDescription(result.getString("Description"));
				leisure.setPhone(result.getString("Telephone"));
				leisure.setEmail(result.getString("Email"));
				
				long idCategory = result.getLong("Id_Categorie");
				Category category = CategoryDAOImpl.find(cnx, idCategory);
				leisure.setCategory(category);
			}
			
		}catch(Exception ex){
			
		}finally {
			if(stmt != null){
                try{
                    stmt.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
		}

		return leisure;
	}
	
	public static void create(Connection cnx, Leisure leisure){
		
		Leisure l = find(cnx, leisure.getName());

		Statement stmt = null;
		
		if( l == null){
			
			try {
				stmt = (Statement)cnx.createStatement();
			
				stmt.executeUpdate("INSERT INTO loisir (Nom, Num, Rue, CodePostale, Ville, Description, Telephone,Email, Id_Categorie) "
						+ "VALUES('"+ leisure.getName()
						+ "','" + leisure.getNumber() 
						+ "','" + leisure.getStreet()
						+ "','" + leisure.getZipCode()
						+ "','" + leisure.getCity()
						+ "','" + leisure.getDescription()
						+ "','" + leisure.getPhone()
						+ "','" + leisure.getEmail()
						+ "','" + leisure.getCategory().getId()
						+ "')");

			}catch(Exception ex){
				
			}finally {
				if(stmt != null){
					try{
						stmt.close();
					} catch (Exception Ex){
					}
				}
			}
		}
	}

}
