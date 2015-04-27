package com.lavalloisir.dao;

public class SQLFactory {	
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
}
