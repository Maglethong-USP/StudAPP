package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

import server.requests.*;
import server.database.*;



public class RecvMessageCountRequest implements UserRequest
{
	public Usuario run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception 
	{	
		// Reading inputs
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
				MsgCount[] mc = Mensagem.getMessageCount(user.getID(), 
														newerThan, olderThan);

				networkWriter.println( "Success!" );

				if(mc == null)
					networkWriter.println( 0 );
				else
				{
					networkWriter.println( mc.length );

					for(int i=0; i<mc.length; i++)
					{
						networkWriter.println( mc[i].originId );
						networkWriter.println( mc[i].msgCount );
					}
				}
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