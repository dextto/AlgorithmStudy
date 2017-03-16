package algospot.book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ex8_9_TrianglePath {

    static BufferedReader r;
    
    static int[][] triangle;
    static int[][] cache;
    static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        String inFile = "TrianglePath.in";
        r = new BufferedReader(new FileReader(inFile));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            n = Integer.parseInt(r.readLine());
            makeTriangle(n);
            cache = new int[100][100];
            System.out.println(path2(0, 0));
        }

        r.close();
    }

    private static int path2(int y, int x) {
        if (y == n - 1) return triangle[y][x];
        if (cache[y][x] != 0) return cache[y][x];
        
        return cache[y][x] = triangle[y][x] + Math.max(path2(y + 1, x), path2(y + 1, x + 1));
    }

    private static void makeTriangle(int n) throws IOException {
        triangle = new int[100][100];
        for (int j = 0; j < n; j++) {
            String[] str = r.readLine().split(" "); 
            for (int i = 0; i < str.length; i++) {
                triangle[j][i] = Integer.parseInt(str[i]);
            }
        }
    }

}
