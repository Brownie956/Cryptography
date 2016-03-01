/*Author: Chris Brown
* Date: 15/11/15
* Description: A class that implements a Diffie-Hellman key exchange*/
package Part2.Exercise3;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman {

    private Person alice;
    private Person bob;
    private BigInteger base;
    private BigInteger modulus;

    public DiffieHellman(Person a, Person b, BigInteger base, BigInteger modulus){
        this.alice = a;
        this.bob = b;
        this.base = base;
        this.modulus = modulus;
    }

    public static BigInteger genRandomPrime(int bitLength){
        return BigInteger.probablePrime(bitLength, new SecureRandom());
    }


    public BigInteger[] genAtoBMessage(){
        //Generate msg (modulus, base, ValueA) using personA's secret value
        BigInteger[] msg = new BigInteger[3];
        msg[0] = modulus;
        msg[1] = base;
        msg[2] = alice.genModPow(base, modulus);

        return msg;
    }

    public BigInteger genBtoAMessage(BigInteger[] msgAB){
        //Generate msg (ValueB) using personB's secret value
        return bob.genModPow(msgAB[1], msgAB[0]);
    }

    public BigInteger genKeyA(BigInteger msgBA){
        //Generate key using personA's secret value
        return alice.genModPow(msgBA, modulus);
    }

    public BigInteger genKeyB(BigInteger[] msgAB){
        //Generate key using personB's secret value
        return bob.genModPow(msgAB[2], modulus);
    }

}
