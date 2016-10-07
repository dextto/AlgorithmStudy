package tryhelloworld;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 과자 많이 먹기
 * 과자를 좋아하는 동우는 책상 위에 일렬로 놓아진 과자를 발견하였습니다. 
 * 과자에는 맛을 숫자로 평가한 종이가 붙어 있습니다. 동우는 맨 왼쪽부터 순서대로 과자를 먹기로 하였습니다. 
 * 동우는 먹을 과자를 고를 때 이전에 먹은 과자보다 맛이 더 좋은 과자만 먹습니다.
 * 제일 처음에 맛이 3인 과자를 먹었다면, 다음에는 4보다 작은 맛의 과자는 먹지 않습니다.
 * 책상위에 놓인 과자의 맛이 입력되면, 동우가 최대 과자를 몇 개를 먹을 수 있는지 반환해주는 eatCookie 함수를 완성하세요.
 * 
 * 예를 들어 [1, 4, 2, 6, 3, 4, 1, 5] 가 입력된다면 
 * 동우는 1, 3, 5, 6, 8번째 과자(각각의 맛은 1, 2, 3, 4, 5)를 골라 총 5개를 먹을 수 있고,
 * 5개보다 더 많이 먹을 수 있는 방법은 없으므로 5를 리턴하면 됩니다.
 */

public class EatCookie {

    @Test
    public void test() throws Exception {
        EatCookie instance = new EatCookie();
        assertEquals(5, instance.eatCookie(new int[] { 1, 4, 2, 6, 3, 4, 5, 1 }));
        assertEquals(4, instance.eatCookie(new int[] { 4, 3, 6, 1, 3, 4, 5, 1 }));
        assertEquals(5, instance.eatCookie(new int[] { 4, 1, 2, 3, 6, 4, 5, 1 }));
        assertEquals(5, instance.eatCookie(new int[] { 1, 4, 2, 6, 3, 4, 1, 5 }));
        assertEquals(
                28,
                instance.eatCookie(new int[] { 5, 65, 942, 584, 802, 211, 621, 473, 569, 327, 302,
                        472, 742, 916, 807, 274, 182, 213, 10, 322, 336, 951, 166, 34, 205, 494,
                        48, 22, 995, 1, 591, 489, 677, 787, 556, 527, 76, 852, 929, 354, 492, 630,
                        647, 628, 359, 366, 522, 824, 856, 160 }));
    }

    private static int[] cache;

    public int eatCookie(int[] cookies) {
        int ans = 0;
        for (int i = 0; i < cookies.length; i++) {
            cache = new int[500];
            ans = Math.max(ans, lis2(cookies, i));
        }
        return ans;
    }

    private static int lis2(int[] S, int start) {
        if (cache[start] != 0) {
            return cache[start];
        }
        
        int n = S.length;
        int ret = (cache[start] = 1);
        for (int next = start + 1; next < n; next++) {
            if (S[start] < S[next]) {
                ret = (cache[start] = Math.max(ret, lis2(S, next) + 1));
            }
        }
        return ret;
    }

    // 아래는 테스트로 출력해 보기 위한 코드입니다.
    public static void main(String[] args) {
        EatCookie e = new EatCookie();
        int[] cookies = { 1, 4, 2, 6, 3, 4, 1, 5 };
        System.out.println(e.eatCookie(cookies));
    }
}
