package sockets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OutputQueue extends Thread {

	private Queue<Object> OutQueue = new ConcurrentLinkedQueue<Object>();
	private ObjectOutputStream OutputStream;
	
	public OutputQueue(ObjectOutputStream out)
	{
		OutputStream = out; 
	}
	
	public void addObjcetToOutQueue(Object obj)
	{
		OutQueue.add(obj);
		this.interrupt();
	}
	
	public void run()
	{
		while(true)
		{
			
			if(OutQueue.size() != 0)
			{
				Object obj = OutQueue.poll();
				try {
					OutputStream.writeObject(obj);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
			{
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
