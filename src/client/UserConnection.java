package client;

import java.io.*;
import java.net.*;
import java.util.*;

import client.*;
import client.requests.*;


public class UserConnection
{
	private Socket socket;
	private User user;
	

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

	public static void main(String args[])
	{
		// Arguments
		if(args.length < 2){
			System.out.println("usage: <email> <password>");
			System.exit(0);
		}

		try
		{
		//	String host = "maglethong.ddns.net";
			String host = "191.189.116.80";
			int port = 12377;

			UserConnection connection = 
						new UserConnection(new Socket(host, port));

			user = User.Authenticate(	this.networkReader,
										this.networkWriter,
										args[0], args[1]	);

			System.out.println(user);
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}
}