/*Author: Chris Brown
* Date: 15/11/15
* Description: A class to aid in solving linear equations*/
package Part2.Exercise2;

import Part2.Exercise1.Euclidean;

import java.math.BigInteger;

public class Linear {

    public static BigInteger solve(BigInteger a, BigInteger b, BigInteger n){

        //Is solution unsolvable?
        if(a.equals(BigInteger.ZERO) || b.equals(BigInteger.ZERO) || !Euclidean.solveToArray(a,n)[0].equals(BigInteger.ONE)) return null;
        else{
            BigInteger answer = BigInteger.ZERO;
            //ax + b = 0

            //Find the multiplicative inverse of 'a'
            BigInteger inv = Euclidean.solveToArray(a,n)[1].mod(n);

            //subtract b -> ax = -b
            answer = answer.subtract(b).mod(n);

            //multiply by the inverse ->? x = (-b)a^-1
            answer = answer.multiply(inv).mod(n);

            return answer;
        }
    }
}
