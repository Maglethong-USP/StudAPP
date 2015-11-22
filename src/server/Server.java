package server;

import java.io.*;
import java.util.*;
import java.security.Security;
import java.security.PrivilegedActionException;

import javax.net.ssl.*;
import com.sun.net.ssl.internal.ssl.Provider;

import server.database.*;


public class Server{
	private SSLServerSocket sslServer;
	
	//! [Constructor]
	public Server(int port) 
	throws Exception
	{
            
            Security.addProvider(new Provider());
            
            System.setProperty("javax.net.ssl.keyStore","foobar");
            System.setProperty("javax.net.ssl.keyStorePassword","foobar");
            
            SSLServerSocketFactory sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
            this.sslServer = (SSLServerSocket)sslServerSocketfactory.createServerSocket(port);
	}

	//! Main execution loop
	public void run()
	{
		while(true)
		{
			try
			{
				Thread userConnection = 
						new Thread(new UserConnection( (SSLSocket)this.sslServer.accept() ));
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
                            PrivilegedActionException priexp = new PrivilegedActionException(e);
                            System.out.println(" Priv act exception --- " + priexp.getMessage());
                            e.printStackTrace();	
			}
		}
		else
		{
			System.out.println("Give me a port to run at!!!");
		}

	}
	
}


