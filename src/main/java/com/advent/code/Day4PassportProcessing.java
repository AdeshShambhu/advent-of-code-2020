package com.advent.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Day 4 of Advent of Code 2020 (https://adventofcode.com/)
 * Passport Processing (https://adventofcode.com/2020/day/4)
 * Input file => resources/input/Day4PassportProcessing_input.txt => contains the list of passport details
 * @author adeshs
 */
public class Day4PassportProcessing {
    static HashSet<String> eyeColors = new HashSet<>();
    static {
        eyeColors.add("amb");
        eyeColors.add("blu");
        eyeColors.add("brn");
        eyeColors.add("gry");
        eyeColors.add("grn");
        eyeColors.add("hzl");
        eyeColors.add("oth");
    }
    private static class Passport{
        // Birth Year
        String byr;
        //Issue Year
        String iyr;
        // Expiration Year
        String eyr;
        //Height
        String hgt;
        //Hair Color
        String hcl;
        //Eye Color
        String ecl;
        //Password ID
        String pid;
        //Country ID
        String cid;

        @Override
        public String toString() {
            return "Passport{" +
                    "byr='" + byr + '\'' +
                    ", iyr='" + iyr + '\'' +
                    ", eyr='" + eyr + '\'' +
                    ", hgt='" + hgt + '\'' +
                    ", hcl='" + hcl + '\'' +
                    ", ecl='" + ecl + '\'' +
                    ", pid='" + pid + '\'' +
                    ", cid='" + cid + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/main/resources/input/Day4PassportProcessing_input.txt"));
        int validPassports = 0;
        int count = 0;
        while(s.hasNext()){
            Passport person = new Passport();
            while(s.hasNext()){
                String line = s.nextLine();
                //System.out.println(line);
                //Empty line delimiter
                if(line.equals("")){
                    break;
                }

                for(String hashData : line.split(" ")) {
                    String[] data = hashData.split(":");
                    switch (data[0]) {
                        case "byr":
                            person.byr = data[1];
                            continue;
                        case "iyr":
                            person.iyr = data[1];
                            continue;
                        case "eyr":
                            person.eyr = data[1];
                            continue;
                        case "hgt":
                            person.hgt = data[1];
                            continue;
                        case "hcl":
                            person.hcl = data[1];
                            continue;
                        case "ecl":
                            person.ecl = data[1];
                            continue;
                        case "pid":
                            person.pid = data[1];
                            continue;
                        case "cid":
                            person.cid = data[1];
                            continue;
                    }
                }
                }
            count++;
            //System.out.println(person.toString());
            if(validatePassport(person))
                validPassports++;
        }
        System.out.println("Total Passports/Valid Passports:"+count+"/"+validPassports);
        System.out.println(eyeColors.toString());
        s.close();
    }

    private static boolean validatePassport(Passport person) {

        if( person.byr == null
            || person.hgt == null
            || person.ecl == null
            || person.eyr == null
            || person.hcl == null
            || person.iyr == null
            || person.pid == null)
            return false;
        //Part 2 Validations

        if(!(person.byr.length() == 4
                && Integer.parseInt(person.byr) >= 1920
                && Integer.parseInt(person.byr) <= 2002))
            return false;
        if(!(person.iyr.length() == 4
                && Integer.parseInt(person.iyr) >= 2010
                && Integer.parseInt(person.iyr) <= 2020))
            return false;
        if(!(person.eyr.length() == 4
                && Integer.parseInt(person.eyr) >= 2020
                && Integer.parseInt(person.eyr) <= 2030)){
            return false;
        }

        if(person.hgt.contains("cm")) {
            if (!(Integer.parseInt(person.hgt.split("cm")[0]) >= 150
                    && Integer.parseInt(person.hgt.split("cm")[0]) <= 193))
                return false;
        }
        else if(person.hgt.contains("in")) {
            if (!(Integer.parseInt(person.hgt.split("in")[0]) >= 59
                    && Integer.parseInt(person.hgt.split("in")[0]) <= 76))
                return false;
        }
        else
            return false;

        if(!(person.hcl.charAt(0) == '#'
                && person.hcl.length()==7
                && person.hcl.substring(1,7).matches("^[a-f0-9]+$")
                )){
            return false;
        }


        if(!eyeColors.contains(person.ecl)){
            return false;
        }
        if(!(person.pid.length()==9 && person.pid.matches("^[0-9]+$"))) {
            return false;
        }

        return true;
    }

}
