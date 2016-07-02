/*
 * Done by 	saleh alghusson, 	almto3@hotmail,com
 * and
 * 			ovais panjwani,		ovais.panjwani@utexas.edu
 * 
 * assignment is to implement AES-256 Encryption 
 * Reader is a class that is responsible of reading a file
 * 
 * assignment specs:
 * 		https://www.cs.utexas.edu/~byoung/cs361/assignment-aes-zhao.html
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader { 
	
	/*	default path on my machine
	 * /Users/almto3/Github/361assignment4/	
	 */
	private static String inputPath;
	private static String keyPath;
	private static File inputFile;
	private static File keyFile;
	private static BufferedReader br;
	
	static String getInputPath(){
		return inputPath;
	}
	private static void setPath(String path, boolean input)throws Exception{
		
		if(input){
			inputPath = path;
			inputFile = new File(inputPath);
			if(! fileExists(inputPath) )
				throw new FileNotFoundException("File not found at path: " + inputPath);
		}
		else{
			keyPath = path;
			keyFile = new File(keyPath);
			if(! fileExists(keyPath) )  
				throw new FileNotFoundException("File not found at path: " + keyPath);
		}
	}
	protected static boolean fileExists(String path){
		File tmp = new File(path);
		if(!tmp.exists() || tmp.isDirectory())
			return false;
		return true;
	}
	
	/*	read method will read the file	*/
	public static String read (String path, boolean input) throws Exception{
		setPath(path, input);
		
		StringBuilder sb = new StringBuilder();
	    try {
	    	if(input)
	    		br = new BufferedReader(new FileReader(inputFile));
	    	else
	    		br = new BufferedReader(new FileReader(keyFile));
	    	
	    	String line = "";
		    
		    /*	feed the input file to the cover channel line by line	*/
		    while((line = br.readLine())!=null){
		    	sb.append(line+"\n");
		    }
	    } 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null)
					br.close();
		    } 
			catch (IOException ex) {
				ex.printStackTrace();
		    }
		}
	    return sb.toString();
	}
}