package tryhelloworld;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Change {

    @Test
    public void test() throws Exception {
        Change instance = new Change();
//        assertEquals(4, instance.change(5, new int[] { 5, 2, 1 }));
//        assertEquals(4, instance.change(5, new int[] { 1, 2, 5 }));
//        assertEquals(5, instance.change(6, new int[] { 1, 2, 5 }));
//        assertEquals(6, instance.change(7, new int[] { 1, 2, 5 }));
//        assertEquals(7, instance.change(8, new int[] { 1, 2, 5 }));
//        assertEquals(8, instance.change(9, new int[] { 1, 2, 5 }));
//        assertEquals(10, instance.change(10, new int[] { 1, 2, 5 }));
        
//        assertEquals(4, instance.change(7, new int[] { 1, 3, 5 }));
//        assertEquals(2, instance.change(7, new int[] { 2, 3, 5 }));
//        assertEquals(3, instance.change(8, new int[] { 2, 3, 5 }));
//        assertEquals(3, instance.change(9, new int[] { 2, 3, 5 }));
        
        assertEquals(602, instance.change(1000, new int[] { 12, 42, 16, 43 }));
//        assertEquals(15147, instance.change(1000, new int[] { 8, 19, 38, 22, 28 }));
//        assertEquals(142739, instance.change(1000, new int[] { 18, 45, 27, 43, 30, 13, 50 }));
    }
    
    public int change(int total, int[] coin) {
        int[] dp = new int[1001];
        dp[0] = 1;
        for (int i = 0; i < coin.length; i++) {
            for (int j = coin[i]; j <= total; j++)
                dp[j] += dp[j - coin[i]];
        }
        return dp[total];
    }
    
    /*    public int change(int total, int[] coins) {
        return change(total, coins, 0);
    }

    public int change(int total, int[] coins, int idx) {
        if (total < 0) return 0;
        if (total == 0)  return 1;
        if (idx == coins.length) return 0;
        return change(total - coins[idx], coins, idx) + change(total, coins, idx + 1);
    }*/
    
    // 아래는 테스트로 출력해 보기 위한 코드입니다.
    public static void main(String[] args) {
        Change c = new Change();
        int[] coins = { 1, 2, 5 };
        System.out.println(c.change(5, coins));
    }
}
