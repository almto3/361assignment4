
/*
 * Driver class
 */
public class AES {

	
	public static void main(String[] args){
		int array[][] = Utility.example_array;
		
		Utility.printBytes(array);
		
		array = Utility.subBytes(array);
		Utility.printBytes(array);
	}
}

