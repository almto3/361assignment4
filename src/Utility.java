/*
 * Done by 	saleh alghusson, 	almto3@hotmail,com
 * and
 * 			ovais panjwani,		ovais.panjwani@utexas.edu
 * 
 * assignment is to implement AES-256 Encryption 
 * Utility is the class responsible for array manipulation
 * 
 * assignment specs:
 * 		https://www.cs.utexas.edu/~byoung/cs361/assignment-aes-zhao.html
 */

import javax.xml.bind.DatatypeConverter;

public class Utility{

	public static int[][] xor(int[][] array, int[][] key){
		System.out.println("xor:");
		for(int i = 0; i < array.length; i++)
			for(int j = 0; j< array[i].length; j++)
				array[i][j] = array[i][j] ^ key[i][j];	
		return array;
	}
	
	public static int[][] subBytes(int[][] array, boolean encryption){
		System.out.println("subBytes, inverse = " + !encryption + ":");
		
		for(int i = 0; i < array.length; i++)
			for(int j = 0; j< array[i].length; j++)
				array[i][j] = subBytesHelper(array[i][j], encryption);
		
		return array;
	}
	public static int subBytesHelper(int a, boolean encryption){
		String s = Integer.toHexString(a);
		int x = 0;
		int y = 0;
		
		if(s.length() == 2){
			x = Integer.parseInt( s.substring(0, 1), 16);
			y = Integer.parseInt( s.substring(1, 2), 16);
		}
		else if(s.length() == 1){
			x = 0;
			y = Integer.parseInt( s.substring(0, 1), 16);
		}
		else{
			x = 0;
			y = 0;
		}	
		if(encryption)
			return Constants.sbox[x][y];
		return Constants.isbox[x][y];
	}
	public static int[][] shiftRows(int[][] array, boolean encryption){
		String x = encryption ? "left:" : "right:";
		System.out.println("shiftRows to the " + x);
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < i; j++)
				array [i] = shiftRowsHelper(array[i], encryption);
		}
		return array;
	}
	/*	takes a 4 byte array, shifts it 1 byte to the left if encryption and to the right if not	*/ 
	private static int[] shiftRowsHelper(int[] array, boolean encryption){
		
		int m = array.length;

        if (encryption){
        	int temp = array[0];
	        for (int k=0; k<m-1; k++){
	            array[k] = array[k+1];
	        }
	        array[m-1] = temp;
        }
        else{
        	int temp = array[array.length-1];
	        for (int k = m-1; k > 0; k--){
	            array[k] = array[k-1];
	        }
	        array[0] = temp;
        }
		return array;
	}
	public static int[][] mixColumns(int[][] array, boolean encryption){
		System.out.println("mixColumns, encryption = " + !encryption + ":");
		for(int j = 0; j< array[0].length; j++)
			if (encryption)
				array = MixColumns.mixColumn2(array, j);
			else
				array = MixColumns.invMixColumn2(array, j);
		return array;
	}
	public static int[][] addRoundKey(int[][] array, int[][] key, int round){
		System.out.println("addRoundKey (" + round + "):");
		
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++){
				array[i][j] ^= key[i][j + (4*round)];
			}
		}
		return array;
	}
	public static int[][] toArray(String line, boolean input){
		int array[][];
		//leading zeros will be added to pad the input
		while(true && input)
			if (line.length() < 32)
				line = "0"+line;
			else
				break;
		
		byte[] bytes;
		//all trailing chars will be discarded
		if(! input){
			bytes = DatatypeConverter.parseHexBinary(line.substring(0, 64));
			array = new int[4][8];
		}
		else{
			bytes = DatatypeConverter.parseHexBinary(line.substring(0, 32));
			array = new int[4][4];
		}
		for (int i = 0; i < array.length; i++){
			for (int j = 0; j < array[0].length; j++)
				array[i][j] = bytes[i + 4*j] & 0xff;
			
		}
		return array;
	}
	
	public static String stringLine(int[][] array){
		
		StringBuilder sb = new StringBuilder();
		array = Utility.transposeMatrix(array);
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j< array[i].length; j++){
				if(array[i][j] < 16)
					sb.append("0");
				sb.append(Integer.toHexString(array[i][j]).toUpperCase());
			}
		}
		sb.append("\n");
		return sb.toString();
	}
	public static void printLine(int[][] array){
		
		System.out.println(stringLine(array));
	}
	public static String stringArray(int[][] array){
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j< array[i].length; j++){
				if(array[i][j] < 16)
					sb.append("0");
				sb.append(Integer.toHexString(array[i][j]).toUpperCase() + "\t");
			}
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	public static void printArray(int[][] array){
		
		System.out.println(stringArray(array));
	}
	public static String stringArray(byte[][] array){
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j< array[i].length; j++){
				if( (array[i][j]& 0xff) < 0x10)
					sb.append("0");
				sb.append( String.format("%02X ", array[i][j] & 0xff) + "\t");
			}
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	public static void printArray(byte[][] array){
		
		System.out.print(stringArray(array));
	}
	/*	create a random array with elements from 0 to 255	*/
	public static int[][] randomArray(int x, int y){
		
		int[][] array = new int[x][y];
		for(int i = 0; i< x; i++)
			for (int j = 0; j< y; j++)
				array[i][j] = (int)(Math.random() * 255); 
		return array;
	}
	public static int[][] createEmpty(int x, int y){
		System.out.println("createEmpty");
		return new int[x][y];
	}
	public static byte[][] tobyteArray(int[][] array){
		byte byteArray[][] = new byte[array.length][array[0].length];
		
		for(int i = 0; i< array.length; i++)
			for (int j = 0; j< array[i].length; j++){
				byte x = (byte) array[i][j];
				byteArray[i][j] = x;
			}
		return byteArray;
	}
	public static int[][] tointArray(byte[][] array){
		int intArray[][] = new int[array.length][array[0].length];
		
		for(int i = 0; i< array.length; i++)
			for (int j = 0; j< array[i].length; j++){
				int x = ((int) (array[i][j])) &0x000000ff;
				intArray[i][j] = x;
			}
		return intArray;
	}
	public static int[][] transposeMatrix(int [][] m){
		int[][] temp = new int[m[0].length][m.length];
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[0].length; j++)
				temp[j][i] = m[i][j];
		return temp;
	}
}