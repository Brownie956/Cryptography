/*Author: Chris Brown
* Date: 15/11/15
* Description: Main class to for testing the Linear Class functionality*/
package Part2.Exercise2;

import java.io.*;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args){

        String[] vals = new String[3];
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/Part2/Exercise2/input1.txt"));
            for(int i = 0; i<vals.length; i++){
                String line = br.readLine();
                vals[i] = line.split("=")[1];
            }
            br.close();

            FileWriter fr = new FileWriter("src/Part2/Exercise2/linearOutput1.txt");

            BigInteger n = new BigInteger(vals[0]);
            BigInteger a = new BigInteger(vals[1]);
            BigInteger b = new BigInteger(vals[2]);

            BigInteger solution = Linear.solve(a,b,n);

            if(solution != null) fr.write(solution.toString());
            else fr.write("null");

            fr.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
