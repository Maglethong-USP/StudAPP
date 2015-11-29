package server.database;

import java.sql.*;
import java.util.*;

import server.database.*;


public class Mensagem
{
	// Database fields
	private int id_origem;
	private int id_destino;
	private String conteudo;
	private Timestamp data;


	//! [Constructor]
	public Mensagem(int sender, int receiver, String content)
	{
		this.id_origem = sender;
		this.id_destino = receiver;
		this.conteudo = content.replace("'", "''");
		this.data = null;
	}

	//! Getters
	public int getSender(){ return this.id_origem; }
	public int getReceiver(){ return this.id_destino; }
	public String getContent(){ return this.conteudo.replace("''", "'"); }
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
		String sql = "INSERT INTO mensagem " 
						+ "(id_origem, id_destino, conteudo) VALUES ('"
					+ this.id_origem + "', '"
					+ this.id_destino + "', '"
					+ this.conteudo + "')";

		db.execute(sql);
		db.close();
	}

	// Remove
	public void remove() 
	throws Exception 
	{
		Database db = new Database();
		String sql = "DELETE FROM mensagem WHERE "
					+"id_origem = '" + this.id_origem + "' "
					+"AND id_destino = '" + this.id_destino + "'"
					+"AND data = '" + this.data + "'";

		db.execute(sql);
		db.close();
	}

	public static ResultSet findByConversation(int id_1, int id_2)
	throws Exception 
	{
		return findByConversation(id_1, id_2, null, null);
	}

	public static ResultSet findByConversation(int id_1, int id_2, 
												Timestamp newerThan)
	throws Exception 
	{
		return findByConversation(id_1, id_2, newerThan, null);
	}

	public static ResultSet findByConversation(int id_1, int id_2, 
												Timestamp newerThan,
												Timestamp olderThan)
	throws Exception 
	{
		Database db = new Database();
		String sql = "SELECT id_origem, id_destino, conteudo, data "
					+ "FROM mensagem WHERE "
					+ "(("
						+"id_origem = '" + id_1 + "'"
						+"AND id_destino = '" + id_2 + "'"
					+ ") OR ("
						+"id_origem = '" + id_2 + "'"
						+"AND id_destino = '" + id_1 + "'"
					+ ")) ";

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

	public static ResultSet findByOneSide(int id)
	throws Exception 
	{
		return findByOneSide(id, null, null);
	}

	public static ResultSet findByOneSide(int id, Timestamp newerThan)
	throws Exception 
	{
		return findByOneSide(id, newerThan, null);
	}

	public static ResultSet findByOneSide(int id, Timestamp newerThan,
													Timestamp olderThan)
	throws Exception 
	{
		Database db = new Database();
		String sql = "SELECT id_origem, id_destino, conteudo, data "
					+ "FROM mensagem WHERE "
						+ "("
							+ "id_origem = '" + id + "'"
							+ "OR id_destino = '" + id + "'"
						+ ")";

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

	public static MsgCount[] getMessageCount(int id)
	throws Exception 
	{
		return getMessageCount(id, null, null);
	}

	public static MsgCount[] getMessageCount(int id, Timestamp newerThan)
	throws Exception 
	{
		return getMessageCount(id, newerThan, null);
	}

	public static MsgCount[] getMessageCount(int id, Timestamp newerThan,
													Timestamp olderThan)
	throws Exception 
	{
		ArrayList<MsgCount> ret = new ArrayList<MsgCount>();
		Database db = new Database();
		String sql = "SELECT COUNT(data) as count, id_origem FROM mensagem "
					+ "WHERE id_destino = '" + id + "' ";

		if( newerThan != null )
		{
			sql += "AND data > '" + newerThan + "' ";
		}
		if( olderThan != null )
		{
			sql += "AND data < '" + olderThan + "' ";
		}

		sql += "GROUP BY(id_origem)";

		ResultSet rs = db.query(sql);
		while(rs.next())
		{
			int count 		= rs.getInt("count");
			int send_id 	= rs.getInt("id_origem");
			ret.add(new MsgCount(send_id, count));
		}

		return ret.toArray(new MsgCount[1]);
	}

	// Get Next user from ResultSet
	public static Mensagem next(ResultSet rs) 
	throws Exception 
	{
		Mensagem msg = null;

		if( rs.next() )
		{
			int id_origem 		= rs.getInt("id_origem");
			int id_destino 		= rs.getInt("id_destino");
			String conteudo 	= rs.getString("conteudo");
			Timestamp data 		= rs.getTimestamp("data");

			msg = new Mensagem(id_origem, id_destino, conteudo);

			msg.data = data;
		}

		return msg;
	}

	
	// To String
	public String toString()
	{
		return "From:    " + this.id_origem + "\n" 
			+  "To:      " + this.id_destino + "\n" 
			+  "Sent at: " + this.data + "\n"
			+  "         " + this.conteudo;
	}

	// Unit test
	public static void main(String args[]){
		// Configure Database
		Database.url = "jdbc:postgresql://localhost:5432/studapp";
		Database.username = "studapp";
		Database.password = "studapp";

	
		try
		{
			Usuario a = Usuario.findByEmail("andy@yopmail.com");
			Usuario b = Usuario.findByEmail("yvan@yopmail.com");

			Mensagem msg = new Mensagem(b.getID(), a.getID(), "Oi andy!");
			msg.insert();

			msg = new Mensagem(a.getID(), b.getID(), "Oi Yvan!");
			msg.insert();

			msg = new Mensagem(b.getID(), a.getID(), "Wololo!");
			msg.insert();
			Mensagem m = null;

			ResultSet rs = findByConversation(a.getID(), b.getID());
			while( (msg = Mensagem.next(rs)) != null )
			{
				m = msg;
				System.out.println(msg);
			}
			m.remove();

			rs = findByConversation(a.getID(), b.getID());
			while( (msg = Mensagem.next(rs)) != null )
			{
				m = msg;
				System.out.println(msg);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}

