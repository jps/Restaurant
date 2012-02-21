package sockets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OutputQueue extends Thread {

	public Queue<Object> OutQueue = new ConcurrentLinkedQueue<Object>();
	private ObjectOutputStream OutputStream;

	public OutputQueue(ObjectOutputStream out) {
		OutputStream = out;
	}

	public synchronized void addObjcetToOutQueue(Object obj) {
		System.out.println("object added to queue");
		OutQueue.add(obj);
		this.notify();
	}

	public synchronized void run() {
		while (true) {
			// System.out.println("checking output stream");
			if (OutQueue.size() != 0) {
				Object obj = OutQueue.poll();
				try {
					System.out.println("trying to write object");
					OutputStream.writeObject(obj);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					this.wait(500);//this has solved a massive cpu hit
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
