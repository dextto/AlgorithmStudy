package algospot.book;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Ex7_6_QuadTree {

    static int idx = -1;

    @Test
    public void test() throws Exception {
        Ex7_6_QuadTree instance = new Ex7_6_QuadTree();
        assertEquals("w", instance.reverse("w"));
        idx = -1;
        assertEquals("xwbbw", instance.reverse("xbwwb"));
        idx = -1;
        assertEquals("xxbwwbbbw", instance.reverse("xbwxwbbwb"));
        idx = -1;
        assertEquals("xxwbxwwxbbwwbwbxwbwwxwwwxbbwb", instance.reverse("xxwwwbxwxwbbbwwxxxwwbbbwwwwbb"));
    }

    private String reverse(String s) {
        idx++;
        char head = s.charAt(idx);
        if (head == 'w' || head == 'b') {
            return String.valueOf(head);
        }
        
        String upperLeft = reverse(s);
        String upperRight = reverse(s);
        String lowerLeft = reverse(s);
        String lowerRight = reverse(s);
        return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
    }

}
