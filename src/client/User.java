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

	private ArrayList<Contact> contactList;


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

		this.contactList = null;
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

		this.contactList = null;

		this.refreshUserInformation(id);
	}

	// Getters
	public int 			getID(){ 		return this.id; }
	public String 		getName(){ 		return this.name; }
	public String 		getEmail(){ 	return this.email; }
	public float 		getRank(){ 		return this.rank; }
	public int 			getRankCount(){ return this.rank_count; }
	public char			getType(){ 		return this.type; }
	public float		getCredits(){ 	return this.credits; }
	public Contact[] 	getContacts()
	{
		if(this.contactList == null)
		{
			this.refreshContactList();
		}

		return this.contactList.toArray(new Contact[1]);
	}

	// Setters
	public void setName(String name){ 	this.name = name; }
	public void setEmail(String email){ this.email = email; }
	
	// To String
	public String toString()
	{
		String ret = "ID:      " + this.id + "\n"
			 + "Name:    " + this.name + "\n"
			 + "E-mail:  " + this.email + "\n"
			 + "Rank:    " + this.rank + "(" + this.rank_count + ") \n"
			 + "Type:    " + this.type + "\n"
			 + "Credits: " + this.credits + "\n"
			 + "Contacts:\n";

		for(int i=0; i<this.contactList.size(); i++)
		{
			ret += "\t - " + this.contactList.get(i) + "\n";
		}

		return ret;	 
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

	// Refresh user information [Does not refresh credits]
	public void refreshUserInformation()
	{
		this.refreshUserInformation(this.id);
	}
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
			this.id 			= id
			this.name 			= networkReader.nextLine();
			this.email 			= networkReader.nextLine();
			this.rank 			= Float.parseFloat(networkReader.nextLine());
			this.rank_count 	= Integer.parseInt(networkReader.nextLine());
			this.type 			= networkReader.nextLine().charAt(0);
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

	// Refresh Credit Amount [just the total amount] // TODO []

	// Refresh Credit Information [log] // TODO []

	// Refresh Message Information // TODO []


	// Refresh user contacts // TODO
	public void refreshContactList()
	{
		// Send Credentials
		networkWriter.println( "UserContactsRequest" );
		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{
			this.contactList = new ArrayList<Contact>();

			while(true)
			{
				int id 		= Integer.parseInt(networkReader.nextLine());
				if(id < 0) break;
				String name = networkReader.nextLine();
				this.contactList.add( new Contact(id, name) );
			}
		}
		else if( result.equals("Not Logged In!") )
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
}