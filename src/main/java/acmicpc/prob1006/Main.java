package acmicpc.prob1006;

import static java.lang.Math.min;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static BufferedReader r;

    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            String[] str = r.readLine().split(" ");
            int N = Integer.parseInt(str[0]);
            int W = Integer.parseInt(str[1]);
            int[] area = new int[N * 2];
            String[] a1 = r.readLine().split(" ");
            String[] a2 = r.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                area[i] = Integer.parseInt(a1[i]);
                area[i + N] = Integer.parseInt(a2[i]);
            }

            System.out.println(cal(area, N, W));
        }
    }

    static int[] dp;
    private static int cal(int[] area, int N, int W) {
        int len = area.length;
        dp = new int[len];
        Arrays.fill(dp, -1);
        boolean[] checked = new boolean[len];
        int cnt = cal(area, checked, N, W, 0);
        return cnt;
    }

    private static int cal(int[] area, boolean[] checked, int N, int W, int s) {
        if (s >= area.length) return 0;
        //if (dp[s] >= 0) return dp[s];
        if (checked[s]) return cal(area, checked, N, W, s + 1);


        if (s > 0) checked[s - 1] = true;

        int left = (s % N == 0) ? (s + N - 1) : s - 1;
        int right = ((s + 1) % N == 0) ? s + 1 - N : s + 1;
        int topBottom = (s < N) ? s + N : s - N;

        int cnt = 1;
        checked[s] = true;

        // bottom
        int subTopbottom = Integer.MAX_VALUE;
        if (!checked[topBottom] && (area[topBottom] + area[s] <= W)) {
            checked[topBottom] = true;
            subTopbottom = cal(area, checked, N, W, s + 1);
            checked[topBottom] = false;
        }

        // left
        int subLeft = Integer.MAX_VALUE;
        if (!checked[left] && (area[left] + area[s] <= W)) {
            checked[left] = true;
            subLeft = cal(area, checked, N, W, s + 1);
            checked[left] = false;
        }

        // right
        int subRight = Integer.MAX_VALUE;
        if (!checked[right] && (area[right] + area[s] <= W)) {
            checked[right] = true;
            subRight = cal(area, checked, N, W, s + 1);
            checked[right] = false;
        }

        // NO SELECTION
        int sub = cal(area, checked, N, W, s + 1);

        cnt += min(min(min(subTopbottom, subLeft), subRight), sub);
        dp[s] = cnt;

        checked[s] = false;
        return cnt;
    }

    @Test
    public void test() throws Exception {
        int[] area = new int[]{70, 60, 55, 43, 57, 60, 44, 50, 58, 40, 47, 90, 45, 52, 80, 40};
        assertEquals(11, cal(area, 8, 100));
    }

    @Test
    public void test2() throws Exception {
        int[] area = new int[]{51, 51};
        assertEquals(2, cal(area, 1, 100));
    }

    @Test
    public void test3() throws Exception {
        int[] area = new int[]{51, 51, 51, 51};
        assertEquals(4, cal(area, 2, 100));
    }

    @Test
    public void test4() throws Exception {
        int[] area = new int[]{51, 49, 51, 49};
        assertEquals(2, cal(area, 2, 100));
    }

    @Test
    public void test5() throws Exception {
        int[] area = new int[]{49, 51, 51, 49, 51, 51};
        assertEquals(4, cal(area, 3, 100));
    }

}
