package com.lavalloisir.beans.dao;

import java.sql.ResultSet;

import com.lavalloisir.beans.business.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class UserDAOImpl {

	public static User find(Connection cnx, String login){
		
		Statement stmt = null;
		User user = null;
		
		try {
			stmt = (Statement)cnx.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT Nom, Prenom, Email, Login, DateInscription FROM utilisateur"
					+ "WHERE Logn ='" + login + "'");
			
			if(result.next()){
				user = new User();
				
				user.setName(result.getString("Nom"));
				user.setFirstName(result.getString("Prenom"));
				user.setEmail(result.getString("Email"));
				user.setLogin(result.getString("Login"));
				user.setRegisterDate(result.getTimestamp("DateInscription"));
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

		return user;
	}
	
	public static void create(Connection cnx, User user){
		

		User u = find(cnx, user.getLogin());
		Statement stmt = null;
		
		if( u == null){
			
			try {
				stmt = (Statement)cnx.createStatement();
			
				stmt.executeUpdate("INSERT INTO utilisateur (Nom, Prenom, Email, Login, MotDePasse, DateInscription)"
						+"VALUES('" + user.getName() + "',"
						+ "'" + user.getFirstName() +"',"
						+"'" + user.getEmail() + "',"
						+"'" + user.getLogin() + "',"
						+ "'" + user.getPassword() + "',"
						+ "'" + user.getRegisterDate() +"')");

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
