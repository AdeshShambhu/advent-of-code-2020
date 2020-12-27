package com.advent.code.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Day 6 of Advent of Code 2020 (https://adventofcode.com/)
 * Custom Customs (https://adventofcode.com/2020/day/6)
 * @author adeshs
 */
public class CustomCustoms {
    public static void main(String[] args) {
        try
        {
            //Counter for total answers per group
            int totalQuestions = 0;
            //Counter for total unique answers per group
            int totalUniqueQuesPerGroup = 0;
            Scanner s = new Scanner(new File("src/main/resources/input/Day6CustomCustoms.txt"));
            while (s.hasNext()) {
                HashSet<Character> questionsMap = new HashSet<Character>();
                HashSet<Character> uniqueMap = new HashSet<Character>();
                int groupMember = 0;
                while (s.hasNext()) {
                    String line = s.nextLine();
                    //System.out.println(line);
                    //Empty line delimiter
                    if (line.equals("")) {
                        break;
                    }
                    for (char c : line.toCharArray()) {
                      questionsMap.add(c);
                      if(groupMember==0) {
                          uniqueMap.add(c);
                      }
                    }
                    //Remove items from hashset if question does not appear.
                    if(groupMember>0){
                        for(char c: questionsMap){
                            if(!line.contains(Character.toString(c)))
                                uniqueMap.remove(c);
                        }
                    }
                    groupMember++;
                }
                totalQuestions += questionsMap.size();
                totalUniqueQuesPerGroup += uniqueMap.size();
                System.out.println(uniqueMap.size()+":"+totalUniqueQuesPerGroup);
            }
            System.out.println("Total sum of questions answered:" + totalQuestions);
            System.out.println("Total SUM of unique Questions Answered Per Group :" + totalUniqueQuesPerGroup);
            s.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
