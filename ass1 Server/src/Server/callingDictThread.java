package Server;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class callingDictThread extends Thread{
	
	private Socket connectingClient;
	private dictionary dict;
	private String Path;
	private int clientnumber;
	
	public callingDictThread(Socket socket,String Path,int number){
		
		this.connectingClient= socket;
		this.Path = Path;
		this.clientnumber = number;
	}
	
	public Socket getconnectingClient() {
		
		return connectingClient;
		
	}
	
	
	public void run() {
		
		this.dict = new dictionary(this.Path);
		try 
		{
			DataInputStream input = new DataInputStream(connectingClient.getInputStream());
			DataOutputStream output = new DataOutputStream(connectingClient.getOutputStream());
				
			
					JSONObject commandReceived = new JSONObject();
					JSONParser parser = new JSONParser();
					
					String state = null;
					String messagetoc = null;
					String word = null;
					
					
					commandReceived = (JSONObject) parser.parse(input.readUTF());
					
					
					String action = getActionFromClient(commandReceived,clientnumber);
					
					writetorecord(action);
					
					System.out.println(action);
					
					
					
					JSONObject commandToSend = new JSONObject();
					
					if(commandReceived.containsKey("1"))
					
					{	
						word = (String) commandReceived.get("1");
						state = "Good!";
						String Meaning = (String) commandReceived.get("meaning");
						messagetoc = dict.addWord(word, Meaning);
					
						
					}
					else if(commandReceived.containsKey("2"))
					
					{	
						word = (String) commandReceived.get("2");
						state = "Good!";
						messagetoc = dict.removeWord(word);
						
						
					}
					
					else if(commandReceived.containsKey("3"))
					{	
						word= (String) commandReceived.get("3");
						state = "Good!";
						messagetoc = dict.query(word);
					
					}
					
					
					commandToSend.put("goodOrnot",state);
					commandToSend.put("meaning",messagetoc);
					
					
					output.writeUTF(commandToSend.toJSONString());
					output.flush();
				
					
					
		
			
				
					System.out.println("The No."+clientnumber+" is done.\n");
					
				
				
			}
		
		catch (UnknownHostException e)
		{
			e.printStackTrace();
			
		}
		catch (IOException e)
		{
			
			e.printStackTrace();
			
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			
		}

		
		
		
		
		
	}
	
	public String getActionFromClient(JSONObject commandReceived,int clientnumber) 
	{	
		String action = null;
		try {
		if(commandReceived.containsKey("1"))
		{	
			String word = (String) commandReceived.get("1");
			String meaning = (String) commandReceived.get("meaning");
			action = ("No."+clientnumber+" tried to add: ("+word+") ("+meaning+")\n");
		}
		else if(commandReceived.containsKey("2"))
		{	
			String word = (String) commandReceived.get("2");
			action = ("No."+clientnumber+" tried to remove: ("+word+")\n");
		}
		else if(commandReceived.containsKey("3"))
		{
			String word = (String) commandReceived.get("3");
			action = ("No."+clientnumber+" tried to search: ("+word+")\n");
		
		}
		else
			throw new Exception ("error");
		
		
		
		}
		catch(Exception e) 
		{
			action = "error happened";
		}
		return action;
		
		
	}
	
	private void writetorecord(String log) {
		File file = new File("RequestActions.txt");
		try {
		if (!file.exists())
		{
			file.createNewFile();
		}
		BufferedWriter output = new BufferedWriter (new FileWriter(file,true));
		output.write(log);
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
