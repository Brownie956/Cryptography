/*Author: Chris Brown
* Date: 21/10/15
* Description: A Vigerere Cipher class that provides functionality to
* encrypt and decrypt text using a Vigerere Cipher*/
package Part1.Exercise2;

import java.io.*;

public class Vigenere {

    public void encrypt(String key, String inputFilePath, String outputFilePath) {
        File outputFile = new File(outputFilePath);
        StringBuilder cipherText = new StringBuilder();
        key = key.toLowerCase();

        try{
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            FileWriter fw = new FileWriter(outputFile);

            String line;
            while((line = br.readLine()) != null){
                for(int i = 0; i < line.length(); i++){
                    char Char = line.toLowerCase().charAt(i);

                    if(Character.isLetter(Char)){
                        int shift = key.charAt(i % key.length()) % 'a';

                        if(Char + shift > 'z') Char -= 26;

                        cipherText.append((char)(Char + shift));
                    } else cipherText.append(Char);
                }
                cipherText.append('\n');
            }

            br.close();
            outputFile.createNewFile();
            fw.write(cipherText.toString());
            fw.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String encrypt(String key, String text) {
        StringBuilder cipherText = new StringBuilder();
        key = key.toLowerCase();

        for(int i = 0; i < text.length(); i++){
            char Char = text.toLowerCase().charAt(i);

            if(Character.isLetter(Char)){
                int shift = key.charAt(i % key.length()) % 'a';
                if(Char + shift > 'z') Char -= 26;

                cipherText.append((char)(Char + shift));
            } else cipherText.append(Char);
        }
        return cipherText.toString();
    }

    public void decrypt(String key, String inputFilePath, String outputFilePath) {
        File outputFile = new File(outputFilePath);
        StringBuilder plainText = new StringBuilder();
        key = key.toLowerCase();

        try{
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            FileWriter fw = new FileWriter(outputFile);

            String line;
            while((line = br.readLine()) != null){
                for(int i = 0; i < line.length(); i++){
                    char Char = line.toLowerCase().charAt(i);

                    if(Character.isLetter(Char)){
                        int shift = key.charAt(i % key.length()) % 'a';

                        if(Char - shift < 'a') Char += 26;

                        plainText.append((char) (Char - shift));
                    } else plainText.append(Char);
                }
                plainText.append('\n');
            }

            br.close();
            outputFile.createNewFile();
            fw.write(plainText.toString());
            fw.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String decrypt(String key, String cipherText) {
        StringBuilder plainText = new StringBuilder();
        key = key.toLowerCase();

        for(int i = 0; i < cipherText.length(); i++){
            char Char = cipherText.toLowerCase().charAt(i);

            if(Character.isLetter(Char)){
                int shift = key.charAt(i % key.length()) % 'a';

                if(Char - shift < 'a') Char += 26;

                plainText.append((char) (Char - shift));
            } else plainText.append(Char);
        }
        return plainText.toString();
    }

    private int letterFrequency(char letter, String text){
        int count = 0;
        for(int i = 0; i < text.length(); i++){
            char Char = text.charAt(i);
            if(!Character.isLetter(Char)) continue;
            if(Char == letter) count++;
        }
        return count;
    }

    public double indexOfCoin(String input){
        input = input.toLowerCase();
        double sum = 0;
        for(int i = 0; i < 26; i++){
            char letter = (char)('a' + i);
            sum += (letterFrequency(letter, input)) * ((letterFrequency(letter, input))-1);
        }

        return sum / (input.length() * (input.length() - 1));
    }

    public String[] getCol(String text, int keyLengthGuess){
        String[] cols = new String[keyLengthGuess];

        for(int j = 0; j < keyLengthGuess; j++){
            StringBuilder sb = new StringBuilder();
            for(int i = j; i < text.length(); i += keyLengthGuess){
                sb.append(text.charAt(i));
            }
            cols[j] = sb.toString();
        }
        return cols;
    }
}
