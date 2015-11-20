package client;

import java.io.*;
import java.net.*;
import java.util.*;


public class User
{
	private Scanner networkReader;
	private PrintWriter networkWriter;

	private int 	id;
	private String 	name;
	private String 	email;
	private float 	rank;
	private int 	rank_count;
	private char 	type;
	private float 	credits;

	//! [Constructor]
	public User(Scanner networkReader, PrintWriter networkWriter)
	{
		this.networkReader = networkReader;
		this.networkWriter = networkWriter;
		this.id = -1;
		this.rank = -1;
		this.rank_count = -1;
		this.type = ' ';
		this.credits = 0;
	}
	public User(Scanner networkReader, PrintWriter networkWriter, int id)
	throws Exception
	{
		this.networkReader = networkReader;
		this.networkWriter = networkWriter;
		this.id = -1;
		this.rank = -1;
		this.rank_count = -1;
		this.type = ' ';
		this.credits = 0;

		this.refreshUserInformation(id);
	}

	// Getters
	public int 		getID(){ 		return this.id; }
	public String 	getName(){ 		return this.name; }
	public String 	getEmail(){ 	return this.email; }
	public float 	getRank(){ 		return this.rank; }
	public int 		getRankCount(){ return this.rank_count; }
	public char		getType(){ 		return this.type; }
	public float	getCredits(){ 	return this.credits; }

	// Setters
	public void setName(String name){ this.name = name; }
	public void setEmail(String email){ this.email = email; }
	
	// To String
	public String toString()
	{
		return "ID:      " + this.id + "\n"
			 + "Name:    " + this.name + "\n"
			 + "E-mail:  " + this.email + "\n"
			 + "Rank:    " + this.rank + "(" + this.rank_count + ") \n"
			 + "Type:    " + this.type + "\n"
			 + "Credits: " + this.credits + "\n";
	}
	
	// --------------------------------------------------------------------- //
	//                                                                       //
	//                           Network Operations                          //
	//                                                                       //
	// --------------------------------------------------------------------- //

	// Register // TODO

	// Login
	public static User Authenticate( 	Scanner networkReader,
										PrintWriter networkWriter,
										String email,
										String password			)
	throws Exception
	{
		User ret = new User(networkReader, networkWriter);
		
		// Send Credentials
		networkWriter.println( "LoginRequest" );
		networkWriter.println( email );
		networkWriter.println( password );
		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{
			ret.id 			= Integer.parseInt(networkReader.nextLine());
			ret.name 		= networkReader.nextLine();
			ret.email 		= networkReader.nextLine();
			ret.rank 		= Float.parseFloat(networkReader.nextLine());
			ret.rank_count 	= Integer.parseInt(networkReader.nextLine());
			ret.type 		= networkReader.nextLine().charAt(0);
			ret.credits		= Float.parseFloat(networkReader.nextLine());
		}
		else if( result.equals("Wrong email or pass!") )
		{
			// TODO [Exception]
			System.out.println(result);
		}
		else if( result.equals("Logout First!") )
		{
			// TODO [Exception]
			System.out.println(result);
		}
		else
		{
			// TODO [Exception]
			System.out.println(result);
		}

		return ret;
	}

	// Refresh user information
	public void refreshUserInformation(int id)
	{
		// Send Credentials
		networkWriter.println( "UserInformationRequest" );
		networkWriter.println( id );
		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{
			this.id 			= Integer.parseInt(networkReader.nextLine());
			this.name 			= networkReader.nextLine();
			this.email 			= networkReader.nextLine();
			this.rank 			= Float.parseFloat(networkReader.nextLine());
			this.rank_count 	= Integer.parseInt(networkReader.nextLine());
			this.type 			= networkReader.nextLine().charAt(0);
			this.credits		= Float.parseFloat(networkReader.nextLine());
		}
		else if( result.equals("Not Found!") )
		{
			// TODO [Exception]
			System.out.println(result);
		}
		else
		{
			// TODO [Exception]
			System.out.println(result);
		}
	}

	// Refresh user languages // TODO
	// Refresh user contacts // TODO
}