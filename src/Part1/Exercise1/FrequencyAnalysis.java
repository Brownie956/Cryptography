/*Author: Chris Brown
* Date: 14/10/15
* Description: A frequency analysis class that provides the functionality to calculate
* the frequency of English letters within a given file and decrypt a Caesar Cipher
* using a given key*/
package Part1.Exercise1;

import java.io.*;
import java.util.HashMap;
import java.util.Map.*;

public class FrequencyAnalysis {

    private Double round(double value, int decimalPlaces){
        double factor = Math.pow(10, decimalPlaces);
        double val = value * factor;
        long rounded = Math.round(val);

        return (double) rounded / factor;
    }

    public HashMap<Character,Integer> getLetterFrequency(File file){
        HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while((line = br.readLine()) != null){
                for(int i = 0; i < line.length(); i++){
                    char Char = line.toLowerCase().charAt(i);
                    if(Character.isLetter(Char)){
                        if(letters.get(Char) == null) letters.put(Char, 1);
                        else letters.put(Char, letters.get(Char) + 1);
                    }
                }
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return letters;
    }

    public HashMap<Character,Integer> getLetterFrequency(String text){
        HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
        for(int i = 0; i < text.length(); i++){
            char Char = text.toLowerCase().charAt(i);
            if(Character.isLetter(Char)){
                if(letters.get(Char) == null) letters.put(Char, 1);
                else letters.put(Char, letters.get(Char) + 1);
            }
        }
        return letters;
    }

    public HashMap getRelativeLetterFrequency(File file) {
        HashMap<Character, Integer> lettersFrequency = getLetterFrequency(file);
        HashMap<Character, Double> lettersRelativeFrequency = new HashMap<Character, Double>();

        //get total
        int total = 0;
        for(Entry<Character, Integer> entry : lettersFrequency.entrySet()){
            total += entry.getValue();
        }

        //Create map of relative figures
        for(Entry<Character, Integer> entry : lettersFrequency.entrySet()){
            //round to 3 decimal places
            double percentage = ((double) entry.getValue()) / total * 100;
            lettersRelativeFrequency.put(entry.getKey(), round(percentage, 3));
        }
        return lettersRelativeFrequency;
    }

    public HashMap getRelativeLetterFrequency(String text) {
        HashMap<Character, Integer> lettersFrequency = getLetterFrequency(text);
        HashMap<Character, Double> lettersRelativeFrequency = new HashMap<Character, Double>();

        //get total
        int total = 0;
        for(Entry<Character, Integer> entry : lettersFrequency.entrySet()){
            total += entry.getValue();
        }

        //Create map of relative figures
        for(Entry<Character, Integer> entry : lettersFrequency.entrySet()){
            //round to 3 decimal places
            double percentage = ((double) entry.getValue()) / total * 100;
            lettersRelativeFrequency.put(entry.getKey(), round(percentage, 3));
        }
        return lettersRelativeFrequency;
    }

    public void decryptCaesar(int shift, String inputFilePath, String outputFilePath){
        File outputFile = new File(outputFilePath);
        try{
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            StringBuilder plainText = new StringBuilder();
            FileWriter fw = new FileWriter(outputFile);

            shift = shift % 26;
            String line;
            while((line = br.readLine()) != null){
                for(int i = 0; i < line.length(); i++){
                    char Char = line.toLowerCase().charAt(i);
                    if(Character.isLetter(Char)){
                        if(Char - shift <  'a') Char += 26;
                        plainText.append((char)(Char - shift));
                    } else plainText.append(Char);
                }
                plainText.append('\n');
            }

            br.close();
            outputFile.createNewFile();
            fw.write(plainText.toString());
            fw.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String decryptCaesar(int shift, String cipherInput){
        StringBuilder plainText = new StringBuilder();

        shift = shift % 26;
        for(int i = 0; i < cipherInput.length(); i++){
            char Char = cipherInput.toLowerCase().charAt(i);
            if(Character.isLetter(Char)){
                if(Char - shift <  'a') Char += 26;
                plainText.append((char)(Char - shift));
            } else plainText.append(Char);
        }
        plainText.append('\n');
        return plainText.toString();
    }
}
