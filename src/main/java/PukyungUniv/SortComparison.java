package PukyungUniv;

import java.util.Arrays;
import java.util.Random;

public class SortComparison {
    public static void main(String[] args) {
        Random rd = new Random();
        int N = 100000;
//        int N = 10000;
        int[] data = new int[N];
        int[] data_serial = new int[N];

        for (int i = 0; i < N; i++) {
            data[i] = rd.nextInt(N);
            data_serial[i] = i;
        }

        long begin = 0;
        long end = 0;
        
//        begin = System.currentTimeMillis();
//        Sort_SelectionSort.sort(Arrays.copyOf(data, N));
//        end = System.currentTimeMillis();
//        System.out.println("Selection Sort: " + (end - begin) / 1000.0);
//
//        begin = System.currentTimeMillis();
//        Sort_BubbleSort.sort(Arrays.copyOf(data, N));
//        end = System.currentTimeMillis();
//        System.out.println("Bubble Sort: " + (end - begin) / 1000.0);
//
//        begin = System.currentTimeMillis();
//        Sort_InsertionSort.sort(Arrays.copyOf(data, N));
//        end = System.currentTimeMillis();
//        System.out.println("Insertion Sort: " + (end - begin) / 1000.0);
//        
//        begin = System.currentTimeMillis();
//        Sort_MergeSort.sort(Arrays.copyOf(data, N));
//        end = System.currentTimeMillis();
//        System.out.println("Merge Sort: " + (end - begin) / 1000.0);
//        
        begin = System.currentTimeMillis();
        Sort_QuickSortWithLastPivot.sort(Arrays.copyOf(data, N));
        end = System.currentTimeMillis();
        System.out.println("Quick Sort - last pivot: " + (end - begin) / 1000.0);
//
//        begin = System.currentTimeMillis();
//        Sort_QuickSortWithMedianPivot.sort(Arrays.copyOf(data, N));
//        end = System.currentTimeMillis();
//        System.out.println("Quick Sort - median pivot: " + (end - begin) / 1000.0);
//        
//        begin = System.currentTimeMillis();
//        Sort_QuickSortWithLastPivot.sort(Arrays.copyOf(data_serial, N));
//        end = System.currentTimeMillis();
//        System.out.println("Quick Sort - last pivot, worst case: " + (end - begin) / 1000.0);
//        
//        begin = System.currentTimeMillis();
//        Sort_QuickSortWithMedianPivot.sort(Arrays.copyOf(data_serial, N));
//        end = System.currentTimeMillis();
//        System.out.println("Quick Sort - median pivot, worst case: " + (end - begin) / 1000.0);
        
        begin = System.currentTimeMillis();
        Sort_QuickSelection.sort(Arrays.copyOf(data, N), N);
        end = System.currentTimeMillis();
        System.out.println("Quick Selection: " + (end - begin) / 1000.0);
        
        begin = System.currentTimeMillis();
        Sort_HeapSort.sort(Arrays.copyOf(data, N));
        end = System.currentTimeMillis();
        System.out.println("Heap Sort: " + (end - begin) / 1000.0);
    }
}
