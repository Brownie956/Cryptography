/*Author: Chris Brown
Date: 21/10/15
Description: Main class for testing cryptography outputs*/
package Part1;

import Part1.Exercise1.FrequencyAnalysis;
import Part1.Exercise2.Vigenere;
import Part1.Exercise3.OneTimePad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){

        //Change to relevant to true to run code
        boolean frequency = false;
        boolean vigenere = false;
        boolean oneTimePad = false;

        if(frequency){
            String plainInputPath = System.getProperty("user.dir") + "/src/Part1/Exercise1/cw-plain-input.txt";
            String cipherInputPath = System.getProperty("user.dir") + "/src/Part1/Exercise1/cw-cipher-input.txt";
            String outputPath = System.getProperty("user.dir") + "/src/Part1/Exercise1/cipher-output.txt";

            FrequencyAnalysis fa = new FrequencyAnalysis();

            HashMap letterFrequency = fa.getLetterFrequency(plainInputPath);
            System.out.println("Plain text letter frequency\n" + letterFrequency);

            HashMap plainRelativeLetterFrequency = fa.getRelativeLetterFrequency(plainInputPath);
            System.out.println("Plain text relative letter frequency\n" + plainRelativeLetterFrequency);

            HashMap cipherRelativeLetterFrequency = fa.getRelativeLetterFrequency(cipherInputPath);
            System.out.println("Cipher text relative letter frequency\n" + cipherRelativeLetterFrequency);


            fa.decryptCaesar(4, cipherInputPath, outputPath);
        }

        if(vigenere){
            String cwPlainInput = "./src/Part1.Exercise2/cw-plain-input.txt";
            String cwPlainEncryptedOutput = "./src/Part1.Exercise2/cw-plain-encrypted-output.txt";
            String cwPlainDecryptedOutput = "./src/Part1.Exercise2/cw-plain-decrypted-output.txt";
            String cwCipherInput = "./src/Part1.Exercise2/cw-cipher-input.txt";
            String cwCipherOutput = "./src/Part1.Exercise2/cw-cipher-output.txt";

            Vigenere vg = new Vigenere();
            FrequencyAnalysis fa = new FrequencyAnalysis();
            StringBuilder sb = new StringBuilder();

            //ncl - newcastleuniversity - ENCRYPTION/DECRYPTION
            System.out.println("Key: ncl , Text: newcastleuniversity\nEncryption Output: " + vg.encrypt("ncl", "newcastleuniversity") + "\n");
            System.out.println("Key: ncl , Cipher text: aghpcdgnphptigcfkel\nDecryption Output: " + vg.decrypt("ncl", "aghpcdgnphptigcfkel") + "\n");
            //abc - cw plain - ENCRYPTION/DECRYPTION
            vg.encrypt("abc", cwPlainInput, cwPlainEncryptedOutput);
            vg.decrypt("abc", cwPlainEncryptedOutput, cwPlainDecryptedOutput);

            System.out.println("Cipher text relative frequencies\n" + fa.getRelativeLetterFrequency(cwPlainEncryptedOutput));
            //cw cipher text decryption
            try{
                //read file into a string
                BufferedReader br = new BufferedReader(new FileReader(cwCipherInput));
                String line;
                while((line = br.readLine()) != null) sb.append(line);
                br.close();
                String input = sb.toString();

                //Break string up into columns based on key length
                int keyLength = 5;
                String[] sa = vg.getCol(input, keyLength);
                System.out.println("Key length guess: " + keyLength);
                //Index of coincidence for each column
                for(String col : sa) System.out.println(vg.indexOfCoin(col));

                //for every column solve shift cipher
                for(String col: sa) System.out.println(fa.getRelativeLetterFrequency(col) + "\n");

                //KEY = plato
                vg.decrypt("plato", cwCipherInput, cwCipherOutput);

            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }

        if(oneTimePad){
            OneTimePad otp = new OneTimePad();

            //Change to true in order to run that test
            boolean generator = false;
            boolean encryptDecrypt = false;
            boolean attack = false;

            /*************************
            * ONE TIME PAD GENERATOR *
            *************************/
            if(generator){
                System.out.println(otp.generateOTP());
            }

            /****************************
            * ENCRYPTION AND DECRYPTION *
            ****************************/
            if(encryptDecrypt){

                /*Basic encryption/ decryption test*/
                String text = "Every cloud has a silver lining";
                String key = "6dc72fc595e35dcd38c05dca2a0d2dbd8e2df20b129b2cfa29ad17972922a2";

                String encrypted = otp.encrypt(text, key);
                System.out.println("Cipher text:\n" + encrypted);

                String decrypted = otp.decryptToString(encrypted, key);
                System.out.println("Decrypted plain text:\n" + decrypted);

                /*One time pad generator test*/

                String joke = "I never wanted to believe that my Dad was stealing from his job as a road worker. But when I got home, all the signs were there. ";
                String jokeKey = otp.generateOTP();

                System.out.println(jokeKey);

                String encryptedJoke = otp.encrypt(joke, jokeKey);
                System.out.println("Encrypted Joke:\n" + encryptedJoke);

                String decryptedJoke = otp.decryptToString(encryptedJoke, jokeKey);
                System.out.println("Decrypted Joke:\n" + decryptedJoke);
            }

            /**********************
            * ONE TIME PAD ATTACK *
            **********************/
            if(attack){
                String[] ciphertext = {
                        "dcb68a9df8f716409ba0fb55ee3fc8b91f090177976e0961", //a cat may look at a king
                        "d4e2c992e9a11e53c2f2f653ef27c8ba1e5d403e882717699d852c", //it never rains but it pours
                        "d1ff8299acb11a558ae5e51aed3d83bd4b5a0f39", //like father like son
                        "d0f78785acb65b4d8bf4e356e47485b9004c13779d270a6f8b9c3314", //many a little makes mickle
                        "c9fe8cdcf8a50e558aa0e053ed38c8b71e5d", //the truth will out
                        "c9f9c999fab2095896e8fe54e6749cb00e5b057795744767c8853a1069c6be", //to everything there is a reason
                        "c4f99c88e4f71252c2f7f649f5318cf8044740239462477f87823116", //youth is wasted on the young
                        "c4f99cdce4b60d44c2e4f854e47491b71e5b402093750c" //you have done your work
                };

                String[] plaintext = {
                        "a cat may look at a king",
                        "it never rains but it pours",
                        "like father like son",
                        "many a little makes a mickle",
                        "the truth will out",
                        "to everything there is a reason",
                        "youth is wasted on the young",
                        "you have done your work"
                };

                //Recover the key
                String potentialKey = otp.decryptToHex(otp.toHex("to everything there is a reason".getBytes()), ciphertext[5]);
                System.out.println(potentialKey);

                //Check this key is correct
                int i = 0;
                for(String str: plaintext){
                    String encr = otp.encrypt(str, potentialKey).toLowerCase();
                    System.out.println(encr.equals(ciphertext[i]));
                    i = i + 1;
                }

                //Decrypt target cipher
                String test = otp.decryptToString(ciphertext[7], potentialKey);
                System.out.println(test);

                //OTP ATTACK OUTPUT
                otp.otpCribDraggingAttack(" but ", ciphertext, 0, 1);
            }
        }
    }
}
