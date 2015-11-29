package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

import server.requests.*;
import server.database.*;



public class RecvMessageRequest implements UserRequest
{
	public Usuario run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Reading inputs
		int contact_id = Integer.parseInt( networkReader.nextLine() );
		Long s_newerThan = Long.parseLong( networkReader.nextLine() );
		Long s_olderThan = Long.parseLong( networkReader.nextLine() );

		Timestamp newerThan = null;
		Timestamp olderThan = null;

		if( s_newerThan > 0 )
			newerThan = new Timestamp( s_newerThan );
		if( s_olderThan > 0 )
			olderThan = new Timestamp( s_olderThan );

		// Writing response
		if(user == null)
			networkWriter.println( "Not Logged In!" );
		else
		{
			try
			{
				ResultSet rs = Mensagem.findByConversation(user.getID(), contact_id,
														newerThan, olderThan);

				networkWriter.println( "Success!" );

				Mensagem msg = Mensagem.next(rs);
				while(msg != null)
				{
					networkWriter.println( msg.getSender() );
					networkWriter.println( msg.getReceiver() );
					networkWriter.println( msg.getContent().length() );
					networkWriter.println( msg.getContent() );
					networkWriter.println( msg.getDate().getTime() );
					msg = Mensagem.next(rs);
				}

				networkWriter.println("");
			}
			catch(Exception e)
			{
				networkWriter.println( "SQL error [untreated]!" );
				e.printStackTrace();
			}
		}

		networkWriter.flush();
		return user;
	}
	
}