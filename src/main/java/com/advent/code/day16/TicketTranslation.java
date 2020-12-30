package com.advent.code.day16;

import com.advent.code.utils.Utility;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Day 16 of Advent of Code 2020 (https://adventofcode.com/)
 * Ticket Translation (https://adventofcode.com/2020/day/16)
 *
 * @author adesh
 */
public class TicketTranslation {

    public static void main(String[] args) throws FileNotFoundException {
        Utility utility = new Utility();

        Map<Integer, List<String>> ticketRules = new HashMap<>();
        List<Integer> myTicketValues = new ArrayList<>();
        List<Integer> othersTicketValues = new ArrayList<>();
        List<String> ticketsInfo = utility.readInputStringList("src/main/resources/input/Day16TicketTranslation.txt");
        int i = 0;
        while (i < ticketsInfo.size()) {
            if (ticketsInfo.get(i).equals(""))
                break;
            String ticketField = ticketsInfo.get(i).split(":")[0];
            String ruleValues = ticketsInfo.get(i).split(":")[1];
            for (String rule : ruleValues.split(" or ")) {
                for (int j = Integer.parseInt(rule.split("-")[0].trim());
                     j <= Integer.parseInt(rule.split("-")[1].trim()); j++) {
                    if (!ticketRules.containsKey(j))
                        ticketRules.put(j, new ArrayList<>(Arrays.asList(ticketField)));
                    else {
                        List<String> updatedTicket = ticketRules.get(j);
                        updatedTicket.add(ticketField);
                        ticketRules.put(j, updatedTicket);
                    }

                }
            }
            i++;
        }
        while (!ticketsInfo.get(i).equals("your ticket:")) {
            i++;
        }
        if (ticketsInfo.get(i).equals("your ticket:")) {
            i++;
            for (String value : ticketsInfo.get(i).split(",")) {
                myTicketValues.add(Integer.parseInt(value));
            }
        }
        while (!ticketsInfo.get(i).equals("nearby tickets:")) {
            i++;
        }
        i++;

        while (i < ticketsInfo.size()) {
            for (String value : ticketsInfo.get(i).split(",")) {
                othersTicketValues.add(Integer.parseInt(value));
            }
            i++;
        }

        /*
         * part 1
         */
        int invalidSum = 0;
        for (Integer val : othersTicketValues) {
            if (!ticketRules.containsKey(val)) {
                invalidSum += val;
            }
        }
        System.out.println("Part1 Sum of all invalid values:" + invalidSum);

    }
}
