package acmicpc.prob2580_sudoku;

import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

public class Main {

    static BufferedWriter w;
    @Test
    public void test() throws IOException, URISyntaxException {
        File inFile = new File(getClass().getResource("/sudoku.in").toURI());
        r = new BufferedReader(new FileReader(inFile));
        w = new BufferedWriter(new FileWriter(new File("sudoku.out")));
        makeBoard();
        // print();
        sudoku(0, 0);
//        sudoku();
        r.close();
        w.close();
    }

    static BufferedReader r;
    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        makeBoard();
        sudoku(0, 0);
        r.close();
    }

    static Map<Integer, List<Integer>> zeroIndexMap = new HashMap<>(); // key: row index, value: column indexes
    static int N = 9; //4; //9;
    static final int TOTAL = 45;// 10; //45;
    static final int CELL_SIZE = 3; //2; //3;
    private static int[][] board = new int[N][N];

    private static void makeBoard() throws IOException {
        zeroIndexMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            List<Integer> indexes = new ArrayList<>();
            String[] splits = r.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(splits[j]);
                if (board[i][j] == 0) {
                    indexes.add(j);
                }
            }
            if (indexes.size() > 0) {
                zeroIndexMap.put(i, indexes);
            }
        }
        initChecks();
    }
/*
    static Object[] rows;
    private static void sudoku() {
        rows = zeroIndexMap.keySet().toArray();
        sudoku(0, 0);
    }
    private static void sudoku(int i, int j) {
        if (i >= rows.length) {
            return;
        }
        Integer row = (Integer) rows[i];

        List<Integer> cols = zeroIndexMap.get(row);
        if (cols == null || j >= cols.size()) {
            return;
        }
        Integer col = cols.get(j);

        for (int n = 1; n <= N; n++) {
            board[row][col] = n;

            if (check()) {
                print();
                return;
            }

            int next_i = (j == cols.size() - 1) ? i + 1 : i;
            int next_j = (j == cols.size() - 1) ? 0 : j + 1;
            sudoku(next_i, next_j);
        }
        board[row][col] = 0;
    }
*/

    private static void sudoku(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            return;
        }

        int next_j = (j + 1) % N;
        int next_i = (j == N - 1) ? (i + 1) : i;

        int cellIdx = (i / CELL_SIZE) * CELL_SIZE + (j / CELL_SIZE);
        if (checkCols[j] && checkRows[i] && checkCells[cellIdx]) {
            sudoku(next_i, next_j);
            return;
        }

        if (board[i][j] == 0) {
            for (int n = 1; n <= N; n++) {
                board[i][j] = n;

                if (check()) { // TODO: check current position
                    print();
                    System.exit(1);
//                    return;
                }

                sudoku(next_i, next_j);
            }

            board[i][j] = 0;

            checkRows[i] = false;
            checkCols[j] = false;
            checkRows[cellIdx] = false;
        }
        sudoku(next_i, next_j);
    }

    static boolean[] checkRows = new boolean[N];
    static boolean[] checkCols = new boolean[N];
    static boolean[] checkCells = new boolean[N];

    private static void initChecks() {
        for (int i = 0; i < N; i++) {
            checkRow(i);
            checkCol(i);
        }
        for (int i = 0; i < CELL_SIZE; i++) {
            for (int j = 0; j < CELL_SIZE; j++) {
                checkCell(i * 3, j * 3);
            }
        }
    }


    private static boolean check() {
        for (int i = 0; i < N; i++) {
            if (!checkRow(i) || !checkCol(i)) {
                return false;
            }
        }
        for (int i = 0; i < CELL_SIZE; i++) {
            for (int j = 0; j < CELL_SIZE; j++) {
//                int cellIdx = (i / CELL_SIZE) * CELL_SIZE + (j / CELL_SIZE);
                if (!checkCell(i * CELL_SIZE, j * CELL_SIZE)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkRow(int i) {
        if (checkRows[i]) {
            return true;
        }

        int sum = 0;
        int[] row = board[i];
        for (int n : row) {
            sum += n;
        }

        boolean ret = (sum == TOTAL);
        checkRows[i] = ret;
        return ret;
    }

    private static boolean checkCol(int j) {
        if (checkCols[j]) {
            return true;
        }

        int sum = 0;
        for (int i = 0; i < N ; i++) {
            sum += board[i][j];
        }

        boolean ret = (sum == TOTAL);
        checkCols[j] = ret;
        return ret;
    }

    private static boolean checkCell(int i, int j) {
        int sum = 0;
        int cellIdx = (i / CELL_SIZE) * CELL_SIZE + (j / CELL_SIZE);
        if (checkCells[cellIdx]) {
            return true;
        }

        for (int ii = 0; ii < CELL_SIZE; ii++) {
            int row = (i / CELL_SIZE) * CELL_SIZE + ii;
            for (int jj = 0; jj < CELL_SIZE; jj++) {
                int col = (j / CELL_SIZE) * CELL_SIZE + jj;
                sum += board[row][col];
            }
        }

        boolean ret = (sum == TOTAL);
        checkCells[cellIdx] = ret;
        return ret;
    }
/*

    // TODO: memoization 적용: 가로, 세로, 각 셀의 합을 미리 계산해 두자.
    private static boolean check() {
        // print();

        int sum = 0;

        // 가로
        for (int i = 0; i < N; i++) {
            if (checkRows[i]) {
                continue;
            }

            sum = 0;
            int[] row = board[i];
            for (int n : row) {
                sum += n;
            }
            if (sum == TOTAL) {
                checkRows[i] = true;
            } else {
                return false;
            }
        }

        // 세로
        for (int j = 0; j < N; j++) {
            if (checkCols[j]) {
                continue;
            }

            sum = 0;
            for (int i = 0; i < N; i++) {
                sum += board[i][j];
            }
            if (sum == TOTAL) {
                checkCols[j] = true;
            } else {
                return false;
            }
        }

        // 현재 위치의 N칸
        for (int k = 0; k < N; k++) {
            sum = 0;
            int start_j = (k % CELL_SIZE) * CELL_SIZE;
            int start_i = (k / CELL_SIZE) * CELL_SIZE;

            int cellIdx = (start_i / N) + (start_j / N);
            if (checkCells[cellIdx]) {
                continue;
            }

            for (int i = start_i; i < start_i + CELL_SIZE; i++) {
                for (int j = start_j; j < start_j + CELL_SIZE; j++) {
                    sum += board[i][j];
                }
            }

            if (sum == TOTAL) {
                checkCells[cellIdx] = true;
            } else {
                return false;
            }
        }
        return true;
    }
*/

    private static void print() {
        for (int[] row : board) {
            for (int n : row) {
                System.out.print(n + " ");
            }
            System.out.println();
        }

        // temp
        System.out.println();
    }
    private static void print2() {
        try {
            for (int[] row : board) {
                for (int n : row) {
                    System.out.print(n + " ");
                    w.write(n + " ");
                }
                System.out.println();
                w.write("\n");
            }

            // temp
            System.out.println();
            w.write("\n");
        } catch (IOException e) {
        }
    }

}
