package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Output extends Thread{
	private Socket echoSocket;
	private PrintWriter out;
	private BufferedReader stdIn;
	
	public Output(Socket echoSoket) throws IOException {
		this.echoSocket = echoSoket;
		this.out = new PrintWriter(echoSocket.getOutputStream(), true);
		this.stdIn = new BufferedReader(
	            new InputStreamReader(System.in));
		
	}
	
	public void run() {
		try {
			String userInput;
			while (true) {
				userInput = stdIn.readLine();
			    out.println(userInput);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
