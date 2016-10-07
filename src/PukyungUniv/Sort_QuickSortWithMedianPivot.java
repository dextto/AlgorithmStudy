package PukyungUniv;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class Sort_QuickSortWithMedianPivot {

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
        if (r - p < 3) {
            manualSort(data, p, r);
            return;
        }

        medianOf3(data, p, r);
        int q = partition(data, p, r);
        sort(data, p, q - 1);
        sort(data, q + 1, r);
    }

    private static void medianOf3(int[] data, int p, int r) {
        int m = (p + r) / 2; // median index
        if (data[p] > data[m])
            swap(data, p, m);
        if (data[p] > data[r])
            swap(data, p, r);
        if (data[m] > data[r])
            swap(data, m, r);

        swap(data, m, r - 1);
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

    public static void manualSort(int[] intArray, int p, int r) {
        int size = r - p + 1;
        if (size <= 1)
            return;
        if (size == 2) {
            if (intArray[p] > intArray[r])
                swap(intArray, p, r);
            return;
        } else {
            if (intArray[p] > intArray[r - 1])
                swap(intArray, p, r - 1);
            if (intArray[p] > intArray[r])
                swap(intArray, p, r);
            if (intArray[r - 1] > intArray[r])
                swap(intArray, r - 1, r);
        }
    }

}