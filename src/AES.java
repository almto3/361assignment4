/*
 * Done by 	saleh alghusson, 	almto3@hotmail,com
 * and
 * 			ovais panjwani,		ovais.panjwani@utexas.edu
 * 
 * assignment is to implement AES-256 Encryption 
 * AES is the Driver class for the program
 * 
 * assignment specs:
 * 		https://www.cs.utexas.edu/~byoung/cs361/assignment-aes-zhao.html
 */

public class AES {
	boolean encrypt = true;
	
	public static void main(String[] args) throws Exception{
		long startTime = System.nanoTime();
		execute(args);
		long endTime = System.nanoTime();

		System.out.println ("runtime = " + (endTime - startTime)/1000000 + " milsec" );  //divide by 1000000 to get milliseconds.

		
	}
	
	private static void execute(String[] args) throws Exception{
		if(args.length < 3){
			System.out.println("java AES <e or d> <keyfile> <inputfile>");
			System.exit(0);
		}
		String key = Reader.read(args[1], false);
		String input = Reader.read(args[2], true);
		StringBuilder sb = new StringBuilder();
		int keyArray[][] = Utility.toArray(key.split("\n")[0]);
		boolean enc = true;
		int inputArray[][];
		
		if(args[0].equalsIgnoreCase("e")){
			for(int i = 0; i < input.split("\n").length; i++){
				inputArray = Utility.toArray(input.split("\n")[i]);
				inputArray = roundsEncrypt(Constants.rounds_256, inputArray, keyArray);
				sb.append( Utility.stringLine(inputArray));
			}
		}
		else if(args[0].equalsIgnoreCase("d")){
			enc = false;
			for(int i = 0; i < input.split("\n").length; i++){
				inputArray = Utility.toArray(input.split("\n")[i]);
				inputArray = roundsDecrypt(Constants.rounds_256, inputArray, keyArray);
				sb.append( Utility.stringLine(inputArray));
			}
		}
		Writer.write(args[2], sb.toString(), enc);
		sb = null;
	}
	
	private static int[][] roundsEncrypt(int rounds, int[][] inputArray, int[][] keyArray){
		
		int[][] extendedKey = AddRoundKey.keyExtension(keyArray);
		inputArray = Utility.addRoundKey(inputArray, extendedKey, 0);
		Utility.printLine(inputArray);
		for(int i = 1; i < rounds; i++) {
			inputArray = Utility.subBytes(inputArray, true);
			Utility.printLine(inputArray);
			
			inputArray = Utility.shiftRows(inputArray, true);
			Utility.printLine(inputArray);

			inputArray = Utility.mixColumns(inputArray, true);
			Utility.printLine(inputArray);

			inputArray = Utility.addRoundKey(inputArray, extendedKey, i);
			Utility.printLine(inputArray);
		}
		inputArray = Utility.subBytes(inputArray, true);
		Utility.printLine(inputArray);
		
		inputArray = Utility.shiftRows(inputArray, true);
		Utility.printLine(inputArray);

		inputArray = Utility.addRoundKey(inputArray, extendedKey, rounds);
		Utility.printLine(inputArray);
		
		return inputArray;
	}
	
	private static int[][] roundsDecrypt(int rounds, int[][] inputArray, int[][] keyArray){
		
		int[][] extendedKey = AddRoundKey.keyExtension(keyArray);
		
		inputArray = Utility.addRoundKey(inputArray, extendedKey, rounds);
		Utility.printLine(inputArray);
		
		inputArray = Utility.shiftRows(inputArray, false);
		Utility.printLine(inputArray);
		inputArray = Utility.subBytes(inputArray, false);
		Utility.printLine(inputArray);
		
		for(int i = rounds-1; i > 0; i--) {
			inputArray = Utility.addRoundKey(inputArray, extendedKey, i);
			Utility.printLine(inputArray);
			
			inputArray = Utility.mixColumns(inputArray, false);
			Utility.printLine(inputArray);
			
			inputArray = Utility.shiftRows(inputArray, false);
			Utility.printLine(inputArray);
			
			inputArray = Utility.subBytes(inputArray, false);
			Utility.printLine(inputArray);
		}
		inputArray = Utility.addRoundKey(inputArray, extendedKey, 0);
		Utility.printLine(inputArray);
		return inputArray;
	}
}

