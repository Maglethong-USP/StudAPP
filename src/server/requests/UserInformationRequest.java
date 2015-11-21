package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;

import server.requests.*;
import server.database.*;



public class UserInformationRequest implements UserRequest
{
	public Usuario run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Reading inputs	
		int id = Integer.parseInt(networkReader.nextLine());

		// Authenticating
		Usuario other = Usuario.findByPrimaryKey(id);

		// Writing response
		if(other == null)
			networkWriter.println( "Not Found!" );
		else
		{
			networkWriter.println( "Success!" );
			networkWriter.println( other.getName() );
			networkWriter.println( other.getEmail() );
			networkWriter.println( other.getRank() );
			networkWriter.println( other.getRankCount() );
			networkWriter.println( other.getType() );
		}

		networkWriter.flush();
		return user;
	}
	
}