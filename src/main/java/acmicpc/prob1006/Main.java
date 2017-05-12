package acmicpc.prob1006;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader r;

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

    private static int cal(int[] area, int N, int W) {
        int cnt = 0;
        int len = area.length;
        boolean[] checked = new boolean[len];
        for (int i = 0; i < len; i++) {
            int left = (i % N == 0) ? (i + N - 1) : i - 1;
            int right = ((i + 1) % N == 0) ? i + 1 - N : i + 1;
            int topBottom = (i < N) ? i + N : i - N;

            if (checked[i])
                continue;

            if (!checked[topBottom] && (area[topBottom] + area[i] <= W)) {
                checked[topBottom] = true;
            } else if (N > 1) {
                if (!checked[left] && (area[left] + area[i] <= W)) {
                    checked[left] = true;
                } else if (!checked[right] && (area[right] + area[i] <= W)) {
                    checked[right] = true;
                }
            }

            checked[i] = true;
            cnt++;
        }

        return cnt;
    }

}
