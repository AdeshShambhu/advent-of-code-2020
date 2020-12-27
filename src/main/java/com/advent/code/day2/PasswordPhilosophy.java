package com.advent.code.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Day 2 of Advent of Code 2020 (https://adventofcode.com/)
 * Password Philosophy
 * Valid Passwords are the passwords which satisfy the company policy.
 * Part 1 => Count the total number of valid passwords in the input file
 *           (Each line gives the password policy and then the password.
 *           The password policy indicates the lowest and highest number of times
 *           a given letter must appear for the password to be valid)
 * Part 2 => Count the total number of valid passwords in the input file
 *           (Each policy actually describes two positions in the password,
 *           Exactly one of these positions must contain the given letter)
 * Input file => resources/input/Day2PasswordPhilosophy.txt => contains the list of passwords
 * Part 1 Input format => <min_number_of_occurrence>-<max_number_of_occurrence> <character>: <password>
 * Part 2 Input format => <PasswordindexNumber>-<PasswordindexNumber> <character>: <password> (Index starts at 1)
 * @author adeshs
 */
public class PasswordPhilosophy {
    private static class PasswordPolicy{
        int minOccurence;
        int maxOccurence;
        char alphabet;
        String password;
    }

    public static void main(String[] args) {
        ArrayList<PasswordPolicy> inputArray = new ArrayList<PasswordPolicy>();
        //Read the input file and store the results in the
        try {
            inputArray = readInput("src/main/resources/input/Day2PasswordPhilosophy.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Input Size:"+ inputArray.size());
        //Part 1
        int validPasswordCount = 0;

        //Part 2 (input password file contains the index of the charaters)
        int validPasswordCountPart2 = 0;

        for(PasswordPolicy currentPassword: inputArray){
            HashMap<Character, Integer> characterCount = buildCharacterHashMap(currentPassword.password);
            if(characterCount.containsKey(currentPassword.alphabet)
                    && characterCount.get(currentPassword.alphabet) >= currentPassword.minOccurence
                    && characterCount.get(currentPassword.alphabet) <= currentPassword.maxOccurence)
                validPasswordCount++;

            if(currentPassword.password.charAt(currentPassword.minOccurence-1) == currentPassword.alphabet
                    && currentPassword.password.charAt(currentPassword.maxOccurence-1) != currentPassword.alphabet)
                validPasswordCountPart2++;
            else if(currentPassword.password.charAt(currentPassword.minOccurence-1) != currentPassword.alphabet
                    && currentPassword.password.charAt(currentPassword.maxOccurence-1) == currentPassword.alphabet)
                validPasswordCountPart2++;
        }
        System.out.println("Valid Passwords Part1 "+validPasswordCount);
        System.out.println("Valid Passwords Part2 "+validPasswordCountPart2);
    }
    /**
     * Builds the hashmap for the string based on the character keys
     */
    private static HashMap<Character, Integer> buildCharacterHashMap(String password){
        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        for(char c: password.toCharArray()){
            charCountMap.put(c, charCountMap.getOrDefault(c,0)+1);
        }
        return charCountMap;
    }

    /**
     * Read the Input file and parse the data line in the required format.
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    private static ArrayList<PasswordPolicy> readInput(String filePath) throws FileNotFoundException {
        ArrayList<PasswordPolicy> input = new ArrayList<PasswordPolicy>();
        Scanner s = new Scanner(new File(filePath));
        while (s.hasNext()){
            PasswordPolicy password = new PasswordPolicy();
            // parse input file format to the class
            // format <min_number_of_occurrence>-<max_number_of_occurrence> <character>: <password>
            String [] parsedLine = s.next().split(" ");
            password.minOccurence = Integer.parseInt(parsedLine[0].split("-")[0]);
            password.maxOccurence = Integer.parseInt(parsedLine[0].split("-")[1]);
            password.alphabet     = s.next().charAt(0);
            password.password     = s.next();
            input.add(password);
        }
        s.close();
        return input;

    }
}
