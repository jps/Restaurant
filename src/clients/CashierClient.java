package clients;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import sockets.*;

import protocol.cashierProtocol;

import models.Cashier;
import models.Order;

public class CashierClient extends Thread {

	public InputQueue inQueue;
	public OutputQueue outQueue;

	// private Queue<Object> OutQueue = new ConcurrentLinkedQueue<Object>();

	/*
	 * public void AddObjectToOutQueue(Object obj) { OutQueue.add(obj); }
	 */
	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @param args
	 * @throws
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {
		// TODO Auto-generated method stub

		String first , second , hostname;
		first = second = hostname = ""; 
		int port = 0; 
		
		
		for(int i = 0; i < args.length ; ++i)
		{
			if(args[i].equals("-h"))
			{
				++i;
				hostname = args[i];
			}else if(args[i].equals("-s"))
			{
				++i;
				port = (Integer.parseInt(args[i]));
			}
			else if(args[i].equals("-fn"))
			{
				++i;
				first = args[i];
			}else if(args[i].equals("-sn"))
			{
				++i;
				second = args[i];
			}
		}
		
		if(first == "" || second == "" || hostname == "" || port == 0 )
		{
			System.out.println("The Cashier Client could not start the required input was not found");
			System.out.println("Input should look like: -h host -s port -fn firstname -sn surname");
		}else
			new CashierClient(first , second, hostname, port);			
	}

	public CashierClient(String FirstName, String SecondName, String Hostname , int Port)
			throws IOException, InterruptedException {

		Init(FirstName, SecondName, Hostname, Port);

	}

	private synchronized void Init(String FirstName, String SecondName, String HostName, int Port)
			throws InterruptedException, IOException {
		CashierGUI cashierGUI = new CashierGUI(0, 0, 350, 410, FirstName +" "+SecondName);
		Cashier cashier = new Cashier(FirstName, SecondName, this);
		// CashierController.INSTANCE.CashierSignIn(ca1);
		// ca1.start();

		Socket RSocket = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;

		try {
			RSocket = new Socket(HostName, Port );
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
		// out.flush();

		// try and sign in
		outQueue.OutQueue.add(cashier); // addObjcetToOutQueue(ca1);

		while (true) {
			fromServer = inQueue.GetNextItemFromQueue();
			if (fromServer != null) {
				if (fromServer instanceof String)
					if (fromServer.equals("SignedIn")) {
						System.out.print("Signed in");
						cashier.setLoggedIn();
						cashier.start(); // start the cashier server
					}
				if (fromServer.equals("Bye.")) // server is closing
					break;
				if (fromServer instanceof Order)
				{
					cashier.addItemToFinishedOrders((Order)fromServer);
				}
				fromUser = cashierProtocol.processInput(fromServer);
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					outQueue.OutQueue.add(fromUser);
				}
			}
			cashierGUI.update(cashier);
			wait(500);// wait for 1/2 a second
		}

		out.close();
		in.close();
		RSocket.close();
	}
}
