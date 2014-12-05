package com.lavalloisir.beans.dao;

import com.lavalloisir.beans.business.Rating;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class RatingDAOImpl {


	public static void create(Connection cnx, Rating rating){
		
		Statement stmt = null;
		
		
		try {
			stmt = (Statement)cnx.createStatement();
		
			stmt.executeUpdate("INSERT INTO evaluation (Note, Option_Courte, Commentaire, Date, Id_Utilisateur, Id_Loisir)"
					+"VALUES('" + rating.getRating() + "',"
					+ "'" + rating.getSummaryComment() +"',"
					+"'" + rating.getComment() + "',"
					+"'" + rating.getDateRating() + "',"
					+ "'" + rating.getUser().getId() + "',"
					+ "'" + rating.getLeisure().getId() +"')");

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
