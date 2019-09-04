package Server;

import java.io.Reader;


import java.io.IOException;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.*;
import org.json.simple.parser.*;




public class dictionary {
	
	private String Path;
	private JSONObject dictionary;
	
	public dictionary(String Path) {
		this.Path = Path;
		this.readJSONFile();
	}
	
	public synchronized void readJSONFile() {
		JSONParser parser = new JSONParser();
		
		File file = new File(this.Path);
		this.dictionary = new JSONObject();
		
		try 
		{	if(!file.exists())
			{
			
				file.createNewFile();
			}
				
				Reader fileinput = new FileReader(file);
				this.dictionary = (JSONObject) parser.parse(fileinput);
				fileinput.close();
			
		}
		
		catch(FileNotFoundException e)
		{
			System.out.println("no file detected");
			
			
		}
		
		catch(IOException e)
		{
			
			System.out.println("reading error");
			
		}
		catch(ParseException e)
		{
			System.out.println("parsing error");
			
		}
		
		
	}
	
	public synchronized void overwriteJSONFile() {
		
		JSONParser parser = new JSONParser();
		
		File file = new File(this.Path);
		try 
		{
			FileWriter filewriter = new FileWriter(file);
			filewriter.write(this.dictionary.toJSONString());
			filewriter.flush();
			System.out.println("file overwritten");
			filewriter.close();
			
			
		}
		catch(FileNotFoundException e)
		{
			
			System.out.println("no file detected,reopen the terminal and it will be alright");
			
			
		}
		
		catch(IOException e)
		{
			
			System.out.println("reading error");
			
		}
		
		
			
			
			
			
			
	}
	
	public synchronized String addWord(String word, String meaning) {
			
		String result = null;
		readJSONFile();
		
			if (this.dictionary.containsKey(word))
			{
				result = ("already exists\n");
			}
		
			else
			{
				this.dictionary.put(word, meaning);
				result = "word added\n";
				overwriteJSONFile();
			
			}
		return result;
		
	}
	
	public synchronized String query(String word) {
		
		String result;
		readJSONFile();
		
		if (!this.dictionary.containsKey(word)) 
		{
			result ="no word in the dictionary\n";
		}
		else
		{
			
			result = (String) this.dictionary.get(word);
		}
		return result;
	
		
		
		
		
	}
	
	public synchronized String removeWord(String word) {
		
		String result = null;
		readJSONFile();
		
		if(!this.dictionary.containsKey(word)) 
		{
			
			result = "no word in the dictionary\n";
			
		}
		
		else
		{
			this.dictionary.remove(word);
			result = "completed\n";
			overwriteJSONFile();
		}
		
		return result;
		
		
	}
		
		
		
		
		
		
		
		
}
	
	
	

