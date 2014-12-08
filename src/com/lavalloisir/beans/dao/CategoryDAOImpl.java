package com.lavalloisir.beans.dao;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

import com.lavalloisir.beans.business.Category;

public class CategoryDAOImpl {

	
	public static Category find(Connection cnx, String title){
		
		Statement stmt = null; 
		Category cat = null; 
		
		try {
			stmt = (Statement)cnx.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT Titre, Description FROM categorie WHERE Titre ='" + title + "'");
			
			if(result.next()){
				cat = new Category();
				cat.setTitle(result.getString("Titre"));
				cat.setDescription(result.getString("Description"));				
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

		return cat;
	}
	
public static Category find(Connection cnx, long id){
		
		Statement stmt = null; 
		Category cat = null; 
		
		try {
			stmt = (Statement)cnx.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT Titre, Description FROM categorie WHERE Id ='" + id + "'");
			
			if(result.next()){
				cat = new Category();
				cat.setTitle(result.getString("Titre"));
				cat.setDescription(result.getString("Description"));				
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

		return cat;
	}

	public static void create (Connection cnx, Category category){
			
			Category c = find(cnx, category.getTitle());
	
			Statement stmt = null;
			
			if( c == null){
				
				try {
					stmt = (Statement)cnx.createStatement();
				
					stmt.executeUpdate("INSERT INTO categorie (Titre, Description) "
							+ "VALUES('"+ category.getTitle()+"', '" + category.getDescription() +"')");
	
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
