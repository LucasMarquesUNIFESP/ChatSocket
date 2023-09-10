package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Input extends Thread{
	private static final String AMARELO = "\u001B[33m";
	private static final String NEGRITO = "\u001b[1m";
	private static final String AZUL = "\u001B[32m";
	private static final String CIANO = "\u001B[36m";
	private static final String PADRAO = "\u001b[m";
	
	private Socket echoSocket;
	private BufferedReader in;
	
	public Input(Socket echoSoket) throws IOException {
		this.echoSocket = echoSoket;
		this.in = new BufferedReader(
	            new InputStreamReader(echoSocket.getInputStream()));
		
	}
	
	public void run() {
		try {
			//Inicia um loop que le as messages do client e a exibe na tela
			while (true) {
				System.out.println(AZUL+"Client: " +CIANO+ in.readLine()+PADRAO);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(NEGRITO+AMARELO+"Conexao encerrada!"+PADRAO);
		}
	}
}
