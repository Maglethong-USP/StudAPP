package server;

import java.io.*;
import java.net.*;
import java.util.*;


import server.requests.*;
import server.database.*;


public class UserConnection implements Runnable
{
	private Socket socket;
	private Usuario user;
	

	// Network read/writer
	private Scanner networkReader;
	private PrintWriter networkWriter;
		

	// Constructor
	public UserConnection(Socket socket)
	throws Exception
	{ 
		this.socket = socket; 
		this.user = null;

		this.networkReader = new Scanner(this.socket.getInputStream());
		this.networkWriter = new PrintWriter(this.socket.getOutputStream());
	}

	// Overwrite run
	public void run()
	{
		try
		{
			while(true)
			{
				// User requesting an operation
				String operation = networkReader.nextLine();
System.out.println("Received operation: " + operation);	
				try
				{
					Class theClass = Class.forName("server.requests." + operation); 
					UserRequest req = (UserRequest)	theClass.newInstance();
					this.user = req.run(this.networkReader, this.networkWriter, this.user);
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

		}
		catch(NoSuchElementException nsle)
		{
System.out.println("Connection Closed!\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}
	
}