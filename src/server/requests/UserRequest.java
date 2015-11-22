package server.requests;

import java.io.*;
import java.net.*;
import java.util.*;

import server.database.*;

public interface UserRequest
{
	public void run(Scanner networkReader, PrintWriter networkWriter, Usuario user) 
	throws Exception;
}
