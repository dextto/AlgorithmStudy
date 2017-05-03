package acmicpc.prob2629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader r;
    static int[] w = new int[30];
    static boolean[] res = new boolean[15001];
    static boolean[] temp = new boolean[15001];

    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(r.readLine());
        String[] str = r.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(str[i]);
        }

        res[0] = true;
        temp[0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 15000; j++) {
                if (res[j]) {
                    int left = Math.abs(w[i] - j);
                    int right = Math.abs(w[i] + j);
                    if (left >= 0 && left <= 15000) temp[left] = true;
                    if (right >= 0 && right <= 15000) temp[right] = true;
                    temp[w[i]] = true;
                }
            }
            for (int j = 0; j <= 15000; j++) res[j] = temp[j];
        }

        int nn = Integer.parseInt(r.readLine());
        str = r.readLine().split(" ");
        for (int i = 0; i < nn; i++) {
            int t = Integer.parseInt(str[i]);
            System.out.print(t <= 15000 && res[t] ? "Y " : "N ");
        }
    }
}
