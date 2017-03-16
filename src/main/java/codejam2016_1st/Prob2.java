package codejam2016_1st;
import static org.junit.Assert.*;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Prob2 {
    @Test
    public void test() throws Exception {
        int[] p1 = new int[] { -2, 3, 2, -2, -3, -4, -7, -5 };
        int[] p2 = new int[] { 2, -2, -3, -4, -7, -5, -2, 3 };
        assertTrue(isSame(p1, p2, p1.length));

        p2 = new int[] { 5, 7, 4, 3, -2, -2, 3, 2 };
        changeDirection(p2, p2.length);
        reverse(p2, p2.length);
        assertTrue(isSame(p1, p2, p1.length));
    }

    private static boolean isSame(int[] p1, int[] p2, int N) {
        for (int i = 0; i < N; i++) {
            if (p1[0] == p2[i]) {
                // 이 다음 인덱스부터 N-1번 다시 비교한다.
                boolean isSame = true;
                for (int j = 1; j < N; j++) {
                    if (p1[j] != p2[(i + j) % N]) {
                        isSame = false;
                        break;
                    }
                }
                if (isSame) return true;
            }
        }
        return false;
    }

    public static void changeDirection(int[] arr, int N) {
        int prevAhead = arr[N - 1];
        for (int i = 0; i < N; i++) {
            if (prevAhead * arr[i] > 0) { // 이전과 방향이 같으면
                prevAhead = arr[i];
                arr[i] = -arr[i]; // 부호를 바꾼다.
            } else {
                prevAhead = arr[i];
            }
        }
    }
    
    private static void reverse(int[] arr, int N) {
        for (int i = 0; i < N / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[N - i - 1];
            arr[N - i - 1] = temp;
        }
    }
    
    public static void main(String[] args) throws IOException {
        //String inFile = "problem_2_Set2.in";
        String inFile = args[0];
        String outFile = (inFile.split(".in"))[0] + ".out";

        BufferedReader r = new BufferedReader(new FileReader(inFile));
        BufferedWriter w = new BufferedWriter(new FileWriter(outFile));
        int T = Integer.parseInt(r.readLine()); // test case

        for (int _i = 0; _i < T; _i++) {
            int N = Integer.parseInt(r.readLine()); // number of points 
            int[] p1 = new int[N];
            int[] p2 = new int[N];

            String[] pStr1 = r.readLine().split(" ");
            String[] pStr2 = r.readLine().split(" ");

            for (int i = 0; i < N; i++) {
                p1[i] = Integer.parseInt(pStr1[i]);
                p2[i] = Integer.parseInt(pStr2[i]);
            }

            // 1. 먼저 같은 방향으로 돈다고 가정
            boolean isSame = isSame(p1, p2, N);
            if (isSame) {
                w.write("1");
                w.write("\n");
                continue;
            }

            // 2. 다른 방향으로 도는 지 체크
            // 방향 전환
            changeDirection(p2, N);
            reverse(p2, N);

            // 다시 비교
            isSame = isSame(p1, p2, N);
            
            w.write(isSame ? "1" : "0");
            w.write("\n");
        }

        r.close();
        w.close();
    }

}
