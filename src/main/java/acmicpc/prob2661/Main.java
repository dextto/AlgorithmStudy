package acmicpc.prob2661;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class Main {

    private static int[] s = new int[81];

    @Test
    public void testSeries() {
        assertEquals("1", series(1, 0));
        assertEquals("12", series(2, 0));
        assertEquals("121", series(3, 0));
        assertEquals("1213", series(4, 0));
        assertEquals("12131", series(5, 0));
        assertEquals("121312", series(6, 0));
        assertEquals("1213121", series(7, 0));
        assertEquals("12131231", series(8, 0));
        assertEquals("121312313", series(9, 0));
    }

    static BufferedReader r;

    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(r.readLine());
        System.out.println(series(n, 0));
        r.close();
    }

    private static String series(int n, int cnt) {
        for (int i = 1; i <= cnt / 2; i++) {
            if (equal(cnt - i, i, cnt - i - i)) {
                return "";
            }
        }

        if (cnt == n) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < cnt; j++) {
                sb.append(s[j]);
            }
            return sb.toString();
        }

        for (int i = 1; i <= 3; i++) {
            s[cnt] = i;
            String ret = series(n,cnt + 1);
            if (!ret.isEmpty()) {
                return ret;
            }
        }

        return "";
    }

    private static boolean equal(int s1, int cnt, int s2) {
        for (int i = 0; i < cnt; i++) {
            if (s[s1 + i] != s[s2 + i]) {
                return false;
            }
        }
        return true;
    }

}
