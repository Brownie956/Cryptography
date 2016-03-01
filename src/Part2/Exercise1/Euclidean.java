/*Author: Chris Brown
* Date: 14/11/15
* Description: A class the provides functionality to solve linear equations*/
package Part2.Exercise1;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Euclidean {

    private static BigInteger[] extEuclidAlgorithm(BigInteger x, BigInteger y){

        BigInteger[] solution = new BigInteger[3];
        BigInteger d;
        BigInteger s;
        BigInteger t;
        BigInteger q;

        //Can't do gcd if y = 0
        if(y.intValue() == 0){
            d = x;
            s = new BigInteger("1");
            t = new BigInteger("0");

            solution[0] = d;
            solution[1] = s;
            solution[2] = t;
        }
        else{
            //Get quotient
            q = x.divide(y);

            //Backtrack through the equations
            solution = extEuclidAlgorithm(y, x.mod(y));

            BigInteger temp = solution[1].subtract(solution[2].multiply(q));
            solution[1] = solution[2];
            solution[2] = temp;
        }

        return solution;
    }

    public static void solveToFile(BigInteger x, BigInteger y){
        //Solve
        BigInteger[] answer = extEuclidAlgorithm(x,y);

        //Write to file
        try{
            FileWriter fr = new FileWriter("src/Part2/Exercise1/extEuclidOutput.txt");

            fr.write("d = " + answer[0] + "\n");
            fr.write("s = " + answer[1] + "\n");
            fr.write("t = " + answer[2] + "\n");

            fr.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static BigInteger[] solveToArray(BigInteger x, BigInteger y){
        return extEuclidAlgorithm(x,y);
    }
}
