package acmicpc.prob4963_islands_count;

import static java.lang.Math.min;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Main {

    @Test
    public void test() throws URISyntaxException, IOException {
        int[][] m = {
                {1, 0, 1, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 1, 0},
        };
        assertEquals(3, island(m));
    }

    private static int island(int[][] m) {
        int cnt = 0;
        int h = m.length;
        int w = m[0].length;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (m[i][j] == -1) {
                    m[i][j] = ++cnt;
                    island(m, i, j, cnt);
                }
            }
        }
        return cnt;
    }

    private static void island(int[][] m, int i, int j, int cnt) {
        int h = m.length;
        int w = m[0].length;
        if (i < 0 || i >= w || j < 0 || j >= h) return;
    }

    static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));

    }

}
