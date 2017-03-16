package algospot.book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ex8_16_Tiling2 {

    static BufferedReader r;
    
    static int[] cache;
    static int MOD = 1000000007;
    static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        String inFile = "Tiling2.in";
        r = new BufferedReader(new FileReader(inFile));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            n = Integer.parseInt(r.readLine());
            cache = new int[101];
            System.out.println(tiling(n));
        }

        r.close();
    }

    private static int tiling(int width) {
        if (width <= 1) return 1;

        int ret = cache[width];
        if (ret != 0) return ret;

        ret = ((tiling(width - 1)  % MOD) + (tiling(width - 2) % MOD)) % MOD;
        cache[width] = ret;
        return ret;
    }

}
