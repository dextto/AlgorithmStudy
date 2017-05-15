package acmicpc.prob1149_RGB_Street;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Arrays;

import static java.lang.Math.min;
import static org.junit.Assert.assertEquals;

public class Main {

    @Test
    public void test() throws URISyntaxException, IOException {
        N = 1;
        costs = new int[][] {
                {26, 40, 83},
        };
        houseColor = new int[N];
        Arrays.fill(houseColor, -1);
        cal(houseColor, 0, 0);
        assertEquals(26, cost);
    }
    @Test
    public void test2() throws URISyntaxException, IOException {
        N = 2;
        costs = new int[][] {
                {26, 40, 83},
                {49, 60, 57}
        };
        houseColor = new int[N];
        Arrays.fill(houseColor, -1);
        cal(houseColor, 0, 0);
        assertEquals(83, cost);
    }
    @Test
    public void test3() throws URISyntaxException, IOException {
        N = 3;
        costs = new int[][] {
                {26, 40, 83},
                {49, 60, 57},
                {13, 89, 99}
        };
        houseColor = new int[N];
        Arrays.fill(houseColor, -1);
        cal(houseColor, 0, 0);
        assertEquals(96, cost);
    }

    static BufferedReader reader;

    static int N;
    static int cost = Integer.MAX_VALUE;
    static int[][] costs;
    static int[] houseColor; // R: 0, G: 1, B: 2

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        costs = new int[N][3];
        houseColor = new int[N];
        Arrays.fill(houseColor, -1);

        for (int i = 0; i < N; i++) {
            String[] str = reader.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                costs[i][j] = Integer.parseInt(str[j]);
            }
        }

        cal(houseColor, 0, 0);
        System.out.println(cost);
    }

    private static void cal(int[] houseColor, int idx, int sum) {
        if (N == 1) {
            cost = min(min(costs[0][0], costs[0][1]), costs[0][2]);
            return;
        }
        if (idx == N - 1) {
            sum += min(costs[idx][(houseColor[idx - 1] - 1 + 3) % 3], costs[idx][(houseColor[idx - 1] + 1) % 3]);
            cost = min(cost, sum);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (idx > 0 && i == houseColor[idx - 1]) continue;

            houseColor[idx] = i;
            sum += costs[idx][i];
            cal(houseColor, idx + 1, sum);
            sum -= costs[idx][i];
        }
    }

}
