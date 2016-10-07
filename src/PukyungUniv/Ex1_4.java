package PukyungUniv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Ex1_4 {
    // 한쪽 변의 길이가 n인 정사면체를 만들 때 사용된 공의 개수를 계산하는 프로그램을 리커전으로 작성하라

    @Test
    public void test() {
        assertEquals(1, count(1));
        assertEquals(4, count(2));
        assertEquals(10, count(3));
        assertEquals(20, count(4));
    }

    public int count(int n) {
        if (n == 1)
            return 1;
        else
            return (n * (n + 1) / 2) + count(n - 1);
    }

}