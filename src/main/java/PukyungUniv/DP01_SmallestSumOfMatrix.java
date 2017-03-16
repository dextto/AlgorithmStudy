package PukyungUniv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DP01_SmallestSumOfMatrix {

//    private int[][] m = {
//            { 6, 7, 12 },
//            { 5, 3, 11 },
//            { 7, 17, 3 },
//    };
    private int[][] m = {
            { 6, 7, 12, 5  },
            { 5, 3, 11, 18 },
            { 7, 17, 3, 3  },
            { 8, 10, 15, 9},
    };

    int[][] cache;
    @Test
    public void test() {
        cache = new int[m.length][m[0].length];
        assertEquals(40, sum(m.length - 1, m[0].length - 1));
        cache = new int[m.length][m[0].length];
        assertEquals(40, sum());
    }

    // Smallest sum of from (0, 0) to (i, j)
    private int sum(int j, int i) {
        if (cache[j][i] != 0) return cache[j][i];
        
        if (j == 0 && i == 0)
            return cache[j][i] = m[j][i];
        else if (i == 0)
            return cache[j][i] = m[j][i] + sum(j - 1, i);
        else if (j == 0)
            return cache[j][i] = m[j][i] + sum(j, i - 1);
        else
            return cache[j][i] = m[j][i] + Math.min(sum(j - 1, i), sum(j, i - 1));
    }
    
    // Bottom-up solution
    private int sum() {
        for (int j = 0; j < m.length; j++ ) {
            for (int i = 0; i < m[0].length; i++) {
                if (j == 0 && i == 0)
                    cache[j][i] = m[j][i];
                else if (i == 0)
                    cache[j][i] = m[j][i] + cache[j - 1][i];
                else if (j == 0)
                    cache[j][i] = m[j][i] + cache[j][i - 1];
                else
                    cache[j][i] = m[j][i] + Math.min(cache[j - 1][i], cache[j][i - 1]);
            }
        }
        return cache[m.length - 1][m[0].length - 1];
    }
}