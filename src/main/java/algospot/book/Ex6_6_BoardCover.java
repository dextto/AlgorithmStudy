package algospot.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ex6_6_BoardCover {

    static int coverType[][][] = new int[][][]{
        { { 0, 0 }, { 1, 0 }, { 0, 1 } },
        { { 0, 0 }, { 0, 1 }, { 1, 1 } },
        { { 0, 0 }, { 1, 0 }, { 1, 1 } },
        { { 0, 0 }, { 1, 0 }, { 1, -1 } }};

    static BufferedReader r;
    static BufferedWriter w;

    public static void main(String[] args) throws NumberFormatException, IOException {
        String inFile = "BoardCover.in";
        String outFile = (inFile.split(".in"))[0] + ".out";
        r = new BufferedReader(new FileReader(inFile));
        w = new BufferedWriter(new FileWriter(outFile));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            int[][] board = makeBoard();
            int ans = cover(board);
            System.out.println(ans);
            w.write(Integer.toString(ans) + "\n");
        }

        r.close();
        w.close();
    }

    private static int[][] makeBoard() throws IOException {
        String str[] = r.readLine().split(" ");
        int H = Integer.parseInt(str[0]);
        int W = Integer.parseInt(str[1]);
        int[][] board = new int[H][W];
        for (int j = 0; j < H; j++) {
            char[] line = r.readLine().toCharArray();
            for (int i = 0; i < W; i++) {
                board[j][i] = line[i] == '#' ? 1 : 0;
            }
        }
        //        printBoard(board);
        return board;
    }

    private static int cover(int[][] board) {
        int y = -1, x = -1;
        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i < board[0].length; i++) {
                if (board[j][i] == 0) {
                    y = j;
                    x = i;
                    break;
                }
            }
            if (y != -1) {
                break;
            }
        }

        if (y == -1) return 1;

        int ret = 0;
        for (int type = 0; type < 4; type++) {
            if (set(board, x, y, type, 1)) {
                ret += cover(board);
            }
            set(board, x, y, type, -1);
        }
        return ret;
    }


    private static boolean set(int[][] board, int x, int y, int type, int delta) {
        boolean ok = true;
        for(int i = 0; i < 3; i++) {
            int ny = y + coverType[type][i][0];
            int nx = x + coverType[type][i][1];
            if (ny < 0 || ny >= board.length || nx < 0 || nx >= board[0].length) {
                ok = false;
            } else if ((board[ny][nx] += delta) > 1) {
                ok = false;
            }
        }
        return ok;
    }

    private static void printBoard(int[][] board) {
        System.out.println(board.length);
        System.out.println(board[0].length);
        for (int[] l : board) {
            for(int b : l) {
                System.out.print(b);
            }
            System.out.println();
        }
    }

}
