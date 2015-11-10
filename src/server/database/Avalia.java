package server.database;

import java.sql.*;
import java.util.*;

import server.database.*;


public class Avalia
{
	// Database fields
	private int id_avaliador;
	private int id_avaliado;
	private int nota;
	private String comentario;
	private Timestamp data;

	//! [Constructor]
	public Avalia(int id_rankin, int id_ranked, String comment, int rank)
	{
		this.id_avaliador = id_rankin;
		this.id_avaliado = id_ranked;
		this.nota = rank;
		this.comentario = comment;
		this.data = null;
	}
	public Avalia(int id_rankin, int id_ranked)
	{
		this.id_avaliador = id_rankin;
		this.id_avaliado = id_ranked;
		this.nota = -1;
		this.comentario = null;
		this.data = null;
	}

	//! Setters
	public void setComment(String comment)
	{
		this.comentario = comment;
	}
	public void setRank(int rank)
	{
		this.nota = rank;
	}

	//! Getters
	public int getRankin(){ return this.id_avaliador; }
	public int getRanked(){ return this.id_avaliado; }
	public String getComment(){ return this.comentario; }
	public int getRank(){ return this.nota; }
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
		String sql = "INSERT INTO avalia " 
						+ "(id_avaliador, id_avaliado, nota, comentario) "
						+ "VALUES ('"
							+ this.id_avaliador + "', '"
							+ this.id_avaliado + "', '"
							+ this.nota + "', '"
							+ this.comentario + "')";

		db.execute(sql);
		db.close();
	}

	// Remove
	public void remove() 
	throws Exception 
	{
		Database db = new Database();
		String sql = "DELETE FROM avalia WHERE "
					+"id_avaliador = '" + this.id_avaliador + "' "
					+"AND id_avaliado = '" + this.id_avaliado + "'";

		db.execute(sql);
		db.close();
	}

	public static ResultSet findRanked(int id)
	throws Exception 
	{
		return findRanked(id, null, null);
	}

	public static ResultSet findRanked(int id, Timestamp newerThan,
									Timestamp olderThan)
	throws Exception 
	{
		Database db = new Database();
		String sql = "SELECT id_avaliador, id_avaliado, nota, comentario, data "
					+ "FROM avalia WHERE "
						+ "id_avaliado = '" + id + "'";

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
	public static Avalia next(ResultSet rs) 
	throws Exception 
	{
		Avalia rec = null;

		if( rs.next() )
		{
			int id_avaliador 		= rs.getInt("id_avaliador");
			int id_avaliado 		= rs.getInt("id_avaliado");
			int nota 				= rs.getInt("nota");
			String comentario 		= rs.getString("comentario");
			Timestamp data 			= rs.getTimestamp("data");

			rec = new Avalia(id_avaliador, id_avaliado, comentario, nota);

			rec.data = data;
		}

		return rec;
	}

	
	// To String
	public String toString()
	{
		return "From:    " + this.id_avaliador + "\n" 
			+  "To:      " + this.id_avaliado + "\n"
			+  "Rank:    " + this.nota + "\n"
			+  "Comment: " + this.comentario + "\n"
			+  "Date:    " + this.data;
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

