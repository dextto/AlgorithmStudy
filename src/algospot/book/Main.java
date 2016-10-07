package algospot.book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader r;

    private static int[] cache;
    private static int n;
    private static int[] S;

    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            n = Integer.parseInt(r.readLine());
            S = makeSequence(n);
            int ans = 0;
            cache = new int[500];
            for (int i = 0; i < n; i++) {
                ans = Math.max(ans, lis2(i));
            }


            System.out.println(ans);

        }

        r.close();
    }

    private static int lis2(int start) {
        if (cache[start] != 0) {
            return cache[start];
        }

        int ret = (cache[start] = 1);
        for (int next = start + 1; next < n; next++) {
            if (S[start] < S[next]) {
                ret = (cache[start] = Math.max(ret, lis2(next) + 1));
            }
        }
        return ret;
    }

    private static int[] makeSequence(int n) throws IOException {
        int[] list = new int[n];
        String[] str = r.readLine().split("\\s");
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(str[i].trim());
        }
        return list;
    }

}
