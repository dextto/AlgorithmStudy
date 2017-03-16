// 0번부터 차례대로 번호 매겨진 n개의 원소 중 toPick 개수만큼 고르는 모든 경우를 출력하라.

package algospot.book;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Ex6_2_pick_of_n {

    @Test
    public void test() throws Exception {
        Ex6_2_pick_of_n instance = new Ex6_2_pick_of_n();
        List<Integer> picked = new ArrayList<>();
        instance.pick(4, picked, 2);
    }

    private void pick(int n, List<Integer> picked, int toPick) {
        if (toPick == 0) {
            System.out.println(picked);
            return;
        }

        int smallest = picked.isEmpty() ? 0 : picked.get(picked.size() - 1) + 1;
        for (int next = smallest; next < n; next++) {
            picked.add(next);
            pick(n, picked, toPick - 1);
            picked.remove(picked.size() - 1);
        }
    }

}
