package PukyungUniv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Ex1_8 {
    // 이진 검색에서 찾는 값이 없을 경우 찾는 값보다 작거나 같으면서 가장 큰 값의 인덱스를 반환하는 프로그램을
    // 순환으로 작성하라. 

    @Test
    public void testEmpty() {
        assertEquals(-1, get(3, new int[] {}));
    }

    @Test
    public void testOneItem() {
        assertEquals(0, get(1, new int[] { 1 }));
    }

    @Test
    public void testOneItemNotFound() {
        assertEquals(0, get(3, new int[] { 1 }));
        assertEquals(-1, get(0, new int[] { 1 }));
    }

    @Test
    public void testThreeItemsFound() {
        int[] list = new int[] { 1, 3, 5 };
        assertEquals(0, get(1, list));
        assertEquals(1, get(3, list));
        assertEquals(2, get(5, list));
    }

    @Test
    public void testThreeItemsNotFound() {
        int[] list = new int[] { 1, 3, 5 };
        assertEquals(-1, get(0, list));
        assertEquals(0, get(2, list));
        assertEquals(1, get(4, list));
        assertEquals(2, get(6, list));
    }

    @Test
    public void testFourItemsFound() {
        int[] list = new int[] { 1, 3, 5, 7 };
        assertEquals(0, get(1, list));
        assertEquals(1, get(3, list));
        assertEquals(2, get(5, list));
        assertEquals(3, get(7, list));
    }

    @Test
    public void testFourItemsNotFound() {
        int[] list = new int[] { 1, 3, 5, 7 };
        assertEquals(0, get(2, list));
        assertEquals(1, get(4, list));
        assertEquals(2, get(6, list));
        assertEquals(3, get(8, list));
    }

    private int get(int item, int[] list) {
        if (list.length == 0) { 
            return -1;
        } else if (list.length == 1) {
            return (list[0] <= item) ? 0 : -1; 
        } else {
            return get(item, list, 0, list.length - 1);
        }
    }

    private int get(int item, int[] list, int start, int end) {
        if (start > end)
            return start - 1;

        int mid = (start + end) / 2;
        if (item == list[mid])
            return mid;
        else if (item < list[mid])
            return get(item, list, 0, mid - 1);
        else
            return get(item, list, mid + 1, end);

    }

}