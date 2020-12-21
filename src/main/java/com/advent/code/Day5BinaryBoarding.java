package com.advent.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Day 5 of Advent of Code 2020 (https://adventofcode.com/)
 * Binary Boarding (https://adventofcode.com/2020/day/5)
 * Input file => resources/input/Day5BinaryBoarding_input.txt => contains the list of Boarding numbers
 * @author adeshs
 */
public class Day5BinaryBoarding {
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(new File("src/main/resources/input/Day5BinaryBoarding_input.txt"));
            HashSet<Integer> seatHash = new HashSet<Integer>();
            int maxSeatID = 0;

            while(s.hasNext()){
                String boardingID = s.next();
                //double indexing
                int low = 0, high = 127;

                int row = 0, column = 0;
                //iterate through the first 6 letters of boarding pass
                for(int i = 0; i < 6; i++){
                    int mid = (high-low)/2;
                    if(boardingID.charAt(i) == 'F'){
                        high -= (mid+1);
                    }else if(boardingID.charAt(i) == 'B'){
                        low +=mid+1;
                    }
                }
                //6th letter, decided on low or high
                if(boardingID.charAt(6) == 'F')
                    row = low;
                else
                    row = high;
                int seatLow = 0, seatHigh = 7;
                //iterate through the next 2 letters of boarding pass
                for(int i = 7; i < 9; i++){
                    int mid = (seatHigh-seatLow)/2;
                    if(boardingID.charAt(i) == 'L'){
                        seatHigh -=(mid+1);
                    }else if(boardingID.charAt(i) == 'R'){
                        seatLow +=(mid+1);
                    }
                }
                //6th letter, decided on low or high
                if(boardingID.charAt(9) == 'L')
                    column = seatLow;
                else
                    column = seatHigh;

                if((row*8 + column) > maxSeatID)
                    maxSeatID = (row*8 + column);

                seatHash.add( (row*8) + column);

            }
            s.close();
            //part2 (find your seat, seat not in very beginning or end of the aircraft)
            //skip first and last 20  rows
            for(int i = (20*8) + 8 ; i < (108*8)+8 ; i++){
                if(!seatHash.contains(i))
                    System.out.println("My Seat ID:"+i);
            }
            System.out.println("Max Seat ID:"+maxSeatID);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
