package com.advent.code.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author adesh
 */
public class Utility {

    /**
     * Reads the input file and returns the List of integer.
     * @param filePath
     * @return
     */
    public List<Integer> readInputIntegerList(String filePath) throws FileNotFoundException {
        List<Integer> input = new ArrayList<Integer>();
        Scanner s = new Scanner(new File(filePath));
        while (s.hasNext()){
            input.add(s.nextInt());
        }
        s.close();
        return input;
    }

    /**
     * Reads the input file and returns the List of Long.
     * @param filePath
     * @return
     */
    public List<Long> readInputLongList(String filePath) throws FileNotFoundException {
        List<Long> input = new ArrayList<Long>();
        Scanner s = new Scanner(new File(filePath));
        while (s.hasNext()){
            input.add(s.nextLong());
        }
        s.close();
        return input;
    }

    /**
     * Reads the input file and returns the List of String.
     * @param filePath
     * @return
     */
    public List<String> readInputStringList(String filePath) throws FileNotFoundException {
        List<String> input = new ArrayList<String>();
        Scanner s = new Scanner(new File(filePath));
        while (s.hasNext()){
            input.add(s.nextLine());
        }
        s.close();
        return input;
    }
}
