package server;

import java.io.*;
import java.util.*;

import javax.net.ssl.*;

import server.requests.*;
import server.database.*;


public class UserConnection implements Runnable
{
	private SSLSocket sslSocket;
	private Usuario user;
	

	// Network read/writer
	private Scanner networkReader;
	private PrintWriter networkWriter;
		

	// Constructor
	public UserConnection(SSLSocket sslSocket)
	throws Exception
	{ 
		this.sslSocket = sslSocket; 
		this.user = null;

		this.networkReader = new Scanner(this.sslSocket.getInputStream());
		this.networkWriter = new PrintWriter(this.sslSocket.getOutputStream());
	}

	// Overwrite run
	public void run()
	{
		
		try
		{
			// User requesting an operation
			String operation = networkReader.nextLine();	
			try
			{
				Class theClass = Class.forName("server.requests." + operation); 
				UserRequest req = (UserRequest)	theClass.newInstance();
				req.run(this.networkReader, this.networkWriter, this.user);

			}
			// Operation was not implemented
			catch(ClassNotFoundException cnfe)
			{	
				networkWriter.println("unknown_command");
			}
			// Some other error ocured
			catch(Exception e)
			{
				networkWriter.println("unknown_error");
			
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}

	}
	
}