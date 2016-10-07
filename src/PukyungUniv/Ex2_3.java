package PukyungUniv;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

public class Ex2_3 {
    public static final int BACKGROUND = 0;
    public static final int IMAGE = 1;
    public static final int COUNTED = 2;

    int N = 8;
    int[][] grid = {
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 1, 1, 0, 0, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 0, 1, 0 },
            { 0, 0, 0, 0, 0, 1, 0, 0 },
            { 0, 1, 0, 1, 0, 1, 0, 0 },
            { 0, 1, 0, 1, 0, 1, 0, 0 },
            { 1, 0, 0, 0, 1, 0, 0, 1 },
            { 0, 1, 1, 0, 0, 1, 1, 1 }
    };

    private void printCountOfCellsEachBlob() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (grid[y][x] == IMAGE) {
                    System.out.println(countCells(x, y));
                }
            }
        }
    }

    private int countCells(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N || grid[y][x] != IMAGE) {
            return 0;
        } else {
            grid[y][x] = COUNTED;
            return 1 + countCells(x, y - 1) + countCells(x + 1, y - 1)
                    + countCells(x + 1, y) + countCells(x + 1, y + 1)
                    + countCells(x, y + 1) + countCells(x - 1, y + 1)
                    + countCells(x - 1, y) + countCells(x - 1, y - 1);
        }
    }

    @Test
    public void test() {
        printCountOfCellsEachBlob();
    }

}
