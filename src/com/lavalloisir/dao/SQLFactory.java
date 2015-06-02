package com.lavalloisir.dao;

public final class SQLFactory {
	private SQLFactory() {
	}
	
	public static String selectAll(String table) {
		return "SELECT * FROM " + table;
	}
	
	public static String selectWhere(String table, String where) {
		return "SELECT * FROM " + table + " WHERE " + where;
	}
	
	public static String insertInto(String table, String fields, String values) {
		return "INSERT INTO " + table + " (" + fields + ") VALUES (" + values + ")";
	}
	
	public static String update(String table, String set, String where) {
		return "UPDATE " + table + " SET " + set + " WHERE " + where;
	}
	
	public static String delete(String table, String where) {
		return "DELETE FROM " + table + " WHERE " + where;
	}
	
	public static String selectFiveBestLeisures() {
		return "SELECT title, AVG(note) "
				+ "FROM leisure, evaluation "
				+ "WHERE leisure.id = evaluation.id_leisure "
				+ "GROUP BY id_leisure "
				+ "ORDER BY AVG(note) DESC "
				+ "LIMIT 5";
	}
}
