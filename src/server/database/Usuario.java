package server.database;

import java.sql.*;
import java.util.*;

import server.database.*;


public class Usuario
{
	// Database fields
	private int id;
	private String nome;
	private String email;
	private String senha;
	private float rank;
	private int rank_count;
	private char tipo;
	private float creditos;

	//! [Constructor]
	public Usuario(String name, String email, String password, char type)
	{
		this.id = -1;
		this.nome = name;
		this.email = email;
		this.senha = password; // TODO Hash password
		this.rank = -1;
		this.rank_count = -1;
		this.tipo = type;
		this.creditos = -1;
	}
	public Usuario(String name, String email, String password)
	{
		this.id = -1;
		this.nome = name;
		this.email = email;
		this.senha = password; // TODO Hash password
		this.rank = -1;
		this.rank_count = -1;
		this.tipo = 0;
		this.creditos = -1;
	}

	// Setters
	private void setName(String name)
	{
		this.nome = name;	
	}
	private void setEmail(String email)
	{
		this.email = email;	
	}
	public void setPassword(String password)
	{
		this.senha = password;	
	}

	//! Getters
	public int getID(){ return this.id; }
	public String getName(){ return this.nome; }
	public String getEmail(){ return this.email; }
	public float getRank(){ return this.rank; }
	public int getRankCount(){ return this.rank_count; }
	public char getType(){ return this.tipo; }
	public float getCredits(){ return this.creditos; }
	
	// Verify password
	public boolean verifyPassword(String password)
	{
		return password.equals(this.senha); // TODO Hash password
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
		String sql;

		if(this.tipo == 0)
		{
			sql = "INSERT INTO usuario (nome, email, senha) VALUES ('"
					+ this.nome + "', '"
					+ this.email + "', '"
					+ this.senha + "')";
		}
		else
		{
			sql = "INSERT INTO usuario (nome, email, senha, tipo) VALUES ('"
					+ this.nome + "', '"
					+ this.email + "', '"
					+ this.senha + "', '"
					+ this.tipo + "')";
		}

		db.execute(sql);
		db.close();
	}

	// Update
	public void update()
	throws Exception
	{
		Database db = new Database();
		String sql = "UPDATE usuario SET "
						+ "nome = '" + this.nome + "', "
						+ "senha = '" + this.senha + "', "
						+ "tipo = '" + this.tipo + "' "
					+ "WHERE id = '" + this.id + "'";

		db.execute(sql);
		db.close();
	}

	// Remove
	public void remove() 
	throws Exception 
	{
		Database db = new Database();
		String sql = "DELETE FROM usuario WHERE id = '" + this.id + "'";

		db.execute(sql);
		db.close();
	}

	// Finders
	public static Usuario findByPrimaryKey(int id)
	throws Exception 
	{
		Database db = new Database();
		String sql = "SELECT id, nome, email, senha, rank, rank_count, tipo "
					+ "FROM usuario "
					+ "WHERE id = '" + id + "'";

		ResultSet rs = db.query(sql);

		Usuario user = Usuario.next(rs);
		db.close();
		return user;
	}

	public static Usuario findByEmail(String email)
	throws Exception 
	{
		Database db = new Database();
		String sql = "SELECT id, nome, email, senha, rank, rank_count, tipo "
					+ "FROM usuario "
					+ "WHERE email = '" + email + "'";

		ResultSet rs = db.query(sql);

		Usuario user = Usuario.next(rs);
		db.close();
		return user;
	}

	public static ResultSet findByName(String name) 
	throws Exception
	{
		Database db = new Database();
		String sql = "SELECT id, nome, email, senha, rank, rank_count, tipo "
					+ "FROM usuario "
					+ "WHERE UPPER(nome) LIKE UPPER('%" + name + "%')";

		return db.query(sql);
	}

	// Get Next user from ResultSet
	public static Usuario next(ResultSet rs) 
	throws Exception 
	{
		Usuario user = null;

		if( rs.next() )
		{
			int id 			= rs.getInt("id");
			String nome 	= rs.getString("nome");
			String email 	= rs.getString("email");
			String senha 	= rs.getString("senha");
			float rank 		= rs.getFloat("rank");
			int rank_count 	= rs.getInt("rank_count");
			String tipo_s 	= rs.getString("tipo"); // TODO test it
			char tipo 		= tipo_s.length() > 0 ? tipo_s.charAt(0) : 0;

			user = new Usuario(nome, email, senha, tipo);
			user.id = id;
			user.rank = rank;
			user.rank_count = rank_count;
		}

		return user;
	}

	// Get contact list
	public ResultSet getContactList() 
	throws Exception 
	{
		Database db = new Database();
		String sql = "SELECT id, nome, email, senha, rank, rank_count, tipo "
					+ "FROM usuario "
					+ "JOIN contato "
						+ "ON (contato.id_contato = usuario.id)"
					+ "WHERE contato.id_usuario = '" + this.id + "'";

		return db.query(sql);
	}

	// Authenticator
	public static Usuario authenticate(String email, String password) 
	throws Exception
	{
		Usuario user;
		
		// Check e-mail
		user = findByEmail(email);
		if(user == null)
		{
			return null;
		}
		// Check password
		if( !user.verifyPassword(password) )
		{
			return null;
		}

		// Get type specific
		if(user.tipo == 'A')
		{
			Database db = new Database();
			String sql = "SELECT creditos FROM aluno "
						+ "WHERE id = '" + user.id + "'";
			ResultSet rs = db.query(sql);
			if( rs.next() )
			{
				user.creditos = rs.getFloat("creditos");
			}
		}
		else if(user.tipo == 'P')
		{

		}

		return user;
	}


	// Add new studying language
	public void insertLanguage(String language)
	throws Exception
	{
		Database db = new Database();
		String sql;

		sql = "INSERT INTO estuda (id, lingua) VALUES ('"
				+ this.id + "', '"
				+ language + "')";

		db.execute(sql);
		db.close();
	}

	// Remove studying language
	public void removeLanguage(String language) 
	throws Exception 
	{
		Database db = new Database();
		String sql = "DELETE FROM estuda WHERE " 
					+ "id = '" + this.id + "' " 
					+ "AND lingua = '" + language + "' " ;

		db.execute(sql);
		db.close();
	}

	// Get studying language list
	public static String[] getStudyingList(int id)
	throws Exception
	{
		ArrayList<String> ret = new ArrayList<String>();

		Database db = new Database();
		String sql = "SELECT lingua FROM estuda WHERE " 
					+ "id = '" + id + "'";

		ResultSet rs = db.query(sql);

		while( rs.next() )
		{
			ret.add(rs.getString("lingua"));
		}

		return ret.toArray(new String[1]);
	}

	public static float getCredits(int id)
	throws Exception
	{
		Database db = new Database();
		String sql = "SELECT creditos FROM aluno "
					+ "WHERE id = '" + id + "'";
		ResultSet rs = db.query(sql);
		if( rs.next() )
		{
			return rs.getFloat("creditos");
		}

		return 0;
	}
	
	// To String
	public String toString()
	{
		return this.id + ", " + this.nome + ", " + this.email + ", " + this.tipo + ", " + this.creditos;
	}

	// Unit test
	public static void main(String args[]){
		// Configure Database
		Database.url = "jdbc:postgresql://localhost:5432/studapp";
		Database.username = "studapp";
		Database.password = "studapp";

	
		try
		{
			Usuario old = Usuario.findByEmail("fulando@yopmail.com");;
			if(old != null)
				old.remove();

			Usuario user = new Usuario("Fulano", "fulando@yopmail.com", "qwerty", 'A');
			user.insert();
			user = Usuario.findByEmail("fulando@yopmail.com");
			user.setName("Fulano Ciclano");
			user.update();

			user = Usuario.authenticate("andy@yopmail.com", "qwerty");
			System.out.println(user);

			user = Usuario.next(Usuario.findByName("Fulano Ciclano"));
			System.out.println(user);

			user.getContactList();

			user.remove();

			user = Usuario.findByEmail("andy@yopmail.com");
			user.insertLanguage("Portugues");
			user.removeLanguage("Ingles");
			String[] lang = Usuario.getStudyingList(user.getID());
			for(int i=0; i<lang.length; i++)
			{
				System.out.println(lang[i]);
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}

