package PukyungUniv;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

// N개의 서로 다른 정수들이 입력으로 주어진다. 그리고 다시 하나의 정수 K가 주어진다. N개의 정수들 중에 임의의 개수의 
// 정수들을 선택하여 그 합이 K가 되는 경우의 수를 구하는 프로그램을 작성하라. 
// 즉 원소들의 합이K가 되는 부분집합의 개수를 구하는 것이다. 

public class Ex3_1_N_Queens {

    int N = 10;
    int data[] = { 10, 2, -3, 5, 6, -7, 1, 4, 11, -1 };
    int K = 25;

    List<Integer> list = new ArrayList<>();
    
    @Test
    public void test() {
        System.out.println(countSolutions(0, 0));
    }

    private int countSolutions(int k, int sum) {
        if (k == N) {
            if (sum == K) {
                System.out.println(list);
                return 1;
            } else {
                return 0;
            }
        }

        list.add(data[k]);
        int withK = countSolutions(k + 1, sum + data[k]);
        list.remove(list.size() - 1);
        int withoutK = countSolutions(k + 1, sum);
        return withoutK + withK;
    }

}
