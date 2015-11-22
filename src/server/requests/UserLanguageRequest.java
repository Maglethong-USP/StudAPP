package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;

import server.requests.*;
import server.database.*;



public class UserLanguageRequest implements UserRequest
{
	public Usuario run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Reading inputs	
		int id = Integer.parseInt(networkReader.nextLine());

		String[] languages = Usuario.getStudyingList(id);

		networkWriter.println( "Success!" );
		for(int i=0; i<languages.length; i++)
		{
			networkWriter.println( languages[i] );
		}
		networkWriter.println( "" );

		networkWriter.flush();
		return user;
	}
	
}