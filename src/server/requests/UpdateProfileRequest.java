package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;

import server.requests.*;
import server.database.*;



public class UpdateProfileRequest implements UserRequest
{
	public Usuario run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Reading inputs
		String name = networkReader.nextLine();
		String email = networkReader.nextLine();

		// Writing response
		if(user == null)
			networkWriter.println( "Not Logged In!" );
		else
		{
			user.setName(name);
			user.setEmail(email);
			try
			{
				user.update();
				networkWriter.println( "Success!" );
			}
			catch(Exception e)
			{
				networkWriter.println( "SQL exception [untreated]. Possible: e-mail in use!" );
			}
		}

		networkWriter.flush();
		return user;
	}
	
}