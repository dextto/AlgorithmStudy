package PukyungUniv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Sort_QuickSelection {

    @Test
    public void test() {
        int[] data = { 1, 2, 2, 2, 3, 4 };
        assertEquals(2, sort(data, 3));
        
        data = new int[] { 3, 1, 6, 4, 2, 7, 8, 10, 5, 8, 9 };
        assertEquals(4, sort(data, 4));
    }

    public static int sort(int[] data, int k) {
        return select(data, 0, data.length - 1, k);
    }

    private static int select(int[] data, int p, int r, int k) {
        int q = partition(data, p, r);
        if (q == k - 1) {
            return data[q];
        } else if (q < k - 1) {
            return select(data, q + 1, r, k);
        } else {
            return select(data, p, q - 1, k);
        }
    }

    private static int partition(int[] data, int p, int r) {
        int pivot = data[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (data[j] <= pivot) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, r);
        return i + 1;
    }

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

}