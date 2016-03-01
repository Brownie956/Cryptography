/*Author: Chris Brown
* Date: 16/11/15
* Description: An extension of the Person class to model a 'man in the middle' attacker
* in a Diffie-Hellman key exchange*/
package Part2.Exercise3;

import java.math.BigInteger;

public class Attacker extends Person{

    private BigInteger extraSecret, extraKey;

    public Attacker(){
        this.extraSecret = null;
        this.extraKey = null;
    }

    public Attacker(BigInteger secretA, BigInteger secretB){
        super(secretA);
        this.extraSecret = secretB;
        this.extraKey = null;
    }

    public void setExtraKey(BigInteger extraKey){
        this.extraKey = extraKey;
    }

    public BigInteger genExtraModPow(BigInteger base, BigInteger mod){ return base.modPow(extraSecret, mod); }
}
