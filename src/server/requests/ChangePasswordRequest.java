package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;

import server.requests.*;
import server.database.*;



public class ChangePasswordRequest implements UserRequest
{
	public Usuario run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Reading inputs
		String oldPass = networkReader.nextLine();
		String newPass = networkReader.nextLine();

		// Writing response
		if(user == null)
			networkWriter.println( "Not Logged In!" );
		else
		{
			if(user.verifyPassword(oldPass))
			{
				user.setPassword(newPass);
				user.update();
				networkWriter.println( "Success!" );
			}
			else
			{
				networkWriter.println( "Wrong Password!" );
			}
		}

		networkWriter.flush();
		return user;
	}
	
}