package server;

import java.io.*;
import java.net.*;
import java.util.*;

import server.database.*;


public class Server{
	private ServerSocket server;
	
	//! [Constructor]
	public Server(int port) 
	throws Exception
	{
		this.server = new ServerSocket(port);
	}

	//! Main execution loop
	public void run()
	{
		while(true)
		{
			try
			{
				Thread userConnection = 
						new Thread(new UserConnection(this.server.accept()));
				userConnection.start();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}	
		}
	}

	//! [Main]
	public static void main(String args[])
	{
		
		// Configuring database
		Database.url = "jdbc:postgresql://localhost:5432/studapp";
		Database.username = "studapp";
		Database.password = "studapp";

		if(args.length > 0)
		{
			try
			{
				Server server = new Server( Integer.parseInt(args[0]) );
				System.out.println("Server Running!");
				server.run();
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
		}
		else
		{
			System.out.println("Give me a port to run at!!!");
		}

	}
	
}


