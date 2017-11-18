/**
 * 
 */
package db;

import java.sql.*;

/**
 * @author corentinboget
 *
 */
public class Database {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/";
	
	static final String USER = "root";
	static final String PASSWORD = "root";
	
	public static void main(String[] args){
		Connection connection = null;
		Statement statement = null;
		
		try {
			// ENREGISTREMENT DU DRIVER
			Class.forName("com.mysql.jdbc.Driver");
			
			// OUVERTURE DE LA CONNEXION
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			// EXECUTION D'UN QUERY POUR CONSTRUIRE ET ENVOYER DES REQUETES SQL
			System.out.println("Creating database...");
			statement = connection.createStatement();
			
			// CREATION DE LA DB
			String sql = "CREATE DATABASE USERS";
			statement.executeUpdate(sql);
			System.out.println("Database created successfully...");
			
		} catch(SQLException sqlE){
			if(sqlE.getErrorCode() == 1007){
				//DATABASE ALREADY EXIST
				System.out.println(sqlE.getMessage());
			}
		} catch(ClassNotFoundException e) {
			System.out.println("Error : No driver class found !");
		}finally {
			
			try {
				if(statement != null)
					statement.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
			
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Bye !");
	}
}
