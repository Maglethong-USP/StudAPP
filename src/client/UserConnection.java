package client;

import java.io.*;
import java.net.*;
import java.util.*;


public class UserConnection
{
	private Socket socket;
	//private Usuario user;
	

	// Network read/writer
	private Scanner networkReader;
	private PrintWriter networkWriter;
		

	// Constructor
	public UserConnection(Socket socket)
	throws Exception
	{ 
		this.socket = socket; 
	//	this.user = null;

		this.networkReader = new Scanner(this.socket.getInputStream());
		this.networkWriter = new PrintWriter(this.socket.getOutputStream());
	}

	// Overwrite run
	public void run(String email, String password)
	{
		
		try
		{
			networkWriter.println( "LoginRequest" );
			networkWriter.println( email );
			networkWriter.println( password );
			networkWriter.flush();

			String result = networkReader.nextLine();
			System.out.println(result);

		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}

	}
	
	public static void main(String args[])
	{
		// Arguments
		if(args.length < 4){
			System.out.println("usage: <email> <password> <host> <port>");
			System.exit(0);
		}

		try
		{
			String host = args[2];
			int port = Integer.parseInt(args[3]);
			UserConnection connection = 
						new UserConnection(new Socket(host, port));

			connection.run(args[0], args[1]);
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}
}