package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;

import server.requests.*;
import server.database.*;



// TODO [test]
public class UserCreditsAmountRequest implements UserRequest
{
	public Usuario run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Writing response
		if(user == null)
			networkWriter.println( "Not Logged In!" );
		else
		{
			float credits = Usuario.getCredits( user.getID() );
			networkWriter.println( "Success!" );
			networkWriter.println( credits );
		}

		networkWriter.flush();
		return user;
	}
	
}