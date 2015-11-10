package server.database;

import java.sql.*;
import java.util.*;

import server.database.*;


public class LigacaoPaga
{
	// Database fields
	private int id_aluno;
	private int id_professor;
	private Timestamp inicio;
	private Timestamp fim;
	private float custo;

	//! [Constructor]
	public LigacaoPaga(int student, int teacher)
	{
		this.id_aluno = student;
		this.id_professor = teacher;
		this.inicio = null;
		this.fim = null;
		this.custo = -1;
	}
	public LigacaoPaga(int student, int teacher, 
						Timestamp start, Timestamp end)
	{
		this.id_aluno = student;
		this.id_professor = teacher;
		this.setDuration(start, end);
	}

	//! Setters
	public void setDuration(Timestamp start, Timestamp end)
	{
		this.inicio = start;
		this.fim = end;

		int seconds = (int)( (end.getTime() - start.getTime())/1000 );
		this.custo = LigacaoPaga.calculateCost(seconds);
	}

	//! Getters
	public int getStudent(){ return this.id_aluno; }
	public int getTeacher(){ return this.id_professor; }
	public Timestamp getStart(){ return this.inicio; }
	public Timestamp getEnd(){ return this.fim; }
	public float getCost(){ return this.custo; }
	

	//! Calculate cost
	public static float costPerMin = 0.2f;
	public static float freeMinutes = 5f;

	public static float calculateCost(float duration) // Seconds
	{
		int cost = (int)(((duration/60 -freeMinutes) *costPerMin) *100);
		if(cost > 0)
			return (float)cost/100;
		return 0;
	}

	public static float timeLeft(float credits) // Seconds
	{
		return credits/costPerMin *60;
	}

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
		String sql = "INSERT INTO ligacao_paga " 
						+ "(id_aluno, id_professor, inicio, fim, custo) "
						+ "VALUES ('"
					+ this.id_aluno + "', '"
					+ this.id_professor + "', '"
					+ this.inicio + "', '"
					+ this.fim + "', '"
					+ this.custo + "')";

		db.execute(sql);
		db.close();
	}

	// Remove
	public void remove() 
	throws Exception 
	{
		Database db = new Database();
		String sql = "DELETE FROM ligacao_paga WHERE "
					+"id_aluno = '" + this.id_aluno + "' "
					+"AND id_professor = '" + this.id_professor + "'"
					+"AND inicio = '" + this.inicio + "'";

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
		String sql = "SELECT id_aluno, id_professor, inicio, fim, custo "
					+ "FROM ligacao_paga WHERE "
					+ "(("
						+"id_aluno = '" + id_1 + "'"
						+"AND id_professor = '" + id_2 + "'"
					+ ") OR ("
						+"id_aluno = '" + id_2 + "'"
						+"AND id_professor = '" + id_1 + "'"
					+ ")) ";

		if( newerThan != null )
		{
			sql += "AND inicio > '" + newerThan + "' ";
		}
		if( olderThan != null )
		{
			sql += "AND inicio < '" + olderThan + "' ";
		}

		sql += "ORDER BY inicio";

		return db.query(sql);
	}

	// Get Next user from ResultSet
	public static LigacaoPaga next(ResultSet rs) 
	throws Exception 
	{
		LigacaoPaga call = null;

		if( rs.next() )
		{
			int id_aluno 			= rs.getInt("id_aluno");
			int id_professor 		= rs.getInt("id_professor");
			Timestamp inicio 		= rs.getTimestamp("inicio");
			Timestamp fim 			= rs.getTimestamp("fim");
			float custo 			= rs.getFloat("custo");

			call = new LigacaoPaga(id_aluno, id_professor);

			call.inicio = inicio;
			call.fim = fim;
			call.custo = custo;
		}

		return call;
	}

	
	// To String
	public String toString()
	{
		return "From:       " + this.id_aluno + "\n" 
			+  "To:         " + this.id_professor + "\n" 
			+  "Started at: " + this.inicio + "\n"
			+  "Ended at:   " + this.fim + "\n"
			+  "Cost:       " + this.custo;
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
			Usuario b = Usuario.findByEmail("prof@yopmail.com");


			LigacaoPaga call = new LigacaoPaga(a.getID(), b.getID(),
									Timestamp.valueOf("2015-01-01 12:00:00.0"),
									Timestamp.valueOf("2015-01-01 12:15:00.0"));
			call.insert();

			ResultSet rs;

			call = new LigacaoPaga(a.getID(), b.getID(),
									Timestamp.valueOf("2015-01-01 12:30:00.0"),
									Timestamp.valueOf("2015-01-01 14:15:00.0"));
			call.insert();


			call = new LigacaoPaga(a.getID(), b.getID(),
									Timestamp.valueOf("2015-01-01 15:30:00.0"),
									Timestamp.valueOf("2015-01-01 16:15:00.0"));
			call.insert();




			LigacaoPaga m = null;

			rs = findByConversation(a.getID(), b.getID());
			while( (call = LigacaoPaga.next(rs)) != null )
			{
				m = call;
				System.out.println(call);
			}
			m.remove();

			rs = findByConversation(a.getID(), b.getID());
			while( (call = LigacaoPaga.next(rs)) != null )
			{
				System.out.println(call);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}

