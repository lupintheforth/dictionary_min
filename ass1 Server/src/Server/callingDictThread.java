package Server;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
					
					System.out.println(commandReceived.toJSONString());
					
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
	
	
	
	
	

}
