package tryhelloworld;

import static org.junit.Assert.*;

import org.junit.Test;


public class MatrixProduct {
    @Test
    public void test() throws Exception {
        int[][] A = { {1,2,3}, {4,5,6} };
        int[][] B = { {1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
        assertEquals(2, A.length);
        assertEquals(3, B.length);
        assertEquals(4, B[0].length);
        MatrixProduct p = new MatrixProduct();
        int[][] res = p.product(A, B);
        assertEquals(2, res.length);
        assertEquals(4, res[0].length);
        assertEquals(38, res[0][0]);
        assertEquals(44, res[0][1]);
        assertEquals(83, res[1][0]);
        assertEquals(98, res[1][1]);
    }

    public int[][] product(int[][] A, int[][] B) {
        int [][] answer = new int[A.length][B[0].length];
        
        for (int i = 0; i < A.length; i++) { // 2
            for (int j = 0; j < B[0].length; j++) { // 4
                int sum = 0;
                for (int k = 0; k < B.length; k++) { // 3
                    sum += A[i][k] * B[k][j];
                }
                answer[i][j] = sum;
            }
        }

        return answer;
    }
}
