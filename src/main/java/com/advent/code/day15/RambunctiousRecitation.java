package com.advent.code.day15;

import com.advent.code.utils.Utility;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Day 15 of Advent of Code 2020 (https://adventofcode.com/)
 * Rambunctious Recitation (https://adventofcode.com/2020/day/15)
 *
 * @author adesh
 */
public class RambunctiousRecitation {
    public static void main(String[] args) throws FileNotFoundException {
        //Map to  store the number and its turn numbers it was called
        Map<Integer, List<Integer>> numberTurnsMap = new HashMap<>();
        Utility utility = new Utility();
        //counter to keep track of the turns
        int turnCount = 1;

        int previousNumber = 0;
        String inputTurns = utility.readInputStringList("src/main/resources/input/Day15RambunctiousRecitation.txt").get(0);
        for (String num : inputTurns.split(",")) {
            int number = Integer.parseInt(num);
            updateTurnsHistoryMap(numberTurnsMap, turnCount, number);
            previousNumber = number;
            turnCount++;
        }
        /*
         * PArt1 = 2020
         * Part2 = 30000000
         */
        while (turnCount <= 2020) {
            int currentNumber;
            //If previous number was told in the game for the first time
            if(numberTurnsMap.containsKey(previousNumber) && numberTurnsMap.get(previousNumber).size() == 1) {
                currentNumber = 0;
            } else {
                currentNumber = numberTurnsMap.get(previousNumber).get(1) - numberTurnsMap.get(previousNumber).get(0);
            }
            updateTurnsHistoryMap(numberTurnsMap, turnCount, currentNumber);
            previousNumber = currentNumber;
            turnCount++;

        }
        System.out.println((turnCount - 1) + "'s turn number :" + previousNumber);
    }

    private static void updateTurnsHistoryMap(Map<Integer, List<Integer>> numberTurnsMap, int turnCount, int currentNumber) {
        if (numberTurnsMap.containsKey(currentNumber)) {
            int previousTurnCount = numberTurnsMap.get(currentNumber).get(numberTurnsMap.get(currentNumber).size() - 1);
            numberTurnsMap.put(currentNumber, new ArrayList<>(Arrays.asList(previousTurnCount, turnCount)));
        } else {
            numberTurnsMap.put(currentNumber, new ArrayList<>(Arrays.asList(turnCount)));
        }
    }
}
