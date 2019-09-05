package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.io.FileWriter;

public class MultithreadServer extends Thread {
	
	private int port = 5001;
	private String Path = "dictionary.json";
	private int clientnumber = 0;
	private Socket clientsocket = null;
	
	
	
	public MultithreadServer(int port, String path) 
	{
		this.port = port;
		this.Path = path;
		
		
	}
	
	public void run() {
		
		ServerSocket listeningSocket = null;
		//Socket clientSocket = null;
		
		
		
		
		
		try {
			//Create a server socket listening on port 5001
			listeningSocket = new ServerSocket(port);
			
			
			//Listen for incoming connections for ever 
			while (true) 
			{
				System.out.println("Server listening on port "+port+" for a connection");
				//Accept an incoming client connection request 
				this.clientsocket = listeningSocket.accept(); //This method will block until a connection request is received
				clientnumber++;
				
				String logger = this.timeStampOfoperations(clientnumber,clientsocket);
				
				System.out.println(logger);
				writetologger(logger);
				
				
				
				
				// process to allocate the 
				
				  
				
				  callingDictThread t = new callingDictThread(clientsocket,Path,clientnumber);
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
	
	private String timeStampOfoperations(int ClientID,Socket clientSocket)
	
	{
		Date date = new Date();
		long time = date.getTime();
		
		String connectionInfo;
		
		Timestamp ts = new Timestamp(time);
		
		connectionInfo = ("request ID: " + ClientID+"\n"+ "Remote Hostname: " + clientSocket.getInetAddress().getHostName()+"\n"
				+"Local Port: " + clientSocket.getLocalPort()+"\n"+ts.toString()+"\n");
		
		return connectionInfo;
		
	}
	
	public int getCurrentClientnumber() {
		
		return this.clientnumber;
	}
	
	public Socket getCurrentclientSocket() {
		
		return this.clientsocket;
	}
	
	private void writetologger(String log) {
		File file = new File("systemlogger.txt");
		try {
		if (!file.exists())
		{
			file.createNewFile();
		}
		BufferedWriter output = new BufferedWriter (new FileWriter(file,true));
		output.write(log+"\n");
		output.flush();
		output.close();
	
		}
		catch(FileNotFoundException e)
		{
			
			System.out.println("no file detected");
			
			
		}
		
		catch(IOException e)
		{
			
			System.out.println("reading error");
			
		}
		
	}

}
