package algospot.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ex7_8_Fence {

    static BufferedReader r;
    static BufferedWriter w;


    public static void main(String[] args) throws NumberFormatException, IOException {
        String inFile = "Fence.in";
        String outFile = (inFile.split(".in"))[0] + ".out";
        r = new BufferedReader(new FileReader(inFile));
        w = new BufferedWriter(new FileWriter(outFile));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            int[] fences = initFences();
            int ans = solve(fences, 0, fences.length - 1);
            w.write(ans + "\n");
            System.out.println(ans);
        }

        r.close();
        w.close();
    }

    private static int[] initFences() throws NumberFormatException, IOException {
        int n = Integer.parseInt(r.readLine());
        int[] fences = new int[n];
        String[] str = r.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            fences[i] = Integer.parseInt(str[i]);
        }
        return fences;
    }

    private static int solve(final int[] fences, int left, int right) {
        if (left == right) {
            return fences[left];
        }

        int mid = (left + right) / 2;
        int ret = Math.max(solve(fences, left, mid), solve(fences, mid + 1, right));

        int lo = mid;
        int hi = mid + 1;
        int height = Math.min(fences[lo], fences[hi]);
        ret = Math.max(ret, 2 * height);
        while (lo > left || hi < right) {
            if (hi < right && (lo == left || fences[lo - 1] < fences[hi + 1])) {
                hi++;
                height = Math.min(height, fences[hi]);
            } else {
                lo--;
                height = Math.min(height, fences[lo]);
            }
            ret = Math.max(ret, (hi - lo + 1) * height);
        }

        return ret;
    }
}
