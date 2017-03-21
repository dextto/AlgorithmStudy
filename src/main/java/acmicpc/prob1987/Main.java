package acmicpc.prob1987;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    @Test
    public void testCount() {
        board = initBoard(2, 4);
        fillBoard("CAAB", 0);
        fillBoard("ADCB", 1);
        assertEquals(3, getCount());
    }

    static BufferedReader r;
    static char[][] board;
    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        String[] splits = r.readLine().split(" ");
        int row = Integer.parseInt(splits[0]);
        int col = Integer.parseInt(splits[1]);

        board = initBoard(row, col);
        for (int i = 0; i < row; i++) {
            fillBoard(r.readLine(), i);
        }

        System.out.println(getCount());
        r.close();
    }

    private static char[][] initBoard(int row, int col) {
        return new char[row][col];
    }

    private static void fillBoard(String s, int row) {
        for (int i = 0; i < s.length(); i++) {
            board[row][i] = s.charAt(i);
        }
    }

    private static boolean[] visited;
    private static int getCount() {
        int row = 0;
        int col = 0;
        visited = new boolean[26];
        return count(row, col);
    }

    private static int count(int row, int col) {
        if (row < 0 || row >= board.length
                || col < 0 || col >= board[0].length) {
            return 0;
        }

        char current = (char) (board[row][col] - 'A');
        if (visited[current]) {
            return 0;
        }

        visited[current] = true;
        int count = 1;
        // 왼쪽
        count = Math.max(count, 1 + count(row, col - 1));
        // 오른쪽
        count = Math.max(count, 1 + count(row, col + 1));
        // 위로 방문
        count = Math.max(count, 1 + count(row - 1, col));
        // 아래로 방문
        count = Math.max(count, 1 + count(row + 1, col));

        visited[current] = false;
        return count;
    }

}
