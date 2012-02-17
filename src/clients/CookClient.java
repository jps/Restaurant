package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import protocol.CookProtocol;
import protocol.cashierProtocol;

import models.Cook;

import sockets.InputQueue;
import sockets.OutputQueue;



public class CookClient extends Thread{
	
	public InputQueue inQueue;
	public OutputQueue outQueue;
	public CookGUI cookGUI; 

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		new CookClient("Amber", "Ryan");
	}
	
	public CookClient(String FirstName, String SecondName) throws IOException
	{
		cookGUI = new CookGUI(0,420,200, 410, "cookClientTest");
		Cook cook = new Cook(FirstName, SecondName);
	    Socket RSocket = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
	    //PrintWriter out = null;
	    //BufferedReader in = null;
		
        try {
            RSocket = new Socket("PandaLaptop",40044);
            out = new ObjectOutputStream(RSocket.getOutputStream());
			in = new ObjectInputStream(RSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: PandaLaptop.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: PandaLaptop.");
            System.exit(1);
        }
        
        inQueue = new InputQueue(in);
		outQueue = new OutputQueue(out);
  
        
        //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        Object fromServer;
        Object fromUser;
 
        while(true){
        	fromServer = inQueue.GetNextItemFromQueue();
        	if(fromServer != null)
        	{
        		if(fromServer instanceof String)
        		{
        			if (fromServer.equals("SignedIn")) {
						System.out.print("Signed in");
						cook.setLoggedIn();
						cook.start();
        			}
        			if (fromServer.equals("Bye."))
                        break;
        		}
        		fromUser = CookProtocol.processInput(fromServer);
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					outQueue.OutQueue.add(fromUser);
				}
        	}
        }
         
        out.close();
        in.close();
        RSocket.close();	
	}
	
	


}
