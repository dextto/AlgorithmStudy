package PukyungUniv;

import static org.junit.Assert.*;

import org.junit.Test;

public class Ex1_6 {
    // 두 문자열의 사전식 순서를 비교하는 함수
    //      public int compare(String s1, String s2)
    // 를 작성하라. 두 문자열은 영문 알파벳 소문자로만 구성되어 있다고 가정하라. str1이 str2보다 사전식 순서가
    // 빠르면 -1, 같으면 0, 그리고 늦으면 1을 반환하라

    @Test
    public void test() {
        assertTrue(compare("a", "a") == 0);
        assertTrue(compare("a", "b") < 0);
        assertTrue(compare("b", "a") > 0);
        assertTrue(compare("aaa", "aaa") == 0);
        
        assertTrue(compare("a", "aa") < 0);
        assertTrue(compare("aaa", "aab") < 0);
    }

    String s1;
    String s2;
    public int compare(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        return compare(0, 0);
    }

    private int compare(int idx1, int idx2) {
        if (idx1 == s1.length() && idx2 == s2.length())
            return 0;
        else if ((idx1 == s1.length() && idx2 < s2.length())
                || (s1.charAt(idx1) < s2.charAt(idx2)))
            return -1;
        else if ((idx1 < s1.length() && idx2 == s2.length())
                || (s1.charAt(idx1) > s2.charAt(idx2)))
            return 1;
        else 
            return compare(idx1 + 1, idx2 + 1);
    }

}