/*Author: Chris Brown
Date: 14/11/15
* Description: A main class for testing the Euclidean class functionality*/
package Part2.Exercise1;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args){
        BigInteger x = new BigInteger("1572855870797393");
        BigInteger y = new BigInteger("630065648824575");

        Euclidean.solveToFile(x,y);
    }
}
