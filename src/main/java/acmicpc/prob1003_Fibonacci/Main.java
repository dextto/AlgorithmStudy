package acmicpc.prob1003_Fibonacci;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class Main {

    @Test
    public void test0() throws URISyntaxException, IOException {
        int N = 0;
        callFibo(N);
        assertEquals(1, zeros[N]);
        assertEquals(0, ones[N]);
    }
    @Test
    public void test1() throws URISyntaxException, IOException {
        int N = 1;
        callFibo(N);
        assertEquals(0, zeros[N]);
        assertEquals(1, ones[N]);
    }
    @Test
    public void test2() throws URISyntaxException, IOException {
        int N = 2;
        callFibo(N);
        assertEquals(1, zeros[N]);
        assertEquals(1, ones[N]);
    }
    @Test
    public void test3() throws URISyntaxException, IOException {
        int N = 3;
        callFibo(N);
        assertEquals(1, zeros[N]);
        assertEquals(2, ones[N]);
    }

    static int[] zeros;
    static int[] ones;

    static void callFibo(int N) {
        zeros = new int[N + 2];
        ones = new int[N + 3];

        zeros[0] = 1; ones[0] = 0;
        zeros[1] = 0; ones[1] = 1;

        if (N > 1) {
            for (int i = 2; i <= N; i++) {
                zeros[i] = zeros[i - 1] + zeros[i - 2];
                ones[i] = ones[i - 1] + ones[i - 2];
            }
        }
    }

    static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(reader.readLine());

        while (cases-- > 0) {
            int N = Integer.parseInt(reader.readLine());
            callFibo(N);
            System.out.println(zeros[N] + " " + ones[N]);
        }
    }

}
