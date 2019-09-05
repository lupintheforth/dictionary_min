package clientterminal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;






public class clientdoing {
	
	private static String ip ="localhost";
	private static int port = 5001 ;
	
	public clientdoing(String newIp,int newPort) {
		
		if(newIp != null && (newPort > 1000 && newPort < 9999) )
		{
				ip = newIp;
				port = newPort;
				
		}
		
		
	}
	
	
	
	
	public String sendRequest(int commandType,String word,String meaning)
	{
		
		Socket socket = null;
		
		String result = "no connection right now, wait for Server on";
		

		
		try
		{
			socket = new Socket(ip,port);
			
			
			if(socket.isConnected())
				System.out.println("Connection established,and request will be sent");
			
			DataInputStream input = new DataInputStream(socket.getInputStream());
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			
			// the process to send the command
			JSONObject commandtoServer = new JSONObject();
			JSONParser parser = new JSONParser();
			
			
		
			
			if(commandType == 1) // if it is type 1 command, namely the 'add' command, we will send a different jsonobject.
			{
				commandtoServer.put(commandType, word);
				commandtoServer.put("meaning",meaning);
				
			}
			else
				commandtoServer.put(commandType,word);
			
			
			System.out.println(commandtoServer.toJSONString());
				
			output.writeUTF(commandtoServer.toJSONString());
			output.flush();
			System.out.println("command Sent");
				
			// the process to receive the result
				
			JSONObject answers = new JSONObject();
			
			try 
			{
				answers = (JSONObject) parser.parse(input.readUTF());
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
			
			if(answers.containsKey("meaning"))
			{	
				String meanings = (String) answers.get("meaning");
				
				result = meanings;
			}
			else
			{
				result = " no response received, something wrong with the Server";
				
			}
			
			
			
			
			
			
			
		}
		
		catch(ConnectException ex)
		{
			
			return result;
			
		}
		
		
		catch (UnknownHostException e)
		{
			e.printStackTrace();
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (socket != null)
			{
				try 
				{
					socket.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
					
			}
				
		}
		
		return result;
	}
	
	
}
