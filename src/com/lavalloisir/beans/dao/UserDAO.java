package com.lavalloisir.beans.dao;

import java.sql.ResultSet;

import com.lavalloisir.beans.business.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class UserDAO {

	public static User findUser(Connection cnx, String name, String firstName){
		
		Statement stmt = null;
		User user = null;
		
		try {
			stmt = (Statement)cnx.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT Nom, Prenom, Email, Login, DateInscription FROM utilisateur"
					+ "WHERE Nom ='" + name + "' AND Prenom ='" + firstName +"'");
			
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
}
