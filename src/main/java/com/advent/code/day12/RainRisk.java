package com.advent.code.day12;

import com.advent.code.utils.Utility;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Day 12 of Advent of Code 2020 (https://adventofcode.com/)
 * Rain Risk (https://adventofcode.com/2020/day/12)
 *
 * @author adesh
 */
public class RainRisk {
    public static void main(String[] args) {
        Utility utility = new Utility();
        try {
            List<String> directions = utility.readInputStringList("src/main/resources/input/Day12RainRisk.txt");

            //direction angle
            int[][] directionXY = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

            //current position
            int[] currentXY = {0, 0};

            // 0 - East, 1 - South, 2 - West, 3- North
            int direction = 0;
            /*
             * Part 1
             */
            for (String dir : directions) {
                char action = dir.charAt(0);
                int value = Integer.parseInt(dir.substring(1));

                switch (action) {
                    case 'N':
                        currentXY[1] += value;
                        break;
                    case 'S':
                        currentXY[1] -= value;
                        break;
                    case 'E':
                        currentXY[0] += value;
                        break;
                    case 'W':
                        currentXY[0] -= value;
                        break;
                    case 'F':
                        currentXY[0] += (value * directionXY[direction][0]);
                        currentXY[1] += (value * directionXY[direction][1]);
                        break;
                    default:
                        if (action == 'R')
                            direction += (value / 90);
                        else if (action == 'L') {
                            direction -= (value / 90);
                            direction += 4;
                        }

                        direction %= 4;
                }
            }
            System.out.println("Part 1: End position:(" + currentXY[0] + "," + currentXY[1] + ")" + ", direction:" + direction);
            System.out.println("Part 1: Manhattan distance:" + (Math.abs(currentXY[0]) + Math.abs(currentXY[1])));

            /*
             PART 2 -TODO
             */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
