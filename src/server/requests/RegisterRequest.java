package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;

import server.requests.*;
import server.database.*;



public class RegisterRequest implements UserRequest
{
	public Usuario run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Reading inputs
		String email = networkReader.nextLine();
		String password = networkReader.nextLine();

		// Writing response
		try
		{
			Usuario newUser = new Usuario(null, email, password);
			newUser.insert();
			networkWriter.println( "Success!" );
		}
		catch(Exception e)
		{
			networkWriter.println( "SQL exception [untreated]. Possible: e-mail in use!" );
		}

		networkWriter.flush();
		return user;
	}
	
}