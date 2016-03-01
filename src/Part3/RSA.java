/*Author: Chris Brown
* Date: 6/12/2015
* Description: A class to model RSA*/
package Part3;

import java.math.BigInteger;

public class RSA {

    private static final BigInteger THREE = BigInteger.valueOf(3);

    public static BigInteger decryptExp3(BigInteger cipher)
    {
        // Implementation based on Section 1.7.1 of
        // "A Course in Computational Algebraic Number Theory"
        // by Henri Cohen.
        //NOTE: This implementation was taken from
        // http://codereview.stackexchange.com/questions/56719/followup-how-do-i-optimize-this-java-cube-root-function-for-biginteger
        BigInteger x = BigInteger.ZERO.setBit(cipher.bitLength() / 3 + 1);
        while (true) {
            BigInteger y = x.shiftLeft(1).add(cipher.divide(x.multiply(x))).divide(THREE);
            if (y.compareTo(x) >= 0) {
                break;
            }
            x = y;
        }
        return x;
    }

    public static String decodeASCII(String val) {

        //Storage container for the decrypted letters
        int[] ascii = new int[val.length()/2];

        //For every 2 digits, get the ascii value and store
        for(int i = 0; i < val.length() - 1; i+=2){
            StringBuilder sb = new StringBuilder();
            sb.append(val.charAt(i));
            sb.append(val.charAt(i+1));
            String letter = sb.toString();
            ascii[i/2] = Integer.parseInt(letter);
        }

        //Create string from the characters decrypted
        StringBuilder sb = new StringBuilder();
        for(int x : ascii){
            sb.append(Character.toString((char) x));
        }
        return sb.toString();
    }
}
