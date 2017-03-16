package algospot.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex8_7_WildCard {

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
                // 완전탐색
                //                boolean matched = matchBrutal(W, S); 
                // 메모이제이션
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

        while (s < S.length() && w < W.length() &&
                (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
            w++;
            s++;
        }

        if (w == W.length()) {
            return cache[w][s] = (s == S.length()) ? 1 : 0;
        }

        if (W.charAt(w) == '*') {
            for (int skip = 0; s + skip <= S.length(); skip++) {
                if (matchMemoized(w + 1, s + skip) == 1) {
                    return cache[w][s] = 1;
                }
            }
        }

        return cache[w][s] = 0;
    }

    private static boolean matchBrutal(String w, String s) {
        int pos = 0;
        while (pos < w.length() && pos < s.length() && 
                (w.charAt(pos) == '?' || w.charAt(pos) == s.charAt(pos))) {
            pos++;
        }

        if (pos == w.length()) {
            return pos == s.length();
        }

        if (w.charAt(pos) == '*') {
            for (int skip = 0; pos + skip <= s.length(); skip++) {
                if (matchBrutal(w.substring(pos + 1), s.substring(pos + skip))) {
                    return true;
                }
            }
        }

        return false;
    }

}
