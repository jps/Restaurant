package clients;

import protocol.RestaurantProtocol;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
	private Socket socket = null;
	private ServerClient serverClient;

	public ServerThread(Socket socket, ServerClient ServerClient) {
		super("Server Client");
		this.socket = socket;
		serverClient = ServerClient;
	}

	public void run() {

		try {
			// PrintWriter out = new PrintWriter(socket.getOutputStream(),
			// true);
			// BufferedReader in = new BufferedReader(
			// new InputStreamReader(
			// socket.getInputStream()));

			ObjectInputStream in = new ObjectInputStream(
					socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(
					socket.getOutputStream());

			// String inputLine, outputLine;
			Object inputObject, outputObject;
			RestaurantProtocol RP = new RestaurantProtocol();

			// outputLine = RP.processInput(null);
			outputObject = RP.processInput(null);
			// out.println(outputLine);
			out.writeObject(outputObject);

			// while ((inputLine = in.readLine()) != null) {
			while (true) {
				inputObject = null;
				try {
					inputObject = in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (inputObject != null) {
					// outputLine = RP.processInput(inputLine);
					// out.println(outputLine);
					outputObject = RP.processInput(inputObject); // pass the
																	// object
																	// into the
																	// protocol
					out.writeObject(outputObject); // send the return of
													// protocol
					if (outputObject instanceof String)
						if (((String) outputObject).equals("Bye"))
							break;
				}
				serverClient.updateGUI();
			}
			out.close();
			in.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
