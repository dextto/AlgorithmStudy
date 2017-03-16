package PukyungUniv;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

// N=1, 2,…, 15 각각의 경우에 대해서 N-queens 문제의 해의 개수를 카운트하는 프로그램을 작성하라.

public class Ex3_2 {

    int[][] board;

    @Test
    public void test() {
        assertThat(countNQueensSolutions(1), is(1));
        assertThat(countNQueensSolutions(1), is(2));
        assertThat(countNQueensSolutions(1), is(3));
    }

    private int countNQueensSolutions(int n) {
        if (n < 1) return 0;
        return count(n);
    }

    private int count(int n) {
        // TODO Auto-generated method stub
        return 0;
    }

}
