package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InputQueue extends Thread {

	private Queue<Object> InQueue = new ConcurrentLinkedQueue<Object>();
	private ObjectInputStream InStream;
	
	public InputQueue(ObjectInputStream inStream)
	{
		InStream = inStream;
	}
	
	public Object GetNextItemFromQueue()
	{
		return InQueue.peek();
	}
	
	public void run()
	{
		while(true)
		{
			try {
				Object in = null;
				in = InStream.readObject();
				if( in != null)
					InQueue.add(in);
				
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
			
				e.printStackTrace();
			}	
		}
	}
	
	
}
