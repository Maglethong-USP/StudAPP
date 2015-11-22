package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

import server.requests.*;
import server.database.*;



// TODO [test]
public class UserContactsRequest implements UserRequest
{
	public Usuario run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Writing response
		if(user == null)
			networkWriter.println( "Not Logged In!" );
		else
		{
			networkWriter.println( "Success!" );

			ResultSet rs = user.getContactList();

			Usuario tmp = Usuario.next(rs);
			while(tmp != null)
			{
				networkWriter.println( tmp.getID() );
				if( tmp.getName() == null || tmp.getName().length() == 0 )
					networkWriter.println( tmp.getEmail() );
				else
					networkWriter.println( tmp.getName() );

				tmp = Usuario.next(rs);
			}
			networkWriter.println( "-1" );

		}

		networkWriter.flush();
		return user;
	}
	
}