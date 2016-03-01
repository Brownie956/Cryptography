/*Author: Chris Brown
* Date: 6/12/2015
* Description: Main class used for testing RSA class*/
package Part3;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args){

        //Test values
        BigInteger c = new BigInteger("674472526620593903800497637242400187916753185909");
        BigInteger n = new BigInteger("23095675100376460353980581297675223373026833410647478222648288977449481620360427");

        //Decrypted to Big Integer
        BigInteger m = RSA.decryptExp3(c);

        //Write answer to a file
        try{
            FileWriter fw = new FileWriter("src/Part3/RSAAttackOutput.txt");
            fw.write("Decrypted message: \n" + RSA.decodeASCII(m.toString()));
            fw.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
