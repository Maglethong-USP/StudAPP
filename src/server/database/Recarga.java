package server.database;

import java.sql.*;
import java.util.*;

import server.database.*;


public class Recarga
{
	// Database fields
	private int id;
	private float recarga;
	private Timestamp data;

	//! [Constructor]
	public Recarga(int id, float recharge)
	{
		this.id = id;
		this.recarga = recharge;
		this.data = null;
	}

	//! Setters
	public void setRecharge(float recharge)
	{
		this.recarga = recharge;
	}

	//! Getters
	public int getEmail(){ return this.id; }
	public float getRecharge(){ return this.recarga; }
	public Timestamp getDate(){ return this.data; }


	// --------------------------------------------------------------------- //
	//                                                                       //
	//                          Database Operations                          //
	//                                                                       //
	// --------------------------------------------------------------------- //

	// Insertion
	public void insert()
	throws Exception
	{
		Database db = new Database();
		String sql = "INSERT INTO recarga (id, recarga) VALUES ('"
					+ this.id + "', '"
					+ this.recarga + "')";

		db.execute(sql);
		db.close();
	}

	// Remove
	public void remove() 
	throws Exception 
	{
		Database db = new Database();
		String sql = "DELETE FROM recarga WHERE "
					+"id = '" + this.id + "' "
					+"AND data = '" + this.data + "'";

		db.execute(sql);
		db.close();
	}

	public static ResultSet find(int id)
	throws Exception 
	{
		return find(id, null, null);
	}

	public static ResultSet find(int id, Timestamp newerThan)
	throws Exception 
	{
		return find(id, newerThan, null);
	}

	public static ResultSet find(int id, Timestamp newerThan,
									Timestamp olderThan)
	throws Exception 
	{
		Database db = new Database();
		String sql = "SELECT id, recarga, data "
					+ "FROM recarga WHERE "
						+ "id = '" + id + "'";

		if( newerThan != null )
		{
			sql += "AND data > '" + newerThan + "' ";
		}
		if( olderThan != null )
		{
			sql += "AND data < '" + olderThan + "' ";
		}

		sql += "ORDER BY data";

		return db.query(sql);
	}

	// Get Next user from ResultSet
	public static Recarga next(ResultSet rs) 
	throws Exception 
	{
		Recarga rec = null;

		if( rs.next() )
		{
			int id 			= rs.getInt("id");
			float recarga 		= rs.getFloat("recarga");
			Timestamp data 		= rs.getTimestamp("data");

			rec = new Recarga(id, recarga);

			rec.data = data;
		}

		return rec;
	}

	
	// To String
	public String toString()
	{
		return "From:       " + this.id + "\n" 
			+  "Recharge:   " + this.recarga + "\n"
			+  "Date:       " + this.data;
	}

	// Unit test
	public static void main(String args[]){
		// Configure Database
		Database.url = "jdbc:postgresql://localhost:5432/studapp";
		Database.username = "studapp";
		Database.password = "studapp";

	
		try
		{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}

