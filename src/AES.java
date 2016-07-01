import java.util.Arrays;

/*
 * assignment is to implement AES-256
 * Driver class
 */
public class AES {

	
	public static void main(String[] args){
		int array[][] = Examples.example_array;
		
		Utility.printArray(array);
		Utility.xor(Examples.example_array, Examples.example_key);
		Utility.printArray(array);
		
		array = Utility.subBytes(array);
		Utility.printArray(array);
		
		array = Utility.shiftRows(array);
		Utility.printArray(array);
		
		array = Utility.mixColumns(array);
		Utility.printArray(array);
	}
}

