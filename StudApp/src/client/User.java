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
			return null;

		return this.contactList.toArray(new Contact[1]);
	}
	public String[] 	getLanguages()
	{
		if(this.languageList == null)
			return null;

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

	//! Login
	/*!
		Possible Exceptions:
		* "Wrong email or pass!"
		* "Logout First!"
		* other
	*/
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
		else
		{
			throw new StudAppException(result);
		}

		return ret;
	}

	// Refresh user information [Does not refresh credits]
	/*!
		Possible Exceptions:
		* "Not Found!"
		* other
	*/
	public void refreshUserInformation()
	throws Exception
	{
		this.refreshUserInformation(this.id);
	}
	public void refreshUserInformation(int id)
	throws Exception
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
		else
		{
			throw new StudAppException(result);
		}
	}

	// Refresh user languages
	/*!
		Possible Exceptions:
		* "Not Logged In!"
		* other
	*/
	public void refreshLanguageList()
	throws Exception
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
		else
		{
			throw new StudAppException(result);
		}
	}

	// Refresh Credit Amount [just the total amount]
	/*!
		Possible Exceptions:
		* "Not Logged In!"
		* other
	*/
	public void refreshCreditsAmount()
	throws Exception
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
		else
		{
			throw new StudAppException(result);
		}
	}

	// Refresh Credit Information [log] // TODO []

	// Send message
	/*!
		Possible Exceptions:
		* "Not Logged In!"
		* other
	*/
	public void sendMessage(Message msg)
	throws Exception
	{
		// Send Request
		networkWriter.println( "SendMessageRequest" );
		networkWriter.println( msg.getDestination() );
		networkWriter.println( msg.getContent().length() );
		networkWriter.println( msg.getContent() );
		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{
			
		}
		else
		{
			throw new StudAppException(result);
		}
	}

	// Receive messages
	/*!
		Possible Exceptions:
		* "Not Logged In!"
		* other
	*/
	public Message[] recvMessages(Date newerThan, Date olderThan)
	throws Exception
	{
		ArrayList<Message> msgList = new ArrayList<Message>();

		// Send Request
		networkWriter.println( "RecvMessageRequest" );
		if(newerThan != null)
			networkWriter.println( newerThan.getTime() );
		else
			networkWriter.println( 0 );

		if(olderThan != null)
			networkWriter.println( olderThan.getTime() );
		else
			networkWriter.println( 0 );

		networkWriter.flush();

		// Read response
		String result = networkReader.nextLine();
		if( result.equals("Success!") )
		{
			while(true)
			{
				String firstLine = networkReader.nextLine();
				if(firstLine.length() == 0)
					break;

				int sender = Integer.parseInt(firstLine);
				int receiver = Integer.parseInt(networkReader.nextLine());
				int contentLen = Integer.parseInt(networkReader.nextLine());
				String content = "";
				do
				{ 
					content += networkReader.nextLine() + "\n"; 
				}
				while(content.length() < contentLen);
				content = content.substring(0, contentLen -1);
				Date sentAt = new Date( Long.parseLong(networkReader.nextLine()) );

				msgList.add( new Message(sender, receiver, content, sentAt) );
			}
		}
		else
		{
			throw new StudAppException(result);
		}

		return msgList.toArray(new Message[1]);
	}

	// Register
	/*!
		Possible Exceptions:
		* "E-mail in Use!"
		* "Invalid Password!"
		* other
	*/
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
		else
		{
			throw new StudAppException(result);
		}
	}

	// Change Password

	// Register
	/*!
		Possible Exceptions:
		* "Not Logged In!"
		* "Wrong Password!"
		* other
	*/
	public void changePassword(String curPasswd, String newPasswd)
	throws Exception
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
		else
		{
			throw new StudAppException(result);
		}
	}

	// Change Profile info
	/*!
		Possible Exceptions:
		* "Not Logged In!"
		* "Wrong Password!"
		* other
	*/
	public void sendNewProfile()
	throws Exception
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
		else
		{
			throw new StudAppException(result);
		}
	}

	// Add contact
	/*!
		Possible Exceptions:
		* "Not Found!"
		* "Not Logged In!"
		* "Already Exists!"
		* other
	*/
	public void addContact(int id)
	throws Exception
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
		else
		{
			throw new StudAppException(result);
		}
	}

	// Remove Contact
	/*!
		Possible Exceptions:
		* "Not Found!"
		* "Not Logged In!"
		* other
	*/
	public void removeContact(int id)
	throws Exception
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
		else
		{
			throw new StudAppException(result);
		}
	}

	// Refresh user contacts
	/*!
		Possible Exceptions:
		* "Not Logged In!"
		* other
	*/
	public void refreshContactList()
	throws Exception
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
		else
		{
			throw new StudAppException(result);
		}
	}
}