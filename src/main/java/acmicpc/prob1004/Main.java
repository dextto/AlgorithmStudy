package acmicpc.prob1004;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class Main {

    @Test
    public void test() throws URISyntaxException, IOException {
        assertEquals(3, cal(-5, 1, 12, 1,
                new int[] { 1, -3, 2, 5, -4, 12, 12 },
                new int[] { 1, -1, 2, 5, 5, 1, 1 },
                new int[] { 8, 1, 2, 1, 1, 1, 2 }));

        assertEquals(0, cal(-5, 1, 12, 1,
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 2 }));
    }

    static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        while (n-- > 0) {
            String[] str = reader.readLine().split(" ");
            int x1 = Integer.parseInt(str[0]);
            int y1 = Integer.parseInt(str[1]);
            int x2 = Integer.parseInt(str[2]);
            int y2 = Integer.parseInt(str[3]);
            int cn = Integer.parseInt(reader.readLine());
            int x[] = new int[cn];
            int y[] = new int[cn];
            int r[] = new int[cn];
            for (int i = 0; i < cn; i++) {
                str = reader.readLine().split(" ");
                x[i] = Integer.parseInt(str[0]);
                y[i] = Integer.parseInt(str[1]);
                r[i] = Integer.parseInt(str[2]);
            }
            cal(x1, y1, x2, y2, x, y, r);
        }
    }

    private static int cal(int x1, int y1, int x2, int y2, int[] x, int[] y, int[] r) {
        int res = 0;
        int n = x.length;
        for (int i = 0; i < n; i++) {
            if (distance(x1, y1, x[i], y[i]) < (r[i] * r[i])
                    && distance(x2, y2, x[i], y[i]) > (r[i] * r[i])) {
                res++;
            }
            if (distance(x2, y2, x[i], y[i]) < (r[i] * r[i])
                    && distance(x1, y1, x[i], y[i]) > (r[i] * r[i])) {
                res++;
            }
        }
        System.out.println(res);
        return res;
    }

    private static int distance(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
