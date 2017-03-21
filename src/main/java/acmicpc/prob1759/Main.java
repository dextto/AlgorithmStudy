package acmicpc.prob1759;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Main {

    @Test
    public void test() {
        //System.err.println(getCandidateCharArray("a t c i s w"));
        //char[] cs = {'a', 'b', '\0', '\0'};
        //System.err.println(cs);

        assertTrue(checkRule("acis"));
        assertFalse(checkRule("aeis"));
        assertFalse(checkRule("cdfs"));

        password("a t c i s w", 4);
    }

    static BufferedReader r;
    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        String[] splits = r.readLine().split(" ");
        int n = Integer.parseInt(splits[0]);
        int c = Integer.parseInt(splits[1]);

        String candidateChars = r.readLine();
        password(candidateChars, n);
        r.close();
    }

    private static void password(String candidateChars, int n) {
        char[] chars = getCandidateCharArray(candidateChars);
        for (int i = 0; i < candidateChars.length(); i++) {
            password(n, chars, i, "");
        }
    }

    private static void password(int n, char[] chars, int idx, String candidate) {
        if (idx >= chars.length) {
            return;
        }

        char addingChar = chars[idx];
        candidate += addingChar;
        if (candidate.length() == n) {
            if (checkRule(candidate)) {
                System.out.println(candidate);
            }
            return;
        }

        for (int i = idx + 1; i < chars.length; i++) {
            password(n, chars, i, candidate);
        }
    }

    static char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
    private static boolean checkRule(String candidate) {
        int vowelCount = 0;
        int consonantCount = 0;
        for (int i = 0; i < candidate.length(); i++) {
            char c = candidate.charAt(i);

            boolean isVowel = false;
            for (char v : vowels) {
                if (c == v) {
                    isVowel = true;
                    break;
                }
            }
            if (isVowel) {
                vowelCount++;
            } else {
                consonantCount++;
            }

            if (vowelCount > 0 && consonantCount > 1) {
                return true;
            }
        }

        return false;
    }

    private static char[] getCandidateCharArray(String candidateChars) {
        candidateChars = candidateChars.replaceAll(" ", "");
        char[] chars = candidateChars.toCharArray();
        Arrays.sort(chars);
        return chars;
    }
}
