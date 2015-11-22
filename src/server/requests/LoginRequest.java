package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;

import server.requests.*;
import server.database.*;



public class LoginRequest implements UserRequest
{
	
	public void run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Reading credentials	
		String email = networkReader.nextLine();
		String password = networkReader.nextLine();

		if(user != null)
		{
			networkWriter.println( "Logout First!" );
			networkWriter.flush();
			return;
		}

		// Authenticating
		user = Usuario.authenticate(email, password);

		// Writing response
		if(user == null)
			networkWriter.println( "Wrong email or pass!" );
		else
		{
			networkWriter.println( "Success!" );
			networkWriter.println( user.getID() );
			networkWriter.println( user.getName() );
			networkWriter.println( user.getEmail() );
			networkWriter.println( user.getRank() );
			networkWriter.println( user.getRankCount() );
			networkWriter.println( user.getType() );
			networkWriter.println( user.getCredits() );
		}

		networkWriter.flush();
	}
	
}