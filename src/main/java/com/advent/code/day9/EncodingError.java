package com.advent.code.day9;

import com.advent.code.utils.Utility;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Day 9 of Advent of Code 2020 (https://adventofcode.com/)
 * Encoding Error (https://adventofcode.com/2020/day/9)
 * @author adesh
 */
public class EncodingError {

    public static void main(String[] args) {
        try {
            int validCodes = 0;
            boolean pairFound = false;
            Utility utility = new Utility();
            List<Long> codes = utility.readInputLongList("src/main/resources/input/Day9EncodingError.txt");
            for(int i= 25; i < codes.size(); i++){
                if(findPairs(codes,codes.get(i),i-25, i-1)){
                    pairFound = true;
                    validCodes++;
                }else{
                    System.out.println("Pair Not found for :"+codes.get(i) );
                    System.out.println("Sum of Min,max:"+findConigousSet(codes.get(i),codes));
                }

            }
            System.out.println("Valid Codes=>"+validCodes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static long findConigousSet(Long targetCode, List<Long> codes) {
        System.out.println("Target :"+targetCode );
        int low = 0;
        int high = codes.size();
        boolean isFound = false;
        for(int i=0; i < codes.size()-1; i++){
            long sum = codes.get(i) ;
            for(int j = i+1; j < codes.size(); j++){
                sum += codes.get(j);
                if(sum == targetCode){

                    isFound = true;
                    low = i;
                    high = j;
                    break;
                }
            }
            if(isFound)
                break;
        }
        long min = codes.get(low);
        long max = codes.get(low);
        for(int i = low; i < high ; i++){
            if(codes.get(i)<min){
                min = codes.get(i);
            }
            if(codes.get(i) > max){
                max = codes.get(i);
            }
        }
        return min+max;
    }

    /**
     * Finds the two numbers pair in the input array whose sum is equal to target number
     * Time Complexity => O(N)
     * @param inputArray
     * @param targetNumber
     * @param lowIndex
     * @param highIndex
     * @return product of the number pair
     */
    private static boolean findPairs(List<Long> inputArray, long targetNumber, int lowIndex, int highIndex) {
        for(int i=lowIndex; i < lowIndex+24; i++){
            for(int j= i+1; j < lowIndex+25; j++){
                if(inputArray.get(i)+inputArray.get(j) ==  targetNumber){
                    return true;
                }
            }
        }
        return  false;
    }

}
