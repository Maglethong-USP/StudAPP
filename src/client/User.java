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
	private ArrayList<String> languageList;


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
		this.languageList = null;
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
		this.languageList = null;

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
	public String[] 	getLanguages()
	{
		if(this.languageList == null)
		{
			this.refreshLanguageList();
		}

		return this.languageList.toArray(new String[1]);
	}

	// Setters
	public void setName(String name){ 	this.name = name; }
	public void setEmail(String email){ this.email = email; }
	
	// To String
	public String toString()
	{
		String ret = "ID:      " + this.id + "\n"
			 + "Name:     " + this.name + "\n"
			 + "E-mail:   " + this.email + "\n"
			 + "Rank:     " + this.rank + "(" + this.rank_count + ") \n"
			 + "Type:     " + this.type + "\n"
			 + "Credits:  " + this.credits + "\n"
			 + "Contacts:\n";
		if(this.contactList != null)
			for(int i=0; i<this.contactList.size(); i++)
				ret += "    - " + this.contactList.get(i) + "\n";
		ret += "Languages:\n";
		if(this.languageList != null)
			for(int i=0; i<this.languageList.size(); i++)
				ret += "    - " + this.languageList.get(i) + "\n";

		return ret;	 
	}
	
	// --------------------------------------------------------------------- //
	//                                                                       //
	//                           Network Operations                          //
	//                                                                       //
	// --------------------------------------------------------------------- //

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
			this.id 			= id;
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

	// Refresh user languages
	public void refreshLanguageList()
	{
		// Send Credentials
		networkWriter.println( "UserLanguageRequest" );
		networkWriter.println( this.id );
		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{
			this.languageList = new ArrayList<String>();

			while(true)
			{
				String lang = networkReader.nextLine();
				if(lang.length() == 0) break;
				this.languageList.add( lang );
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

	// Refresh Credit Amount [just the total amount] // TODO [test]
	public void refreshCreditsAmount()
	{
		// Send Credentials
		networkWriter.println( "UserCreditsAmountRequest" );
		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{
			this.credits = Float.parseFloat(networkReader.nextLine());
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

	// Refresh Credit Information [log] // TODO []

	// Refresh Message Information // TODO []

	// Send message // TODO []

	// Register
	public static void createNewAccount( 	Scanner networkReader,
											PrintWriter networkWriter,
											String email,
											String password			)
	throws Exception
	{
		// Send Request
		networkWriter.println( "RegisterRequest" );
		networkWriter.println( email );
		networkWriter.println( password );
		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{
			
		}
		else if( result.equals("E-mail in Use!") )
		{
			// TODO [Exception]
			System.out.println(result);
		}
		else if( result.equals("Invalid Password!") )
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

	// Change Password
	public void changePassword(String curPasswd, String newPasswd)
	{
		// Send Request
		networkWriter.println( "ChangePasswordRequest" );
		networkWriter.println( curPasswd );
		networkWriter.println( newPasswd );
		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{
			
		}
		else if( result.equals("Not Logged In!") )
		{
			// TODO [Exception]
			System.out.println(result);
		}
		else if( result.equals("Wrong Password!") )
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

	// Change Profile info
	public void sendNewProfile()
	{
		// Send Request
		networkWriter.println( "UpdateProfileRequest" );
		networkWriter.println( this.name );
		networkWriter.println( this.email );
		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{
			
		}
		else if( result.equals("Not Logged In!") )
		{
			// TODO [Exception]
			System.out.println(result);
		}
		else if( result.equals("Wrong Password!") )
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

	// Add contact
	public void addContact(int id)
	{
		// Send Request
		networkWriter.println( "AddContactRequest" );
		networkWriter.println( id );
		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{

		}
		else if( result.equals("Not Found!") )
		{
			// TODO [Exception]
			System.out.println(result);
		}
		else if( result.equals("Already Exists!") )
		{
			// TODO [Exception]
			System.out.println(result);
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

	// Remove Contact // TODO [Test]
	public void removeContact(int id)
	{
		// Send Request
		networkWriter.println( "RemoveContactRequest" );
		networkWriter.println( id );
		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{

		}
		else if( result.equals("Not Found!") )
		{
			// TODO [Exception]
			System.out.println(result);
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

	// Refresh user contacts
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