package PukyungUniv;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recurstion_PowerSet {

    private static char[] data = "abcdef".toCharArray();
    private static int n = data.length;
    private static boolean[] include = new boolean[n];
    
    public static void main(String[] args) {
        powerSet(0); 
    }

    private static void powerSet(int k) {
        if (k == n) { // leaf node
            for (int i = 0; i < n; i++) {
                if (include[i]) System.out.print(data[i] + " ");
            }
            System.out.println();
            return;
        }

        include[k] = false; // 현재 노드를 포함하지 않았을 때
        powerSet(k + 1); // 다음 level 탐색

        include[k] = true; // 현재 노드를 포함했을 때
        powerSet(k + 1); // 다음 level 탐색
    }
}