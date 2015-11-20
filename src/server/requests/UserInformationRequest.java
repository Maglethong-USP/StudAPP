package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;

import server.requests.*;
import server.database.*;



public class UserInformationRequest implements UserRequest
{
	
	public void run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Reading inputs	
		int id = Integer.parseInt(networkReader.nextLine());

		// Authenticating
		user = Usuario.findByPrimaryKey(id);

		// Writing response
		if(user == null)
			networkWriter.println( "Not Found!" );
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