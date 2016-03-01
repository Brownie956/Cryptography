/*Author: Chris Brown
* Date: 24/10/15
* Description: A class to encrypt and decrypt a one time pad
* The class also gives the functionality to generate a one time pad
* and assist in attacking a one time pad*/
package Part1.Exercise3;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

public class OneTimePad {

    final int MB = 1024 * 1024;

    public String generateOTP(){
        byte[] otp = new byte[MB];
        new SecureRandom().nextBytes(otp);
        return toHex(otp);
    }

    public String toHex(byte[] byteArray){
        return HexBin.encode(byteArray);
    }

    private byte[] toByteArray(String hex){
        byte[] byteArray = new byte[hex.length() / 2];
        for (int i = 0; i < byteArray.length; i++){
            int index = i * 2;
            int tempInt = Integer.parseInt(hex.substring(index, index + 2), 16);
            byteArray[i] = (byte) tempInt;
        }
        return byteArray;
    }

    public String encrypt(String text, String key){
        byte[] textBytes = text.getBytes();
        byte[] keyBytes = toByteArray(key);

        byte[] xorResult = new byte[textBytes.length];

        for(int i = 0; i < textBytes.length; i++){
            xorResult[i] = (byte)(textBytes[i] ^ keyBytes[i]);
        }

        return toHex(xorResult);
    }

    private byte[] encrypt(byte[] text, String key){
        byte[] keyBytes = toByteArray(key);

        byte[] xorResult = new byte[text.length];

        for(int i = 0; i < text.length; i++){
            xorResult[i] = (byte)(text[i] ^ keyBytes[i]);
        }

        return xorResult;
    }

    public String decryptToString(String cipher, String key){
        byte[] cipherBytes = toByteArray(cipher);
        String result = "";
        try{
            result = new String(encrypt(cipherBytes, key), "UTF-8");
        }
        catch(UnsupportedEncodingException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public String decryptToHex(String cipher, String key){
        byte[] cipherBytes = toByteArray(cipher);
        return toHex(encrypt(cipherBytes, key));
    }

    public void otpCribDraggingAttack(String guess, String[] ciphers, int smlCipherIndex, int lrgCipherIndex){
        byte[] guessBytes = guess.getBytes();
        byte[] cipherxor = encrypt(toByteArray(ciphers[smlCipherIndex]), ciphers[lrgCipherIndex]);

        byte[] result = new byte[guessBytes.length];

        //xor guess at all positions in cipher xor
        for(int j = 0; j<cipherxor.length - guessBytes.length; j++){
            for(int i = 0; i < guessBytes.length; i++){
                result[i] = (byte)(guessBytes[i] ^ cipherxor[i+j]);
            }

            try {
                System.out.println("j=" + j + "---:" + new String(result, "UTF-8"));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
