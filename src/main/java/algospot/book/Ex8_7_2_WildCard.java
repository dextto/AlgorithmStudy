package algospot.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex8_7_2_WildCard {

    static BufferedReader r;
    static BufferedWriter w;
    static List<String> candidates;

    public static void main(String[] args) throws NumberFormatException, IOException {
        String inFile = "WildCard.in";
        String outFile = (inFile.split(".in"))[0] + ".out";
        r = new BufferedReader(new FileReader(inFile));
        w = new BufferedWriter(new FileWriter(outFile));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            W = r.readLine();
            int n = Integer.parseInt(r.readLine());
            candidates = new ArrayList<>();
            while (n-- > 0) {
                S = r.readLine();
                initCache();
                boolean matched = matchMemoized(0, 0) == 1;

                if (matched) {
                    candidates.add(S);
                }
            }
            candidates.sort((s1, s2) -> s1.compareTo(s2));
            candidates.forEach(System.out::println);
        }

        r.close();
        w.close();
    }

    static int[][] cache = new int[101][101];
    private static void initCache() {
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }
    }

    static String W;
    static String S;
    private static int matchMemoized(int w, int s) {
        if (cache[w][s] != -1) {
            return cache[w][s]; 
        }

        if (s < S.length() && w < W.length() &&
                (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
            return cache[w][s] = matchMemoized(w + 1, s + 1);
        }

        if (w == W.length()) {
            return cache[w][s] = (s == S.length()) ? 1 : 0;
        }

        if (W.charAt(w) == '*') {
            if (matchMemoized(w + 1, s) == 1 || 
                    (s < S.length() && matchMemoized(w, s + 1) == 1)) {
                return cache[w][s] = 1;
            }
        }

        return cache[w][s] = 0;
    }

}
