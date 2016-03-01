/*Author: Chris Brown
* Date: 16/11/15
* Description: A main class used to test a DiffieHellman class*/
package Part2.Exercise3;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void simulateKeyExchange(){
        try{
            FileWriter log = new FileWriter("src/Part2/Exercise3/KeyExchangeLog.txt");

            String secretA = "34";
            String secretB = "27";

            //Create two people with secret values
            Person alice = new Person(new BigInteger(secretA));
            Person bob = new Person(new BigInteger(secretB));

            log.write("secretA = " + secretA + "\n");
            log.write("secretB = " + secretB + "\n");

            //generate a base and modulus
            BigInteger base = DiffieHellman.genRandomPrime(64);
            BigInteger modulus = DiffieHellman.genRandomPrime(1024);

            //agree on a generator and a prime modulus
            DiffieHellman dh = new DiffieHellman(alice, bob, base, modulus);

            //msg1 A->B
            BigInteger[] msg1 = dh.genAtoBMessage();
            log.write("msg1.modulus = " + msg1[0] + "\n");
            log.write("msg1.base = " + msg1[1] + "\n");
            log.write("msg1.valueA = " + msg1[2] + "\n");

            //msg1 B->A
            BigInteger msg2 = dh.genBtoAMessage(msg1);
            log.write("msg2.valueB = " + msg2 + "\n");

            //keyA
            BigInteger keyA = dh.genKeyA(msg2);
            alice.setKey(keyA);
            log.write("keyA = " + keyA + "\n");

            //keyB
            BigInteger keyB = dh.genKeyB(msg1);
            bob.setKey(keyB);
            log.write("keyB = " + keyB + "\n");

            log.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void simulateMIMAttack(){
        try{
            FileWriter log = new FileWriter("src/Part2/Exercise3/MIMAttackLog.txt");

            String secretA = "34";
            String secretB = "27";

            String attackSecretA = "13";
            String attackSecretB = "43";

            //Create two people with secret values + attacker(MIM)
            Person alice = new Person(new BigInteger(secretA));
            Person bob = new Person(new BigInteger(secretB));
            Attacker eve = new Attacker(new BigInteger(attackSecretA), new BigInteger(attackSecretB));

            log.write("secretA = " + secretA + "\n");
            log.write("secretB = " + secretB + "\n");
            log.write("attackSecretA = " + attackSecretA + "\n");
            log.write("attackSecretB = " + attackSecretB + "\n");

            //generate a base and modulus
            BigInteger base = DiffieHellman.genRandomPrime(64);
            BigInteger modulus = DiffieHellman.genRandomPrime(1024);

            //agree on a generator and a prime modulus
            DiffieHellman dh = new DiffieHellman(alice, bob, base, modulus);

            //msg1 A->attacker
            BigInteger[] msg1 = dh.genAtoBMessage();
            log.write("msg1.modulus = " + msg1[0] + "\n");
            log.write("msg1.base = " + msg1[1] + "\n");
            log.write("msg1.valueA = " + msg1[2] + "\n");
            log.write("\n!!!Message intercepted by attacker!!!\n\n");

            //atkMsg2 attacker->A
            BigInteger atkMsg2 = eve.genModPow(msg1[1], msg1[0]);
            log.write("Attacker sends message back to 'A' using own secret value\n");
            log.write("atkMsg2.valueB = " + atkMsg2 +"\n");

            //keyA
            BigInteger keyA = dh.genKeyA(atkMsg2);
            alice.setKey(keyA);
            log.write("keyA = " + keyA + "\n");

            //atkKeyA
            BigInteger atkKeyA = eve.genModPow(msg1[2], msg1[0]);
            eve.setKey(atkKeyA);
            log.write("atkKeyA = " + atkKeyA + "\n");

            //altered msg1 attacker->B
            log.write("\nAttacker sends altered msg1 to 'B'\n");
            BigInteger[] atkMsg1 = msg1;
            atkMsg1[2] = eve.genExtraModPow(atkMsg1[1], atkMsg1[0]);
            log.write("atkMsg1.valueA = " + atkMsg1[2] + "\n");

            //msg2 B->attacker
            BigInteger msg2 = dh.genBtoAMessage(msg1);
            log.write("msg2.valueB = " + msg2 + "\n");
            log.write("attacker receives valueB from 'B'\n");

            //keyB
            BigInteger keyB = dh.genKeyB(atkMsg1);
            bob.setKey(keyB);
            log.write("keyB = " + keyB + "\n");

            //atkKeyB
            BigInteger atkKeyB = eve.genExtraModPow(msg2, atkMsg1[0]);
            eve.setExtraKey(atkKeyB);
            log.write("atkKeyB = " + atkKeyB + "\n");

            log.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        simulateKeyExchange();
        simulateMIMAttack();
    }
}
