package server.database;


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
}