package algospot.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Ex8_5_JumpGame {

    static BufferedReader r;
    static BufferedWriter w;

    static int[][] board = new int[100][100];
    static int[][] cache = new int[100][100];

    public static void main(String[] args) throws NumberFormatException, IOException {
        String inFile = "JumpGame.in";
        String outFile = (inFile.split(".in"))[0] + ".out";
        r = new BufferedReader(new FileReader(inFile));
        w = new BufferedWriter(new FileWriter(outFile));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            int n = Integer.parseInt(r.readLine());
            initBoard(n);

            // 완전탐색
//            String ans = jump(n, 0, 0) ? "YES" : "NO";

            // 메모이제이션
            initCache(board.length);
            String ans = jump2(n, 0, 0) == 1 ? "YES" : "NO";

            w.write(ans + "\n");
            System.out.println(ans);
        }

        r.close();
        w.close();
    }

    private static void initCache(int n) {
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }
    }

    private static int jump2(int n, int y, int x) {
        if (y >= n || x >= n) return 0;
        if (y == n - 1 && x == n - 1) return 1;

        int ret = cache[y][x];
        if (ret != -1) {
            return ret;
        }

        int jumpSize = board[y][x];
        return cache[y][x] = jump2(n, y + jumpSize, x) | jump2(n, y, x + jumpSize);
    }

    private static boolean jump(int n, int y, int x) {
        if (y >= n || x >= n) return false;
        if (y == n - 1 && x == n - 1) return true;

        int jumpSize = board[y][x];
        return jump(n, y + jumpSize, x) || jump(n, y, x + jumpSize);
    }

    private static void initBoard(int n) throws NumberFormatException, IOException {
        for (int j = 0; j < n; j++) {
            String[] str = r.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                board[j][i] = Integer.parseInt(str[i]);
            }
        }
    }

}
