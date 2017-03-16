package PukyungUniv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DP02_PathOfSmallestSumOfMatrix {

    private static final int UP = 1;
    private static final int LEFT = 2;

    private int[][] m = {
            { 6, 7, 12, 5 },
            { 5, 3, 11, 18 },
            { 7, 17, 3, 3 },
            { 8, 10, 15, 9 },
    };

    int[][] cache;
    int[][] path;
    
    @Test
    public void test() {
        cache = new int[m.length][m[0].length];
        path = new int[m.length][m[0].length];
        assertEquals(40, sum());
    }

    // Bottom-up solution
    private int sum() {
        for (int j = 0; j < m.length; j++) {
            for (int i = 0; i < m[0].length; i++) {
                if (j == 0 && i == 0) {
                    cache[j][i] = m[j][i];
                    path[j][i] = 0;
                } else if (i == 0) {
                    cache[j][i] = m[j][i] + cache[j - 1][i];
                    path[j][i] = UP;
                } else if (j == 0) {
                    cache[j][i] = m[j][i] + cache[j][i - 1];
                    path[j][i] = LEFT;
                } else {
                    cache[j][i] = m[j][i] + Math.min(cache[j - 1][i], cache[j][i - 1]);
                    if (cache[j - 1][i] < cache[j][i - 1]) {
                        path[j][i] = UP;
                    } else {
                        path[j][i] = LEFT;
                    }
                }
            }
        }
        
        printPath(m.length - 1, m[0].length - 1);
        
        return cache[m.length - 1][m[0].length - 1];
    }

    private void printPath(int j, int i) {
        if (path[j][i] == 0) {
            System.out.println(i +  " " + j);
        } else {
            if (path[j][i] == LEFT) {
                printPath(j, i - 1);
            } else {
                printPath(j - 1, i);
            }
            System.out.println(i +  " " + j);
        }
    }
}