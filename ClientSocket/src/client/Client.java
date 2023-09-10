package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private static final String AMARELO = "\u001B[33m";
	private static final String NEGRITO = "\u001b[1m";
	private static final String PADRAO = "\u001b[m";

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		String hostName = "localhost";
		int portNumber = 4999;
		
		//Verificando o hostname e porta especifica do Servidor
		System.out.println(NEGRITO+AMARELO+" ** CLIENTE IMPLEMENTADO COM SOCKETS  **"+PADRAO);
		System.out.println(AMARELO+"Precione ENTER para continuar ou digite uma porta especifica: "+PADRAO);
		Scanner scanner = new Scanner(System.in);
		String portNumberString = scanner.nextLine();		
		
		if(portNumberString != "") {
			portNumber =  Integer.parseInt(portNumberString);
			System.out.println(AMARELO+"Digite o hostname do Servidor..."+PADRAO);
			hostName = scanner.nextLine();
		}
		System.out.println(AMARELO+"\nAguardando Conexao com o Servidor...");
		
		try (
				//Inicia a o client e a conexao com o server atraves do hostname e porta definida
			    Socket echoSocket = new Socket(hostName, portNumber);
			)
		{
			System.out.println(NEGRITO+AMARELO+"Cliente conectado ao Servidor!"+PADRAO);
			System.out.println(AMARELO+"\nInicie uma conversa com o Servidor: "+PADRAO);
			
			//Inicia a thread responsavel pelas messages do client
			Output threadOutput = new Output(echoSocket);
			threadOutput.start();
			
			//Inicia a thread responsavel pelas messages do serv
			Input threadInput = new Input(echoSocket);
			threadInput.start();
			
			while(threadOutput.isAlive() && threadInput.isAlive());
			
		}
		
		scanner.close();
	}

}
