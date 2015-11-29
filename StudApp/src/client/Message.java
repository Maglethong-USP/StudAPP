package client;

import java.util.*;


public class Message
{
	private int 	origin;
	private int 	destination;
	private String 	content;
	private Date 	timeSent;

	//! [Constructor]
	public Message()
	{
		this.origin 		= -1;
		this.destination 	= -1;
		this.content 		= null;
		this.timeSent 		= null;
	}
	public Message(int destination, String content)
	{
		this.origin 		= -1;
		this.destination 	= destination;
		this.content 		= content;
		this.timeSent 		= null;
	}
	public Message(int origin, int destination, String content, Date timeSent)
	{
		this.origin 		= origin;
		this.destination 	= destination;
		this.content 		= content;
		this.timeSent 		= timeSent;
	}

	// Getters
	public int 		getOrigin(){ 		return this.origin; }
	public int 		getDestination(){ 	return this.destination; }
	public String 	getContent(){ 		return this.content; }
	public Date 	getTimeSent(){ 		return this.timeSent; }

	// Setters
	public void setOrigin(int origin){ this.origin = origin; }
	public void setDestination(int destination){ this.destination = destination; }
	public void setContent(String content){ this.content = content; }
	public void setTimeSent(Date timeSent){ this.timeSent = timeSent; }

	// To string
	public String toString()
	{
		return 	"From:    " + this.origin + "\n"
			  + "To:      " + this.destination  + "\n"
			  + "Time:    " + this.timeSent  + "\n"
			  + "Content: " + this.content; 
	}
}