package Part1;/*
  A sample program to attack the ciphertexts that were generated by XORing an array of plaintext
  with the same one-time pad key. 
*/

public class OTPAttack 
{

   public static String bytesToHex(byte[] bytes) {
	    final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	    char[] hexChars = new char[bytes.length * 2];
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
   
   	public static byte[] hexStringToByteArray(String s) {
	    byte[] b = new byte[s.length() / 2];
	    for (int i = 0; i < b.length; i++) {
	      int index = i * 2;
	      int v = Integer.parseInt(s.substring(index, index + 2), 16);
	      b[i] = (byte) v;
	    }
	    return b;
	  }

    public static void main(String[] args)throws Exception
    {       
       String[] ciphertext = {
    		   "dcb68a9df8f716409ba0fb55ee3fc8b91f090177976e0961",
    		   "d4e2c992e9a11e53c2f2f653ef27c8ba1e5d403e882717699d852c",
    		   "d1ff8299acb11a558ae5e51aed3d83bd4b5a0f39",
    		   "d0f78785acb65b4d8bf4e356e47485b9004c13779d270a6f8b9c3314",
    		   "c9fe8cdcf8a50e558aa0e053ed38c8b71e5d",
    		   "c9f9c999fab2095896e8fe54e6749cb00e5b057795744767c8853a1069c6be",
    		   "c4f99c88e4f71252c2f7f649f5318cf8044740239462477f87823116",
    		   "c4f99cdce4b60d44c2e4f854e47491b71e5b402093750c"    		   
       };
    
        try{ 

            /* Attack */
            for (int c=0; c<ciphertext.length; c++) {
              for (int i=0; i<ciphertext.length; i++) {
                if (c != i) {
                 System.out.print((c+1)+":"+(i+1) + "\t");
                 
                 byte [] data1 = hexStringToByteArray(ciphertext[i]);
                 byte [] data2 = hexStringToByteArray(ciphertext[c]);

                 int min = Math.min(data1.length, data2.length);
                 int max = Math.max(data1.length, data2.length);
                 
                 for (int j=0; j<min; j++) {
                    byte xor = (byte)( (data1[j] ^ data2[j]) & 0xff);
                    
                    if ( (xor >= 'A' && xor <= 'Z') || (xor >='a' && xor <'z') ) {
                    	System.out.print((char)(xor & 0xff));
                    }
                    else System.out.print("-");
                  }
                 
                 for (int j=min; j<max; j++){
                	 System.out.print("-");
                 }
                  System.out.println();
                }
              }
            }

         } catch (Exception e) {

           System.out.println("Error: "+e);
           System.exit(1);
         }

  
    }
}