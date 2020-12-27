package com.advent.code.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Day 13 of Advent of Code 2020 (https://adventofcode.com/)
 * Shuttle Search (https://adventofcode.com/2020/day/13)
 *
 * @author adesh
 */
public class ShuttleSearch {
    public static void main(String[] args) {

        List<Integer> busList = new ArrayList<Integer>();
        //Bus to be boarded
        int boardedBusID = 0;
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/input/Day13ShuttleSearch.txt"));
            int arrivalTimeStamp = scanner.nextInt();
            String busScheduleInput = scanner.next();
            for(String busTime : busScheduleInput.split(",")){
                if(!busTime.equals("x")){
                    int busID = Integer.parseInt(busTime);
                    busList.add(busID);
                }
            }
            System.out.println(busList.toString());
            boolean busFound = false;
            int startWaitTimestamp = arrivalTimeStamp;
            while(!busFound){
                for(int bus: busList){
                    if((startWaitTimestamp%bus)==0){
                        System.out.println("Boarding Bus ID:"+bus);
                        System.out.println("Waiting time:"+ (startWaitTimestamp  - arrivalTimeStamp));
                        System.out.println("Part 1 solution:" + (bus * (startWaitTimestamp  - arrivalTimeStamp)));
                        busFound =true;
                        break;
                    }
                }
                startWaitTimestamp++;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
