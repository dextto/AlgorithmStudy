package acmicpc.prob6603_lotto;

import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Main {

    @Test
    public void testLotto() throws URISyntaxException, IOException {
        File inFile = new File(getClass().getResource("/lotto.in").toURI());
        BufferedReader r = new BufferedReader(new FileReader(inFile));
        lotto(r);
        r.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        lotto(r);
        r.close();
    }

    private static void lotto(BufferedReader r) throws IOException {
        int[] candidates;
        String line;
        while ((line = r.readLine()).length() > 1) {
            String[] s = line.split(" ");
            int n = Integer.parseInt(s[0]);
            candidates = new int[n];
            for (int i = 0; i < n; i++) {
                candidates[i] = Integer.parseInt(s[i + 1]);
            }
//            System.out.println(Arrays.toString(candidates));

            List<Integer> nums = new ArrayList<>();
            lotto(candidates, 0, nums);
            System.out.println();
        }
    }

    private static void lotto(int[] candidates, int start, List<Integer> nums) {
        if (nums.size() == 6) {
            print(nums);
            return;
        }

        int n = candidates.length;
        for (int i = start; i < n; i++) {
            nums.add(candidates[i]);
            lotto(candidates, i + 1, nums);
            nums.remove(nums.size() - 1);
        }

    }

    private static void print(List<Integer> nums) {
        for (int n : nums) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

}
