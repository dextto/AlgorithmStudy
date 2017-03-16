package PukyungUniv;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

public class Ex2_1 {

    public final int EMPTY = 0;
    public final int OCCUPIED = 1;
    public final int VISITED = 2;
    public final int BLOCKED = 3;

    // 0은 빈칸
    // 1은 다른 말이 놓인 칸  
    // 세로축이 x-축
    // 가로축이 y-축
    // 시작 위치와 목표 위치가 주어졌을 때 마가 움직일 수 있는지 여부를 판단하라.
    // 중간/목표 지점 모두 빈칸만 가능하다.
    public final int[][] board = {
            { 0, 0, 0, 1, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 0, 0, 0, 0, 1, 0 },
            { 0, 1, 0, 0, 0, 0, 1, 0 },
            { 1, 0, 0, 0, 0, 0, 1, 0 },
            { 0, 1, 0, 0, 0, 0, 1, 0 }
    };
    public final int N = board.length;

    int targetX;
    int targetY;

    List<Position> path;

    class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    private boolean canMove(int x, int y, int tx, int ty) {
        path = new ArrayList<>();
        targetX = tx;
        targetY = ty;
        return canMove(x, y);
    }

    private boolean canMove(int x, int y) {
        path.add(new Position(x, y));

        if (x < 0 || x >= N || y < 0 || y >= N || board[y][x] != EMPTY) {
            removeLast();
            return false;
        } else if (x == targetX && y == targetY) {
            return true;
        } else {
            board[y][x] = VISITED;

            if (canMove(x - 1, y - 2) || canMove(x + 1, y - 2)
                    || canMove(x + 2, y - 1) || canMove(x + 2, y + 1)
                    || canMove(x - 1, y + 2) || canMove(x + 1, y + 2)
                    || canMove(x - 2, y + 1) || canMove(x - 2, y - 1)) {
                return true;
            }

            board[y][x] = BLOCKED; // dead end;
            return false;
        }
    }

    private void removeLast() {
        if (path.size() > 0) {
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void test1() {
        assertFalse(canMove(1, 3, 6, 7));
    }

    @Test
    public void test2() {
        assertTrue(canMove(0, 0, 2, 1));
    }

    @Test
    public void test3() {
        assertTrue(canMove(0, 0, 7, 3));
    }

    @Test
    public void test4() {
        assertTrue(canMove(1, 1, 2, 0));
    }

    @After
    public void tearDown() {
        //        printPath();
    }

    private void printPath() {
        for (Position p : path) {
            System.out.println(p + "->");
        }
    }
}
