package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	private static final String AMARELO = "\u001B[33m";
	private static final String NEGRITO = "\u001b[1m";
	private static final String PADRAO = "\u001b[m";
	
	

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		int portNumber = 4999;
		
		//Verificando o inicio do server em uma porta especifica
		System.out.println(NEGRITO+AMARELO+" ** SERVIDOR IMPLEMENTADO COM SOCKETS  **"+PADRAO);
		System.out.println(AMARELO+"Precione ENTER para continuar ou digite uma porta especifica: "+PADRAO);
		Scanner scanner = new Scanner(System.in);
		String portNumberString = scanner.nextLine();		
		
		if(portNumberString != "") portNumber =  Integer.parseInt(portNumberString);
		System.out.println(AMARELO+"\nAguardando inicio do Servidor e conexao com o Cliente...");
		
		try (   
				//Inicia a o serv na porta portNumber e aceita a conexao com o client
			    ServerSocket serverSocket = new ServerSocket(portNumber);
			    Socket clientSocket = serverSocket.accept();
			) 
		{	
				System.out.println(NEGRITO+AMARELO+"Cliente Conectado!"+PADRAO);
				System.out.println(AMARELO+"\nInicie uma conversa com o Cliente: "+PADRAO);
				
				//Inicia a thread responsavel pelas messages do serv
				Output threadOutput = new Output(clientSocket);
				threadOutput.start();
				
				//Inicia a thread responsavel pelas messages do client
				Input threadInput = new Input(clientSocket);
				threadInput.start();
				
				while(threadOutput.isAlive() || threadInput.isAlive());
		}
		
		scanner.close();
		
	}

}
