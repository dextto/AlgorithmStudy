package PukyungUniv;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class Sort_MergeSort {

    @Test
    public void test() {
        int[] data = { 1, 3, 5, 7, 2, 4, 6, 8 };
        sort(data);
        assertEquals(Arrays.toString(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }), Arrays.toString(data));
    }

    public static void sort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    private static void sort(int[] data, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            sort(data, p, q);
            sort(data, q + 1, r);
            merge(data, p, q, r);
        }
    }

    private static void merge(int[] data, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int k = p;
        int[] tmp = new int[data.length];
        while (i <= q && j <= r) {
            if (data[i] <= data[j]) {
                tmp[k++] = data[i++];
            } else {
                tmp[k++] = data[j++];
            }
        }
        while (i <= q) {
            tmp[k++] = data[i++];
        }
        while (j <= r) {
            tmp[k++] = data[j++];
        }

        for (int _i = p; _i <= r; _i++) {
            data[_i] = tmp[_i];
        }
    }

}