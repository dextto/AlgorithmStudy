package study.mc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Multiply {
    @Test
    public void test() throws Exception {
        assertEquals(1, 1);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        ArrayList<Integer> num = new ArrayList<Integer>();
        num.addAll(Arrays.asList(32, 52, 61, 60, 34, 16, 5));
        normalize(num);
    }

    static void normalize(List<Integer> num) {
        num.add(0);
        for (int i = 0; i < num.size(); i++) {
            if (num.get(i) < 0) {
                int borrow = (Math.abs(num.get(i)) + 9) / 10;
                num.set(i + 1, num.get(i + 1) - borrow);
                num.set(i, borrow * 10);
            } else {
                num.set(i + 1, (num.get(i + 1) + num.get(i)) / 10);
                num.set(i, num.get(i) % 10);
            }
        }

        while (num.size() > 1 && num.get(num.size() - 1) == 0) {
            System.out.print(num.remove(num.size() - 1));
        }
    }
}
