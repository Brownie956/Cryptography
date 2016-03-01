/*Author: Chris Brown
* Date: 15/11/15
* Description: A class to represent a person used to test a
* Diffie-Hellman key exchange class*/
package Part2.Exercise3;

import java.math.BigInteger;

public class Person {

    private BigInteger secret, key;

    public Person(){
        this.secret = null;
        this.key = null;
    }

    public Person(BigInteger secret){
        this.secret = secret;
        this.key = null;
    }

    public BigInteger genModPow(BigInteger base, BigInteger mod){ return base.modPow(secret, mod); }
    public void setKey(BigInteger key){ this.key = key; }
}
