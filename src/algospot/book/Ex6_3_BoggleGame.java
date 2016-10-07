package algospot.book;

import static org.junit.Assert.*;

import org.junit.Test;

public class Ex6_3_BoggleGame {
    @Test
    public void test() throws Exception {
        assertTrue(hasWord(1, 1, "PRETTY"));
        assertFalse(hasWord(1, 1, "PRETTO"));
        assertTrue(hasWord(2, 0, "GIRL"));
        assertTrue(hasWord(1, 2, "REPEAT"));
    }

    private static int dx[] = { -1, -1, -1, 1, 1, 1, 0, 0 };
    private static int dy[] = { -1, 0, 1, -1, 0, 1, -1, 1 };
    private static char[][] board = {
            { 'U', 'R', 'L', 'P', 'M' },
            { 'X', 'P', 'R', 'E', 'T' },
            { 'G', 'I', 'A', 'E', 'T' },
            { 'X', 'T', 'N', 'Z', 'Y' },
            { 'X', 'O', 'Q', 'R', 'S' }
    };

    static boolean hasWord(int y, int x, String word) {
        if (!inRange(y, x)) {
            return false;
        }
        if (board[y][x] != word.charAt(0)) {
            return false;
        }
        if (word.length() == 1) {
            return true;
        }

        for (int direction = 0; direction < 8; direction++) {
            int nextY = y + dy[direction];
            int nextX = x + dx[direction];
            if (hasWord(nextY, nextX, word.substring(1))) {
                return true;
            }
        }
        return false;
    }

    private static boolean inRange(int y, int x) {
        if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
            return false;
        }
        return true;
    }
}
