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

		new CashierClient("Tom", "Taylor");

	}

	public CashierClient(String FirstName, String SecondName)
			throws IOException, InterruptedException {

		Init(FirstName, SecondName);

	}

	private synchronized void Init(String FirstName, String SecondName)
			throws InterruptedException, IOException {
		@SuppressWarnings("unused")
		CashierGUI cashierGUI = new CashierGUI(0, 0, 200, 410, "Cashier 1");
		Cashier casher = new Cashier(FirstName, SecondName, this);
		// CashierController.INSTANCE.CashierSignIn(ca1);
		// ca1.start();

		Socket RSocket = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;

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
		// out.flush();

		// try and sign in
		outQueue.OutQueue.add(casher); // addObjcetToOutQueue(ca1);

		while (true) {
			fromServer = inQueue.GetNextItemFromQueue();
			if (fromServer != null) {
				if (fromServer instanceof String)
					if (fromServer.equals("SignedIn")) {
						System.out.print("Signed in");
						casher.setLoggedIn();
						casher.start(); // start the cashier server
					}
				if (fromServer.equals("Bye.")) // server is closing
					break;
				if (fromServer instanceof Order)
				{
					casher.addItemToFinishedOrders((Order)fromServer);
				}
				fromUser = cashierProtocol.processInput(fromServer);
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					outQueue.OutQueue.add(fromUser);
				}
			}
			wait(500);// wait for 1/2 a second
		}

		out.close();
		in.close();
		RSocket.close();
	}
}
