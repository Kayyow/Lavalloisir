package com.lavalloisir.beans.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.lavalloisir.beans.business.Category;

public class CategoryDAOImpl implements CategoryDAO{

	private DAOFactory daoFactory;
	
	CategoryDAOImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public CategoryDAOImpl() {
	}

	private static final String SQL_SELECT = "SELECT"
			+ "Id, Titre, Description"
			+ "FROM categorie WHERE Id = ? ";
	
	//Implémentation de la méthode trouver() dans l'interface CategoryDAO
	@Override
	public Category find (long id) throws DAOException {
		Connection cnct = null;
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        Category category = null;
        
        try {
        	// Récupération d'une connexion depuis la Factory
        	cnct = daoFactory.getConnection();
        	preparedStmt = DAOUtil.initPreparedStatement(cnct, SQL_SELECT, false, id);
        	rs = preparedStmt.executeQuery();
        	
        	if(rs.next()){
        		category = map(rs);
        	}
        	
        } catch (SQLException e) {
        	throw new DAOException(e);
        } finally {
        	DAOUtil.silentsClosing(rs, preparedStmt, cnct);
        }
        
		return category;
	}
	
	private static Category map (ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getInt("Id"));
		category.setTitle(rs.getString("Titre"));
		category.setDescription(rs.getString("Description"));
		return category;
	}
	
	
	

	////////////////////////////////////////////////////////////
	////////// A MODIFIER/ADAPTER. (Fait par Julien) ///////////
	////////////////////////////////////////////////////////////
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
