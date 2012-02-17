package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InputQueue extends Thread {

	public Queue<Object> InQueue = new ConcurrentLinkedQueue<Object>();
	private ObjectInputStream InStream;
	
	public InputQueue(ObjectInputStream inStream)
	{
		InStream = inStream;
	}
	
	public synchronized Object GetNextItemFromQueue()
	{
		return InQueue.poll();
	}
	
	public void run()
	{
		while(true)
		{
			try {
				System.out.println("checking input queue");
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
