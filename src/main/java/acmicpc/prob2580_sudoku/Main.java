package acmicpc.prob2580_sudoku;

import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;

public class Main {

    @Test
    public void test() throws IOException, URISyntaxException {
        File inFile = new File(getClass().getResource("/sudoku.in").toURI());
        r = new BufferedReader(new FileReader(inFile));
        makeBoard();
        sudoku(0, 0);
        r.close();
    }

    static BufferedReader r;
    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        makeBoard();
        sudoku(0, 0);
        r.close();
    }

    private static int[][] board = new int[9][9];
    private static void makeBoard() throws IOException {
        for (int i = 0; i < 9; i++) {
            String[] splits = r.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(splits[j]);
            }
        }
    }

    private static void sudoku(int i, int j) {
        if (i >= 9) {
            print();
            return;
        }

        int nextJ = (j == 8) ? 0 : j + 1;
        int nextI = (j == 8) ? i + 1 : i;

        if (board[i][j] == 0) {
            for (int n = 1; n <= 9; n++) {
                if (checkRow(i, n) && checkCol(j, n) && checkCell(i, j, n)) {
                    board[i][j] = n;
                    sudoku(nextI, nextJ);
                }
            }
            board[i][j] = 0;
        } else {
            sudoku(nextI, nextJ);
        }
    }

    private static boolean checkRow(int i, int n) {
        for (int j = 0; j < 9; j++) {
            if (board[i][j] == n) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkCol(int j, int n) {
        for (int i = 0; i < 9; i++) {
            if (board[i][j] == n) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkCell(int i, int j, int n) {
        int cellI = (i / 3) * 3;
        int cellJ = (j / 3) * 3;

        for (int ii = cellI; ii < cellI + 3; ii++) {
            for (int jj = cellJ; jj < cellJ + 3; jj++) {
                if (board[ii][jj] == n) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void print() {
        for (int[] row : board) {
            for (int n : row) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
            System.out.println();
    }
}
