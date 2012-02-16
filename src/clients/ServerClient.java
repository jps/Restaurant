package clients;

import java.io.IOException;
import java.net.ServerSocket;



public class ServerClient {

	private int cashiersConnected = 0;
	private int cooksConnected = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		@SuppressWarnings("unused")
		ServerGUI serverGUI = new ServerGUI(450,0,750,410,"Server");
		
		ServerSocket serverSocket = null;
		boolean listening = true; 
		
		
        try {
            serverSocket = new ServerSocket(40044);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 40044.");
            System.exit(-1);
        }
 
        while (listening)
        new ServerThread(serverSocket.accept()).start();
 
        serverSocket.close();
		
	}

	
	public  void incrementCashiers(){
		++cashiersConnected;
	}
	
	
	public void decrementCashiers(){
		--cashiersConnected;
	}
	
	public int getCashiers()
	{
		return cashiersConnected;
	}
	
	public  void incrementCooks(){
		++cooksConnected;
	}
	
	
	public void decrementCooks(){
		--cooksConnected;
	}
	
	public int getCooks()
	{
		return cooksConnected;
	}
	
	
	
	
	/*
	 * 
	 * 
	 *  public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
 
        try {
            serverSocket = new ServerSocket(40044);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 40044.");
            System.exit(-1);
        }
 
        while (listening)
        new KKMultiServerThread(serverSocket.accept()).start();
 
        serverSocket.close();
    }
	 * 
	 * 
	 */
	
}
