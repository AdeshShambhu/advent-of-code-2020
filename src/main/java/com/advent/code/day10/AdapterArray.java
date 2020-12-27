package com.advent.code.day10;

import com.advent.code.utils.Utility;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Day 10 of Advent of Code 2020 (https://adventofcode.com/)
 * Adapter Array (https://adventofcode.com/2020/day/10)
 * @author adesh
 */
public class AdapterArray {
    public static void main(String[] args) {
        Utility utility = new Utility();
        try {
            List<Integer> adapterList = utility.readInputIntegerList("src/main/resources/input/Day10AdapterArrays.txt");
            partOneGetNumberOfSteps(adapterList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int partOneGetNumberOfSteps(List<Integer> adapterList) {
        //counter for 1-difference and 3-differences
        int oneDifferenceCounter = 0;
        int threeDifferenceCounter = 0;
        // initial joltage
        int jolt =  0;
        //Counter to iterate over the elements
        int i = 0;
        while(i < adapterList.size()){
            //just if jolt+1 contains in the list
            if(adapterList.contains(jolt+1)){
                jolt += 1;
                oneDifferenceCounter++;
            }else if(adapterList.contains(jolt+2)){
                jolt += 2;
            }else if(adapterList.contains(jolt+3)){
                jolt += 3;
                threeDifferenceCounter++;
            }else{
                System.err.println("Joltage jump is not possible");
            }
            i++;
            //System.out.println(jolt);
        }
        //Perform last 3-step jump
        jolt+=3;
        threeDifferenceCounter++;

        System.out.println("One difference * 3 difference="+oneDifferenceCounter*threeDifferenceCounter);
        return oneDifferenceCounter*threeDifferenceCounter;
    }
}
