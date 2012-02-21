package clients;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import protocol.CookProtocol;
import models.Cook;
import models.Order;

import sockets.InputQueue;
import sockets.OutputQueue;

public class CookClient extends Thread {

	public InputQueue inQueue;
	public OutputQueue outQueue;
	public CookGUI cookGUI;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {
		new CookClient("Amber", "Ryan");
	}

	public CookClient(String FirstName, String SecondName) throws IOException,
			InterruptedException {
		Init(FirstName, SecondName);
	}

	private synchronized void Init(String FirstName, String SecondName)
			throws IOException, InterruptedException {
		cookGUI = new CookGUI(0, 420, 200, 410, "cookClientTest");
		Cook cook = new Cook(FirstName, SecondName,this);
		Socket RSocket = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		// PrintWriter out = null;
		// BufferedReader in = null;

		try {
			RSocket = new Socket("PandaLaptop", 40044);
			out = new ObjectOutputStream(RSocket.getOutputStream());
			in = new ObjectInputStream(RSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: PandaLaptop.");
			System.exit(1);
		} catch (IOException e) {
			System.err
					.println("Couldn't get I/O for the connection to: PandaLaptop.");
			System.exit(1);
		}

		inQueue = new InputQueue(in);
		outQueue = new OutputQueue(out);

		Object fromServer;
		Object fromUser;

		inQueue.start();
		outQueue.start();
		
		outQueue.addObjcetToOutQueue(cook);
		
		while (true) {
			fromServer = inQueue.GetNextItemFromQueue();
			if (fromServer != null) {
				if (fromServer instanceof String) {
					if (fromServer.equals("SignedIn")) {
						System.out.print("Signed in");
						cook.setLoggedIn();
						cook.start();
					}
					else if(fromServer.equals("FailedIn"))
					{
						System.out.println("Signed in failed"); //TODO: this should be handled
						break;
					}else if(fromServer.equals("SignedOut"))
					{
						System.out.println("Signed out");
						break; 
					}else if(fromServer.equals("Sign out failed"))
					{
						//try again
						outQueue.addObjcetToOutQueue(cook);
					}
					if (fromServer.equals("Bye."))
						break;
				}
				else if(fromServer instanceof Order)
				{
					cook.addToWaiting((Order)fromServer);//pass the order to cook
				}
				
				fromUser = CookProtocol.processInput(fromServer);
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					outQueue.OutQueue.add(fromUser);
				}
			} else {
				this.wait(500);// wait for 1/2 second to reduce server load
			}
		}

		out.close();
		in.close();
		RSocket.close();
	}
}
