package algospot.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ex6_8_ClockSync {

    static BufferedReader r;
    static BufferedWriter w;

    final static String LINKED[] = {
        // 0123456789012345
        "xxx.............",
        "...x...x.x.x....",
        "....x.....x...xx",
        "x...xxxx........",
        "......xxx.x.x...",
        "x.x...........xx",
        "...x..........xx",
        "....xx.x......xx",
        ".xxxxx..........",
        "...xxx...x...x.."
    };

    private static final int CLOCKS = 16;
    private static final int SWITCHES = 10;
    private static final int INF = 99999;

    public static void main(String[] args) throws NumberFormatException, IOException {
        String inFile = "ClockSync.in";
        String outFile = (inFile.split(".in"))[0] + ".out";
        r = new BufferedReader(new FileReader(inFile));
        w = new BufferedWriter(new FileWriter(outFile));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            int[] clocks = initClocks();
            int ans = solve(clocks, 0);
            ans = (ans == INF) ? -1 : ans;
            w.write(ans + "\n");
            System.out.println(ans);
        }

        r.close();
        w.close();
    }

    private static int[] initClocks() throws IOException {
        int[] clocks = new int[CLOCKS];
        String str[] = r.readLine().split(" ");
        for (int i = 0; i < CLOCKS; i++) {
            clocks[i] = Integer.parseInt(str[i]);
        }
        return clocks;
    }

    private static int solve(int[] clocks, int swtch) {
        if (swtch == SWITCHES) {
            return areAligned(clocks) ? 0 : INF;
        }

        int ret = INF;
        for (int cnt = 0; cnt < 4; cnt++) {
            ret = Math.min(ret, cnt + solve(clocks, swtch + 1));
            push(clocks, swtch);
        }
        return ret;
    }

    private static boolean areAligned(int[] clocks) {
        for (int t : clocks) {
            if (t != 12) {
                return false;
            }
        }
        return true;
    }

    private static void push(int[] clocks, int swtch) {
        for (int clock = 0; clock < CLOCKS; clock++) {
            if (LINKED[swtch].charAt(clock) == 'x') {
                clocks[clock] += 3;
                if (clocks[clock] == 15) {
                    clocks[clock] = 3;
                }
            }
        }
    }
}
