package client;


import java.util.*;



public class MsgCount
{
	public int originId;
	public int msgCount;

	public MsgCount(int originId, int msgCount)
	{
		this.originId = originId;
		this.msgCount = msgCount;
	}

	public String toString()
	{
		return "From: " + this.originId + " (" + msgCount + ")";
	}
}