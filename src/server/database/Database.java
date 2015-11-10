package server.database;

import java.sql.*;


public class Database
{

	//! The driver connection
	private Connection connection;
	

	public static String url;
	public static String username;
	public static String password;

	//! [Constructor]
	public Database() throws Exception
	{
		// Connecting to database
		this.connection = DriverManager.getConnection(url, username, password);
	}

	//! Execution comands
	public boolean execute(String sql) 
	throws Exception 
	{
		Statement stmt = this.connection.createStatement();
		boolean ret = stmt.execute(sql);
		stmt.close();
		return ret;
	}

	//! Query comands
	public ResultSet query(String sql) 
	throws Exception 
	{	
		Statement stmt = this.connection.createStatement();
		return stmt.executeQuery(sql);
	}
	
	//! Close conection to database
	public void close() throws Exception { this.connection.close(); }

}