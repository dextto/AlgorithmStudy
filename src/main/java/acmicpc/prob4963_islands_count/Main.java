package acmicpc.prob4963_islands_count;

//import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//import static org.junit.Assert.assertEquals;

public class Main {
    /*@Test
    public void test() throws Exception {
        m = new int[][] {
                {-1,  0, -1},
                { 0, -1,  0},
        };
        assertEquals(1, island());
    }
    @Test
    public void test2() throws Exception {
        m = new int[][] {
                { 0, -1,  0},
                {-1,  0, -1},
        };
        assertEquals(1, island());
    }
    @Test
    public void test3() throws Exception {
        m = new int[][] {
                {-1,  0},
                { 0, -1},
                {-1,  0}
        };
        assertEquals(1, island());
    }
    @Test
    public void test4() throws Exception {
        m = new int[][] {
                { 0 , -1},
                { -1,  0},
                { 0 , -1}
        };
        assertEquals(1, island());
    }
    @Test
    public void test5() throws Exception {
        m = new int[][] {
                {-1, 0, -1, 0, 0},
                {-1, 0, 0, 0, 0},
                {-1, 0, -1, 0, -1},
                {-1, 0, 0, -1, 0},
        };
        assertEquals(3, island());
    }
*/
    private static int[][] m;
    private static int h;
    private static int w;
    private static int cnt = 0;
    private static Queue<Integer> queueI;
    private static Queue<Integer> queueJ;

    // m is map.
    //   -1: land
    //    0: sea
    //    1~N : land count
    private static int island() {
        h = m.length;
        w = m[0].length;
        queueI = new LinkedList<>();
        queueJ = new LinkedList<>();
        cnt = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (m[i][j] >= 0) continue;
                m[i][j] = ++cnt;
                queueI.add(i);
                queueJ.add(j);
                check();
            }
        }
        return cnt;
    }

    private static void check() {
        if (queueI.isEmpty()) return;

        int i = queueI.poll();
        int j = queueJ.poll();

        check(i - 1, j - 1);
        check(i - 1, j);
        check(i - 1, j + 1);
        check(i    , j - 1);
        check(i    , j + 1);
        check(i + 1, j - 1);
        check(i + 1, j);
        check(i + 1, j + 1);

        check();
    }

    private static void check(int i, int j) {
        if (i < 0 || i >= h || j < 0 || j >= w) return;
        if (m[i][j] == -1) {
            m[i][j] = cnt;
            queueI.add(i);
            queueJ.add(j);
        }
    }

    static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = reader.readLine();
            String[] str = line.split(" ");
            int w = Integer.parseInt(str[0]);
            int h = Integer.parseInt(str[1]);
            if (w == 0 && h == 0) return;

            m = new int[h][w];
            for (int i = 0; i < h; i++) {
                str = reader.readLine().split(" ");
                for (int j = 0; j < w; j++) {
                    int v = Integer.parseInt(str[j]);
                    m[i][j] = (v == 1) ? -1 : 0;
                }
            }
            System.out.println(island());
        }
    }

}
