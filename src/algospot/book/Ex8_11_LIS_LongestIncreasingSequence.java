package algospot.book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ex8_11_LIS_LongestIncreasingSequence {

    static BufferedReader r;
    
    private static int[] cache;
    private static int n;
    private static int[] S;

    public static void main(String[] args) throws IOException {
        String inFile = "LIS.in";
        r = new BufferedReader(new FileReader(inFile));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            /*r.readLine();
            List<Integer> list = makeSequence();
            int ans = lis(list);*/

            n = Integer.parseInt(r.readLine());
            S = makeSequence(n);
            int ans = 0;
            cache = new int[100];
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

    private static int lis(List<Integer> A) {
        if (A.isEmpty()) return 0;
        
        int ret = 0;
        for (int i = 0; i < A.size(); i++) {
            List<Integer> B = new ArrayList<>();
            for (int j = i + 1; j < A.size(); j++) {
                if (A.get(i) < A.get(j)) {
                    B.add(A.get(j));
                }
            }
            ret = Math.max(ret, 1 + lis(B));
        }
        return ret;
    }

    /**
     * @param n
     * @return
     * @throws IOException
     */
    private static int[] makeSequence(int n) throws IOException {
        int[] list = new int[n];
        String[] str = r.readLine().split("\\s");
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(str[i].trim());
        }
        return list;
    }

    private static List<Integer> makeSequence() throws IOException {
        List<Integer> list = new ArrayList<>();
        String[] str = r.readLine().split(" ");
        for (String s : str) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }

}
