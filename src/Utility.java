

/*
 * Utility class will be responsible for utility things
 */
public class Utility{

	public static int[][] xor(int[][] array, int[][] key){
	
		for(int i = 0; i < array.length; i++)
			for(int j = 0; j< array[i].length; j++)
				array[i][j] = array[i][j] ^ key[i][j];	
		return array;
	}
	
	public static int[][] subBytes(int[][] array){
		System.out.println("subBytes");
		int x = 0;
		int y = 0;
		for(int i = 0; i < array.length; i++)
			for(int j = 0; j< array[i].length; j++){
				String s = Integer.toHexString(array[i][j]);
				
				if(s.length() == 2){
					//System.out.println("s = " + s + " " + s.length());
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
				array[i][j] = Examples.sbox[x][y];
			}
		
		return array;
	}
	public static int[][] shiftRows(int[][] array){
		System.out.println("shiftRows");
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < i; j++)
				array [i] = shiftRowsHelper(array[i]);
		}
		return array;
	}
	private static int[] shiftRowsHelper(int[] array){
		
		int m = array.length;
        int temp = array[0];
        for (int k=0; k<m-1; k++){
            array[k] = array[k+1];
        }
        array[m-1] = temp;
		return array;
	}
	public static int[][] mixColumns(int[][] array){
		System.out.println("mixColumns");
		for(int j = 0; j< array[0].length; j++)
			array = MixColumns.mixColumn2(array, j);
		return array;
	}
	public static int[][] addRoundKey(int[][] key, int[][] array){
		System.out.println("addRoundKey");
		return new int[5][5];
	}
	public static String stringArray(int[][] array){
		System.out.println("int stringArray");
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
		System.out.println("int printArray");
		System.out.println(stringArray(array));
	}
	public static String stringArray(byte[][] array){
		System.out.println("byte stringArray");
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
		System.out.println("byte printArray");
		System.out.print(stringArray(array));
	}
	/*	create a random array with elements from 0 to 255	*/
	public static int[][] randomArray(int x, int y){
		System.out.println("randomArray");
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
}