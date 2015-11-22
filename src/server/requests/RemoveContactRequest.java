package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;

import server.requests.*;
import server.database.*;



public class RemoveContactRequest implements UserRequest
{
	public Usuario run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Reading inputs
		int id = Integer.parseInt(networkReader.nextLine());

		// Writing response
		if(user == null)
			networkWriter.println( "Not Logged In!" );
		else
		{
			try
			{
				user.removeContact(id);
				networkWriter.println( "Success!" );
			}
			catch(Exception e)
			{
				networkWriter.println( "SQL error [untreated. Possible: fk fail, reinsert, other]!" );
				e.printStackTrace();
			}
		}

		networkWriter.flush();
		return user;
	}
	
}