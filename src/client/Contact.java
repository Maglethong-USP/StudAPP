package client;

import java.util.*;


public class Contact
{
	private int 	id;
	private String 	name;

	//! [Constructor]
	public Contact()
	{
		this.id = -1;
		this.name = "";
	}
	public Contact(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	// Getters
	public int 		getID(){ 		return this.id; }
	public String 	getName(){ 		return this.name; }

	// Setters
	public void setID(int id){ this.id = id; }
	public void setName(String name){ this.name = name; }

	// To string
	public String toString()
	{
		return this.name + " (" + this.id + ")";
	}
}