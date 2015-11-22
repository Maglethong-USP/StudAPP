package client;

import java.io.*;
import java.util.*;

import javax.net.ssl.*;
import com.sun.net.ssl.internal.ssl.Provider;
import java.security.Security;

import client.*;


public class UserConnection
{
	private SSLSocket sslSocket;
	private User user;
	

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
		//	String host = "191.189.116.80";
			String host = "localhost";
			int port = 12377;
                        
                        System.setProperty("javax.net.ssl.trustStore","foobar");
                        System.setProperty("javax.net.ssl.trustStorePassword", "foobar");
                        
                        Security.addProvider(new Provider());
                        SSLSocketFactory sslsocketfactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
                        UserConnection connection = new UserConnection((SSLSocket)sslsocketfactory.createSocket(host,port));
                        connection.user = User.Authenticate(connection.networkReader,connection.networkWriter,args[0], args[1]);
                        System.out.println(connection.user);
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}
}
