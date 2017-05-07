package acmicpc.prob1005_ACM_Craft;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.*;

import static java.lang.Math.max;
import static org.junit.Assert.assertEquals;

public class Main {

    @Test
    public void test() throws URISyntaxException, IOException {
        time = new int[] { 0, 10, 1, 100, 10 };
        mem = new int[4 + 1];
        Arrays.fill(mem, -1);
        g = new int[5][5];
        g[1][2] = g[1][3] = g[2][4] = g[3][4] = 1;
        assertEquals(120, cal(4, 4));
    }

    @Test
    public void test2() throws URISyntaxException, IOException {
        time = new int[] { 0, 10, 20, 1, 5, 8, 7, 1, 43 };
        mem = new int[8 + 1];
        Arrays.fill(mem, -1);
        g = new int[9][9];
        g[1][2] = g[1][3] = g[2][4] = g[2][5] = g[3][6] = g[5][7] = g[6][7] = g[7][8] = 1;
        assertEquals(39, cal(7, 8));
    }

    static BufferedReader reader;
    static int g[][]; // 방향성 그래프. g[u][v]가 1이면 u->v인 경로가 존재.
    static int time[]; // 건물을 짓는데 걸리는 시간
    static int mem[]; // 건물을 짓는데 걸리는 시간

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(reader.readLine());
        while (cases-- > 0) {
            String str[] = reader.readLine().split(" ");
            int N = Integer.parseInt(str[0]);
            int K = Integer.parseInt(str[1]);
            time = new int[N + 1];
            mem = new int[N + 1];
            Arrays.fill(mem, -1);
            str = reader.readLine().split(" ");
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(str[i - 1]);
            }
            g = new int[N + 1][N + 1];
            for (int i = 0; i < K; i++) {
                str = reader.readLine().split(" ");
                int u = Integer.parseInt(str[0]);
                int v = Integer.parseInt(str[1]);
                g[u][v] = 1;
            }

            int W = Integer.parseInt(reader.readLine());

            System.out.println(cal(W, N));
        }
    }

    private static int cal(int node, int N) {
        if (node == 0 || node > N) return 0;
        if (mem[node] != -1) return mem[node];

        int sum = time[node];
        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (g[i][node] == 1) {
                max = max(max, cal(i, N));
            }
        }
        sum += max;
        mem[node] = sum;
        return sum;
    }
}
