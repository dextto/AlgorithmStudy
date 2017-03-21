package acmicpc.prob9663;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class Main {

    @Test
    public void testNQueens() {
        assertEquals(1, queens(1));
        assertEquals(0, queens(2));
        assertEquals(0, queens(3));
        assertEquals(2, queens(4));
        assertEquals(92, queens(8));
    }

    static BufferedReader r;

    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(r.readLine());
        System.out.println(queens(n));
        r.close();
    }

    static int count = 0;
    static int cols[]; // cols[i]: i번째 row에서 queen이 놓여 있는 col값
    private static int queens(int n) {
        count = 0;

        cols = new int[n + 1];
        backtracking(1, n);
        return count;
    }

    private static void backtracking(int row, int n) {
        if (row == n) {
            // 마지막 row에는 직접 각 col에 대해 각각 검사해 본다
            for (int i = 1; i <= n; i++) {
                cols[row] = i;
                if (promising(row)) {
                    count++;
                }
            }
        } else {
            // 현재 row의 각 col에 queen을 놓았을 때에 대해 검사
            for (int i = 1; i <= n; i++) {
                cols[row] = i;
                if (promising(row)) {
                    backtracking(row + 1, n);
                }
            }
        }
    }

    private static boolean promising(int row) {
        for (int i = 1; i < row; i++) { // 위쪽 열들만 검사
            if (cols[i] == cols[row]) { // 같은 열에 놓았는지 검사
                return false;
            } else if (row - i == Math.abs(cols[row] - cols[i])) { // 같은 대각선에 놓았는지 검사
                return false;
            }
        }
        return true;
    }

}
