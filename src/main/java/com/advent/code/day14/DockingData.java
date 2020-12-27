package com.advent.code.day14;

import com.advent.code.utils.Utility;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Day 14 of Advent of Code 2020 (https://adventofcode.com/)
 * Docking Data (https://adventofcode.com/2020/day/14)
 *
 * @author adesh
 */
public class DockingData {
    private static final int BIT_REPRESENTATION = 36;
    public static void main(String[] args) throws FileNotFoundException {
        Utility utility = new Utility();
        List<String> instructions = utility.readInputStringList("src/main/resources/input/Day14DockingData.txt");

        HashMap<Long, Long> addressValueMap = new HashMap<>();
        //Part 2  - decoder v2
        HashMap<Long, Long> decoderValueMap = new HashMap<>();

        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i).startsWith("mask")) {
                String mask = instructions.get(i).split("=")[1].trim();
                //Iterate through the instructions for each mask
                while (i < instructions.size() - 1 && instructions.get(i + 1).startsWith("mem")) {
                    //increment the iterator
                    i++;

                    //get the memory address and value
                    Long addr = Long.parseLong(instructions.get(i).split("=")[0]
                            .trim()
                            .replace("mem[", "")
                            .replace("]", "")
                    );
                    Long value = Long.parseLong(instructions.get(i).split("=")[1].trim());
                    //convert value to 36 bits
                    StringBuilder valueBinary = convertLongTo36Binary(value);
                    //replace the bits with mask bits
                    for (int s = 0; s < BIT_REPRESENTATION; s++) {
                        if (mask.charAt(s) != 'X') {
                            valueBinary.setCharAt(s, mask.charAt(s));
                        }
                    }
                    addressValueMap.put(addr, Long.parseUnsignedLong(valueBinary.toString(), 2));

                    /*
                     * Part 2
                     */
                    StringBuilder addressBinary = convertLongTo36Binary(addr);
                    //replace the bits with mask bits
                    for (int s = 0; s < BIT_REPRESENTATION; s++) {
                        if (mask.charAt(s) == '1') {
                            addressBinary.setCharAt(s, '1');
                        } else if (mask.charAt(s) == 'X') {
                            addressBinary.setCharAt(s, 'X');
                        }
                    }
                    List<String> addressList = new ArrayList<>();

                    for (int s = 0; s < BIT_REPRESENTATION; s++) {
                        if (mask.charAt(s) != 'X') {
                            if (addressList.isEmpty())
                                addressList.add(String.valueOf(addressBinary.charAt(s)));
                            else {
                                for (int j = 0; j < addressList.size(); j++) {
                                    String number = addressList.get(j);
                                    addressList.set(j, new StringBuilder(number).append(addressBinary.charAt(s)).toString());
                                }
                            }

                        } else if (mask.charAt(s) == 'X') {
                            int numberOfPossibleOutComes = addressList.size();
                            //when MSB is a floating digit
                            if (numberOfPossibleOutComes == 0) {
                                addressList.add("0");
                                addressList.add("1");
                            } else {
                                for (int j = 0; j < numberOfPossibleOutComes; j++) {
                                    String number = addressList.get(j);
                                    //replace floating bit X with 0 number
                                    addressList.set(j,new StringBuilder(number).append("0").toString());
                                    //add new number with floating bit X to 1
                                    addressList.add(new StringBuilder(number).append("1").toString());
                                }
                            }
                        }
                    }
                    //New Decoder Values
                    for(String addrNew: addressList){
                        decoderValueMap.put(Long.parseLong(addrNew,2),value);
                    }
                }
            }
        }
        Long sum = 0L;
        for (Long addr : addressValueMap.keySet()) {
            sum += addressValueMap.get(addr);
        }
        System.out.println("Part1: Final Sum:" + sum);

        Long sumv2 = 0L;
        for (Long addr : decoderValueMap.keySet()) {
            sumv2 += decoderValueMap.get(addr);
        }

        System.out.println("Part2: Final Sum:" + sumv2);
    }

    /**
     * Converts long data to 36 bits binary representation (min 36 bits).
     * Appends zero to the binary number to fill 36 bits.
     *
     * @param value
     * @return
     */
    private static StringBuilder convertLongTo36Binary(Long value) {
        int binaryLength = Long.toBinaryString(value).length();
        StringBuilder sb = new StringBuilder();
        //append lending zeros
        for (int i = 0; i < BIT_REPRESENTATION - binaryLength; i++) {
            sb.append("0");
        }
        sb.append(Long.toBinaryString(value));
        return sb;

    }
}
