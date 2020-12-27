package com.advent.code.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Day 3 of Advent of Code 2020 (https://adventofcode.com/)
 * Toboggan Trajectory
 * Given a Map with open spaces(.) and trees(#), traverse from top left to bottom right.
 * Count the total number of trees encountered while traversing 3 right and 1 down (Only at the end point).
 * Part 1 => Find total number of trees encountered while traversing
 * Part 2 => Different combinations for traversal (multiple the number of trees encountered in each traversal).
 * Input file => resources/input/Day3TobogganTrajectory.txt => contains the map with open spaces(.) and trees(#)
 * @author adeshs
 */
public class TobogganTrajectory {

    public static void main(String[] args) {
        ;
        //Read the input file and store the results in the
        try {
            char[][] islandMap = readInput("src/main/resources/input/Day3TobogganTrajectory.txt");

            List<List<Integer>> traversalCombination =
                    Arrays.asList(Arrays.asList(1, 1),
                            Arrays.asList(3, 1),
                            Arrays.asList(5, 1),
                            Arrays.asList(7, 1),
                            Arrays.asList(1, 2)) ;
            //product of all treecounts in the traversals
            long result = 1;
            for(List<Integer> slope : traversalCombination) {
                //Tree Count
                int treeCount = 0;
                //Traverse down the slope (3 to the right, 1 down)
                int x = slope.get(0), y = slope.get(1);
                while (y < islandMap.length) {
                    if (islandMap[y][(x) % (islandMap[y].length)] == '#')
                        treeCount++;
                    y += slope.get(1);
                    x += slope.get(0);
                }
                System.out.println(treeCount);
                result *= treeCount;
            }
            System.out.println("Product:"+ result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * construct a 2D character array from the input file
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    private static char[][] readInput(String filePath) throws FileNotFoundException {

        Scanner s = new Scanner(new File(filePath));
        char[][] islandMap = new char[323][31];
        int i=0;
        while (s.hasNext()){
            String line = s.next();
            for(int j = 0; j < line.length(); j++){
                islandMap[i][j] = line.charAt(j);
            }
            i++;
        }
        s.close();
        return islandMap;

    }

}
