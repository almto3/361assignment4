
public class MixColumns {


    ////////////////////////  the mixColumns Transformation ////////////////////////
    
    private static byte mul (int a, byte b) {
		int inda = (a < 0) ? (a + 256) : a;
		int indb = (b < 0) ? (b + 256) : b;
	
		if ( (a != 0) && (b != 0) ) {
		    int index = (Examples.LogTable[inda] + Examples.LogTable[indb]);
		    byte val = (byte)(Examples.AlogTable[ index % 255 ] );
		    return val;
		}
		else 
		    return 0;
    } // mul

    // In the following two methods, the input c is the column number in
    // your evolving state matrix st (which originally contained 
    // the plaintext input but is being modified).  Notice that the state here is defined as an
    // array of bytes.  If your state is an array of integers, you'll have
    // to make adjustments. 

    protected static int[][] mixColumn2 (int[][] array, int c) {
    	// This is another alternate version of mixColumn, using the 
    	// logtables to do the computation.
	
		byte a[] = new byte[4];
		byte st[][] = Utility.tobyteArray(array);
		
		// note that a is just a copy of st[.][c]
		for (int i = 0; i < 4; i++) 
		    a[i] = st[i][c];
		
		// This is exactly the same as mixColumns1, if 
		// the mul columns somehow match the b columns there.
		st[0][c] = (byte)(mul(2,a[0]) ^ a[2] ^ a[3] ^ mul(3,a[1]));
		st[1][c] = (byte)(mul(2,a[1]) ^ a[3] ^ a[0] ^ mul(3,a[2]));
		st[2][c] = (byte)(mul(2,a[2]) ^ a[0] ^ a[1] ^ mul(3,a[3]));
		st[3][c] = (byte)(mul(2,a[3]) ^ a[1] ^ a[2] ^ mul(3,a[0]));
		
		return Utility.tointArray(st);
    } // mixColumn2

    protected static int[][] invMixColumn2 (int[][] array, int c) {
		byte a[] = new byte[4];
		byte st[][] = Utility.tobyteArray(array);
		
		// note that a is just a copy of st[.][c]
		for (int i = 0; i < 4; i++) 
		    a[i] = st[i][c];
		
		st[0][c] = (byte)(mul(0xE,a[0]) ^ mul(0xB,a[1]) ^ mul(0xD, a[2]) ^ mul(0x9,a[3]));
		st[1][c] = (byte)(mul(0xE,a[1]) ^ mul(0xB,a[2]) ^ mul(0xD, a[3]) ^ mul(0x9,a[0]));
		st[2][c] = (byte)(mul(0xE,a[2]) ^ mul(0xB,a[3]) ^ mul(0xD, a[0]) ^ mul(0x9,a[1]));
		st[3][c] = (byte)(mul(0xE,a[3]) ^ mul(0xB,a[0]) ^ mul(0xD, a[1]) ^ mul(0x9,a[2]));
		
		return Utility.tointArray(st);
    } // invMixColumn2
}
