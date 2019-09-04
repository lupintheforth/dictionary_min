package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class MultithreadServer {
	
	private static int port = 5001;
	private static String Path = "dictionary.json";
	
	public static void main(String[] args) {
		
		ServerSocket listeningSocket = null;
		Socket clientSocket = null;
		
		ArrayList<callingDictThread> threadlist = new ArrayList<callingDictThread>(10);
		
		
		
		
		try {
			//Create a server socket listening on port 4444
			listeningSocket = new ServerSocket(port);
			int i = 0; //counter to keep track of the number of clients
			
			
			
			//Listen for incoming connections for ever 
			while (true) 
			{
				System.out.println("Server listening on port "+port+" for a connection");
				//Accept an incoming client connection request 
				clientSocket = listeningSocket.accept(); //This method will block until a connection request is received
				i++;
				System.out.println("request number  " + i + " accepted:");
				//System.out.println("Remote Port: " + clientSocket.getPort());
				System.out.println("Remote Hostname: " + clientSocket.getInetAddress().getHostName());
				System.out.println("Local Port: " + clientSocket.getLocalPort());
				
				
				
				// process to 
				  
				  
				  Thread t = new Thread(new callingDictThread(clientSocket,Path,i));
				  t.start();
				  
				
		
			}
		} 
		catch (SocketException ex)
		{
			
			if(!(listeningSocket.isClosed()))
			{
				try
				{
					listeningSocket.close();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			System.out.println("the client is gone");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			if(listeningSocket != null)
			{
				try
				{
					listeningSocket.close();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

}
