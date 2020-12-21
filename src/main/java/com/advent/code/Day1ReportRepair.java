package com.advent.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Day 1 of Advent of Code 2020 (https://adventofcode.com/)
 * Report Repair
 * Part 1 => Find a pair of numbers in an array that sum to 2020 and return the product of the pair.
 * Part 2 => Find a triplet of numbers in an array that sum to 2020 and return the product of the triplet.
 * Input file => resources/input/Day1ReportRepair_input.txt => contains the list of numbers
 * @author adeshs
 */
public class Day1ReportRepair {

    public static void main(String[] args) {
        ArrayList<Integer> inputArray = new ArrayList<Integer>();
        //Read the input file and store the results in the
        try {
            inputArray = readInput("src/main/resources/input/Day1ReportRepair_input.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Input Size:"+ inputArray.size());
        //Sort the array in ascending order
        Collections.sort(inputArray);

        System.out.println("Pair Product:"+findPairs(inputArray,2020,-1));
        System.out.println("Triplet Product:"+findTriplet(inputArray,2020));
    }



    /**
     * Finds the two numbers pair in the input array whose sum is equal to target number
     * Time Complexity => O(N)
     * @param inputArray
     * @param targetNumber
     * @param skipIndex The array index that has to be skipped while searching the pair
     * @return product of the number pair
     */
    private static Integer findPairs(ArrayList<Integer> inputArray, int targetNumber, int skipIndex) {
        //double index
        int low = 0;
        int high = inputArray.size()-1;
        while(low < high){
            //If the index has to be skipped
            if(low == skipIndex) {
                low += 1;
                continue;
            }
            else if (high == skipIndex) {
                high -= 1;
                continue;
            }

            if(inputArray.get(low)+inputArray.get(high) == targetNumber){
                System.out.println("Pairs:=>"+inputArray.get(low)+","+inputArray.get(high));
                return inputArray.get(low) * inputArray.get(high);
            }
            else if (inputArray.get(low)+inputArray.get(high) < targetNumber)
                low += 1;
            else
                high -=1;
        }
        return  0;
    }
    /**
     * Finds the three numbers triplet in the input array whose sum is equal to target number
     * Time Complexity => O(N^2)
     * @param inputArray
     * @param targetNumber
     * @return product of the number triplet
     */
    private static Integer findTriplet(ArrayList<Integer> inputArray, int targetNumber) {
        int result = 0;
        for(int i = 0; i < inputArray.size()-2; i++){
            int pairProduct = findPairs(inputArray,targetNumber-inputArray.get(i),i);
            if(pairProduct != 0){
                System.out.println("Third Number:" + inputArray.get(i));
                return pairProduct * inputArray.get(i);
            }

        }
        return result;
    }

    private static ArrayList<Integer> readInput(String filePath) throws FileNotFoundException {
        ArrayList<Integer> input = new ArrayList<Integer>();
        Scanner s = new Scanner(new File(filePath));
        while (s.hasNext()){
            input.add(Integer.parseInt(s.next()));
        }
        s.close();
        return input;

    }
}
