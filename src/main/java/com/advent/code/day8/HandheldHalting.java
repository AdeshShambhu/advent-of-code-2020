package com.advent.code.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Day 8 of Advent of Code 2020 (https://adventofcode.com/)
 * Hand Held Halting (https://adventofcode.com/2020/day/8)
 * @author adesh
 */
public class HandheldHalting {
    public static void main(String[] args) {
        try {
            ArrayList<String> instructions = readInput("src/main/resources/input/Day8HandHeldHalting.txt");
            boolean infiniteLoopFLag = boot(instructions);


            for(int i = 0; i <instructions.size() ; i++){
                ArrayList<String> instructionsCopy = new ArrayList<String>();
                for(String instructionCopy: instructions){
                    instructionsCopy.add(instructionCopy);
                }
                instructionsCopy.set(i, swapOperations(instructions.get(i)));
                if(!boot(instructionsCopy))
                    break;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String swapOperations(String instruction) {
        String operation = instruction.split(" ")[0];
        switch (operation){
            case "nop": instruction = instruction.replace("nop","jmp");
                        break;
            case "jmp": instruction = instruction.replace("jmp","nop");
                break;
        }
        return instruction;
    }

    private static ArrayList<String> readInput(String filePath) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<String>();
        Scanner s = new Scanner(new File(filePath));
        while (s.hasNext()){
            input.add(s.nextLine());
        }
        s.close();
        return input;

    }
    private static boolean boot(ArrayList<String> instructions){
        //array to keep track of the visited instructions
        int[] instructionAddress = new int[instructions.size()];
        //accumulator
        int accumulator = 0;
        int address = 0;
        boolean infiniteLoopFLag = false;
        while(address < instructionAddress.length){
            //Instrtuction already visited
            if(instructionAddress[address] == 1){
                infiniteLoopFLag = true;
                break;
            }

            instructionAddress[address] = 1;
            String operation = instructions.get(address).split(" ")[0];
            int value = Integer.parseInt(instructions.get(address).split(" ")[1]);
            switch (operation){
                case "acc": accumulator+=value;
                    break;
                case "jmp": address+=value;
                    break;
            }

            if(!operation.equals("jmp"))
                address++;
        }
        System.out.println("Final Accumulator:"+accumulator);
        if(!infiniteLoopFLag){
            System.out.println("Final Accumulator with normal execution:"+accumulator);
        }
        return infiniteLoopFLag;
    }
}
