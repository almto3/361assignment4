/*
 * Done by 	saleh alghusson, 	almto3@hotmail,com
 * and
 * 			ovais panjwani,		ovais.panjwani@utexas.edu
 * 
 * assignment is to implement AES-256 Encryption 
 * AddRoundKey is the class responsible for key extension
 * 
 * assignment specs:
 * 		https://www.cs.utexas.edu/~byoung/cs361/assignment-aes-zhao.html
 * 
 * major help from:
 * 		http://www.samiam.org/key-schedule.html
 */

public class AddRoundKey {
	
	public static int[][] keyExtension(int[][] cipherKey){
		int[][] extendedKey = new int[4][60];
		int[][] temporaryKey = new int[4][8];
		
		for(int i = 0; i < cipherKey.length; i++)
			for (int j = 0; j < cipherKey[0].length; j++) 
				extendedKey[i][j] = cipherKey[i][j];			
		
		int[] temp = new int[4];
		for(int i = 4; i > 0; i--)
			temp[4-i] = cipherKey[4-i][7];
		
		int count = 1;
		for (int i = 0; i < 6; i++) {
			temp = scheduleCore(temp, count);
			count++;
			for(int j = 0; j < 4; j++) {
				temp[j] ^= extendedKey[j][(i*8)];
				temporaryKey[j][0] = temp[j];
			}
			for(int j = 0; j < 3; j++){
				int index = j + 1 + (i*8);
				for(int k = 0; k < 4; k++) {
					temp[k] ^= extendedKey[k][index];
					temporaryKey[k][j+1] = temp[k];
				}
			}
			for(int j = 0; j < 4; j++) {
				temp[j] = Utility.subBytesHelper(temp[j],true);
				temp[j] ^= extendedKey[j][(i*8)+4];
				temporaryKey[j][4] = temp[j];
			}
			for(int j = 0; j < 3; j++){
				int index = j + 5 + (i*8);
				for(int k = 0; k < 4; k++) {
					temp[k] ^= extendedKey[k][index];
					temporaryKey[k][j+5] = temp[k];
				}
			}
			for(int j = 0; j < temporaryKey.length; j++){
				for(int k = 0; k < temporaryKey[0].length; k++) {
					extendedKey[j][k + ((i+1)*8)] = temporaryKey[j][k];
				}
			}
		}
		temp = scheduleCore(temp, count);
		count++;
		for(int j = 0; j < 4; j++) {
			temp[j] ^= extendedKey[j][(6*8)];
			temporaryKey[j][0] = temp[j];
		}
		for(int j = 0; j < 3; j++){
			int index = j + 1 + (6*8);
			for(int k = 0; k < 4; k++) {
				temp[k] ^= extendedKey[k][index];
				temporaryKey[k][j+1] = temp[k];
			}
		}
		for(int j = 0; j < temporaryKey.length; j++)
			for(int k = 0; k < temporaryKey[0].length/2; k++) 
				extendedKey[j][k + (7*8)] = temporaryKey[j][k];
			
		//System.out.print(toString(extendedKey));
		return extendedKey;
	}
	public static int[] scheduleCore(int[] input, int count){
	    int a = input[0];
	    for(int i = 0; i < 3; i++) 
	    	input[i] = input[i + 1];
	    input[3] = a;
		for(int i = 0; i < 4; i++)
			input[i] = Utility.subBytesHelper(input[i], true);
		
		input[0] ^= rcon(count);
		return input;
	}
	public static int rcon(int in) {
        int c=1;
        if(in == 0)  
        	return 0; 
        while(in != 1) {
			int b;
			b = c & 0x80;
			c <<= 1;
			if(b == 0x80) {
				c ^= 0x1b;
			}
			in--;
        }
        return c;
	}
	
	public static String toString(int[][] M) {
	    
	    StringBuilder result = new StringBuilder();
	    
	    for (int i = 0; i < M.length; i++) {
	        for(int j = 0; j < M[i].length; j++){
	            result.append(Integer.toHexString(M[i][j]));
	            if(j>0 && j%8 == 0)
	            	result.append("  ");
	        }
	        result.append("\n");
	    }
	    return result.toString();
	}
}
