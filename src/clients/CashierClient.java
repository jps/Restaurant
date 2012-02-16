package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import sockets.*;

import models.Order;
import models.Passable;
import protocol.cashierProtocol;


import models.Cashier;

public class CashierClient extends Thread {

	
	private Queue<Object> OutQueue = new ConcurrentLinkedQueue<Object>();
	
	public void AddObjectToOutQueue(Object obj)
	{
		OutQueue.add(obj);
	}
	/**
	 * @param args
	 * @throws  
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		@SuppressWarnings("unused")
		CashierGUI cashierGUI = new CashierGUI(0,0,200,410, "Cashier 1"); 
		Cashier ca1 = new Cashier("Tom","Taylor");
		//CashierController.INSTANCE.CashierSignIn(ca1);
		//ca1.start();
		
		 	Socket RSocket = null;
		    ObjectOutputStream out = null;
		    ObjectInputStream in = null;
			
	        try {
	            RSocket = new Socket("PandaLaptop",40044);
	            out = new ObjectOutputStream(RSocket.getOutputStream());
	            in = new ObjectInputStream(RSocket.getInputStream());
	            //in = new BufferedReader(new ObjectStreamReader(RSocket.getInputStream()));
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host: PandaLaptop.");
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for the connection to: PandaLaptop.");
	            System.exit(1);
	        }
	        
	        InputQueue inQueue = new InputQueue(in);
	        OutputQueue outQueue = new OutputQueue(out);
	        

	        Object fromServer;
	        Object fromUser;
	        
	   
	        
	        
	        inQueue.run();
	        outQueue.run();
	        //out.flush();
			
	      //try and sign in 
	        outQueue.addObjcetToOutQueue(ca1);
	        
			
	        while (true) {
	        	//System.out.println("passed line sign in");
	        	fromServer = null;
	        	fromServer = inQueue.GetNextItemFromQueue();
	        	
				if(fromServer != null)
				{
					if(fromServer instanceof String)
						if( fromServer.equals("SignedIn"))
						{
							System.out.print("Signed in");
							ca1.setLoggedIn();
							ca1.start(); //start the cashier server
						}
	        			if (fromServer.equals("Bye.")) //server is closing
	        				break;
		            fromUser = cashierProtocol.processInput(fromServer);
	        		if (fromUser != null) {
		                System.out.println("Client: " + fromUser);
		                outQueue.addObjcetToOutQueue(fromUser);
		        	}
		        }
	        }
		 
	        out.close();
	        in.close();
	        RSocket.close();
		}

	}

