package com.advent.code.day11;

import com.advent.code.utils.Utility;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Day 11 of Advent of Code 2020 (https://adventofcode.com/)
 * Seating System (Game of life problem) (https://adventofcode.com/2020/day/11)
 *
 * @author adesh
 */
public class SeatingSystem {

    public static void main(String[] args) throws FileNotFoundException {
        Utility utility = new Utility();
        List<String> seatingArea = utility.readInputStringList("src/main/resources/input/Day11SeatingArea.txt");
        List<String> seatingAreaPart2 = new ArrayList<>(seatingArea);
        System.out.println("Total Rows in the seating area:" + seatingArea.size());
        System.out.println("Initial Occupied seats:" + totalSeatTypes(seatingArea, '#'));
        System.out.println("Initial Empty seats:" + totalSeatTypes(seatingArea, 'L'));

        System.out.println(seatingArea.toString());
        /**
         * Part 1
         */
        //Repeat the seat switching for 100 itr until it gets stable(equilibrium state).
        for (int itr = 0; itr < 100; itr++) {
            List<String> next = new ArrayList<>();
            for (int i = 0; i < seatingArea.size(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < seatingArea.get(i).length(); j++) {
                    char seatStatus = seatingArea.get(i).charAt(j);
                    if (seatStatus != '.') {
                        int occupants = getTotalOccupiedNeighbours(seatingArea, i, j);
                        if (seatStatus == 'L' && occupants == 0)
                            seatStatus = '#';
                        else if (seatStatus == '#' && occupants >= 4)
                            seatStatus = 'L';
                    }
                    sb.append(seatStatus);
                }
                next.add(sb.toString());
            }
            seatingArea = next;
            System.out.println("Part 1 : Initial Occupied seats (after "+itr+" iteration):" + totalSeatTypes(seatingArea,'#'));
        }

        /**
         * Part 2
         */
        //Repeat the seat switching for 100 itr until it gets stable(equilibrium state).
        for (int itr = 0; itr < 100; itr++) {
            List<String> next = new ArrayList<>();
            for (int i = 0; i < seatingAreaPart2.size(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < seatingAreaPart2.get(i).length(); j++) {
                    char seatStatus = seatingAreaPart2.get(i).charAt(j);
                    if (seatStatus != '.') {
                        int occupants = getTotalOccupiedNeighboursPart2(seatingAreaPart2, i, j);
                        if (seatStatus == 'L' && occupants == 0)
                            seatStatus = '#';
                        else if (seatStatus == '#' && occupants >= 5)
                            seatStatus = 'L';
                    }
                    sb.append(seatStatus);
                }
                next.add(sb.toString());
            }
            seatingAreaPart2 = next;
            System.out.println("Part 2 : Initial Occupied seats (after "+itr+" iteration):" + totalSeatTypes(seatingAreaPart2,'#'));
        }

    }



    /**
     * method to count the total number of adjacent occupied seats
     *
     * @param data
     * @param currentRow
     * @param currentCol
     * @return
     */
    private static int getTotalOccupiedNeighbours(List<String> data, int currentRow, int currentCol) {
        int totalOccupiedSeats = 0;
        int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1},
        };

        for (int[] cell : offsets) {
            int cellAddressRow = currentRow + cell[0];
            int cellAddressCol = currentCol + cell[1];

            //Condition to handle the edges
            if (cellAddressCol >= 0 && cellAddressRow >= 0
                    && cellAddressRow < data.size() && cellAddressCol < data.get(cellAddressRow).length()
            ) {
                //if the adjacent cell is occupied
                if (data.get(cellAddressRow).charAt(cellAddressCol) == '#') {
                    totalOccupiedSeats++;
                }
            }
        }
        return totalOccupiedSeats;
    }

    /**
     * Find the first seat in each direction
     * @param data
     * @param currentRow
     * @param currentCol
     * @return
     */
    private static int getTotalOccupiedNeighboursPart2(List<String> data, int currentRow, int currentCol) {
        int totalOccupiedSeats = 0;
        int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1},
        };

        for (int[] cell : offsets) {
            int cellAddressRow = currentRow + cell[0];
            int cellAddressCol = currentCol + cell[1];

            while (cellAddressCol >= 0 && cellAddressRow >= 0
                    && cellAddressRow < data.size() && cellAddressCol < data.get(cellAddressRow).length() && data.get(cellAddressRow).charAt(cellAddressCol) == '.') {
                //Continue changing the index in the same direction until a seat is found
                cellAddressRow +=  cell[0];
                cellAddressCol +=  cell[1];
            }

            //Condition to handle the edges
            if (cellAddressCol >= 0 && cellAddressRow >= 0
                    && cellAddressRow < data.size() && cellAddressCol < data.get(cellAddressRow).length()
            ) {
                //if the adjacent cell is occupied
                if (data.get(cellAddressRow).charAt(cellAddressCol) == '#') {
                    totalOccupiedSeats++;
                }
            }
        }
        return totalOccupiedSeats;
    }

    /**
     * Count the total occupied seats
     *
     * @param data
     * @return
     */
    private static int totalSeatTypes(List<String> data, Character seatType) {
        int count = 0;
        for (String row : data) {
            for (char c : row.toCharArray()) {
                if (c == seatType) {
                    count++;
                }
            }
        }
        return count;
    }
}
