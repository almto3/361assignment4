

/*
 * assignment is to implement AES-256
 * Driver class
 */
public class AES {
	boolean encrypt = true;
	
	public static void main(String[] args){
		//execute(args);
		int array[][] = Examples.example_array;
		
		Utility.printArray(array);
		
		array = Utility.xor(Examples.example_array, Examples.example_key);
		Utility.printArray(array);
		
		array = Utility.subBytes(array, true);
		Utility.printArray(array);
		
		array = Utility.shiftRows(array, true);
		Utility.printArray(array);

		array = Utility.mixColumns(array, true);
		Utility.printArray(array);
		
		array = Utility.mixColumns(array, false);
		Utility.printArray(array);
		
	}
	
	static void execute(String[] args){
		if(args.length < 3)
		{
			System.out.println("USAGE <e or d> <keyfile> <inputfile>");
			System.exit(-1);
		}
	}
}

