/*
 * Done by 	saleh alghusson, 	almto3@hotmail,com
 * and
 * 			ovais panjwani,		ovais.panjwani@utexas.edu
 * 
 * assignment is to implement AES-256 Encryption 
 * Writer is the class responisble for writing to files
 * 
 * assignment specs:
 * 		https://www.cs.utexas.edu/~byoung/cs361/assignment-aes-zhao.html
 */

import java.io.PrintWriter;

public class Writer {
	
	/*	write method will write to a file	*/
	static void write (String path, String text, boolean encrypt) throws Exception{
		String ext = encrypt ? ".enc" : ".dec";
		PrintWriter writer = new PrintWriter(path+ext, "UTF-8");
		
		writer.println(text.trim());
		writer.close();
		
		System.out.println("Wrote result to " + path);
	}
}
