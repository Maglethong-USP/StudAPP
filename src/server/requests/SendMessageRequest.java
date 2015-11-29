package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;

import server.requests.*;
import server.database.*;



public class SendMessageRequest implements UserRequest
{
	public Usuario run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Reading inputs
		int destination = Integer.parseInt(networkReader.nextLine());
		int contentLen = Integer.parseInt(networkReader.nextLine());
		String content = "";

		do
		{ 
			content += networkReader.nextLine() + "\n"; 
		}
		while(content.length() < contentLen);
		content = content.substring(0, contentLen -1);

		// Writing response
		if(user == null)
			networkWriter.println( "Not Logged In!" );
		else
		{
			try
			{
				Mensagem msg = new Mensagem(user.getID(), destination, content);
				msg.insert();
				networkWriter.println( "Success!" );
			}
			catch(Exception e)
			{
				networkWriter.println( "SQL error [untreated. Possible: fk fail, other]!" );
				e.printStackTrace();
			}
		}

		networkWriter.flush();
		return user;
	}
	
}