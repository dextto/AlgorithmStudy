package PukyungUniv;

import static org.junit.Assert.*;

import org.junit.Test;

public class Ex1_9 {
    // 오름차순으로 정렬된 두 정수 배열에서 교집합의 크기를 구하는 함수를 recursion으로 작성하라.

    @Test
    public void test() {
        assertEquals(0, intersection(new int[] { }, new int[] { 1 }));
        assertEquals(0, intersection(new int[] { 1 }, new int[] { }));
        assertEquals(3, intersection(new int[] { 1, 2, 3, 4 }, new int[] { 2, 3, 4, 5 }));
        assertEquals(0, intersection(new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 }));
    }

    private int intersection(int[] arr1, int[] arr2) {
        return intersection(arr1, arr2, 0, 0);
    }

    private int intersection(int[] arr1, int[] arr2, int idx1, int idx2) {
        if (idx1 == arr1.length || idx2 == arr2.length)
            return 0;
        else {
            if (arr1[idx1] < arr2[idx2]) {
                return intersection(arr1, arr2, idx1 + 1, idx2);
            } else if (arr1[idx1] > arr2[idx2]) {
                return intersection(arr1, arr2, idx1, idx2 + 1);
            } else {
                return 1 + intersection(arr1, arr2, idx1 + 1, idx2 + 1);
            }
        }
    }

}