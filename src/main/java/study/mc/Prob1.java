package study.mc;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Prob1 {
    @Test
    public void test() throws Exception {
        makePoint();

        assertEquals(1, hashmap.get("80").intValue());
        assertEquals(3, hashmap.get("806").intValue());
        assertEquals(3, hashmap.get("060").intValue());
        assertEquals(2, hashmap.get("609").intValue());
        assertEquals(3, hashmap.get("094").intValue());
        assertEquals(0, hashmap.get("944").intValue());
        assertEquals(3, hashmap.get("449").intValue());
        assertEquals(3, hashmap.get("493").intValue());
        assertEquals(2, hashmap.get("935").intValue());
        assertEquals(3, hashmap.get("350").intValue());
        assertEquals(3, hashmap.get("502").intValue());
        assertEquals(3, hashmap.get("029").intValue());
        assertEquals(0, hashmap.get("299").intValue());
        assertEquals(3, hashmap.get("997").intValue());
        assertEquals(0, hashmap.get("977").intValue());
        assertEquals(2, hashmap.get("774").intValue());
        assertEquals(0, hashmap.get("744").intValue());
        assertEquals(3, hashmap.get("440").intValue());
        assertEquals(2, hashmap.get("409").intValue());
        assertEquals(0, hashmap.get("099").intValue());
    }

    static HashMap<String, Integer> hashmap = new HashMap<>(1000);

    static String near[] = { "07", "08", "09", "12", "14", "15", "21", "23", "24", "25", "26", "32"
            , "35", "36", "41", "42", "45", "47", "48", "51", "52", "53", "54", "55", "56", "57", "58"
            , "59", "62", "63", "65", "68", "69", "74", "75", "78", "70", "84", "85", "86", "87", "89"
            , "80", "95", "96", "98", "90" };

    public static void main(String[] args) throws IOException {
        makePoint();

        BufferedReader r = new BufferedReader(new FileReader("Large.in"));
        r.readLine(); // test case

        String line;
        while ((line = r.readLine()) != null) {
            String ans = "";
            int ansVal = Integer.MAX_VALUE;
            int dataNum = Integer.parseInt(line);
            int numPoint = 0;

            for (int i = 0; i < dataNum; i++) {
                line = r.readLine();
                numPoint = getPoint(line);
                if (numPoint < ansVal) {
                    ansVal = numPoint;
                    ans = line;
                }
            }
            System.out.println(ansVal + ": " + ans);
        }

        r.close();
    }

    private static int getPoint(String line) {
        int sum = hashmap.get(line.substring(0, 2));
        int index = 0;
        int length = line.length();
        while (index < length - 2) {
            sum += hashmap.get(line.substring(index, index + 3));
            index++;
        }

        return sum;
    }

    private static void makePoint() {
        for (int n = 0; n < 1000; n++) {
            if (n < 10) {
                if (n == 0) {
                    hashmap.put("00", 0);
                    hashmap.put("000", 0);
                } else if (n == 7 || n == 8 || n == 9) {
                    hashmap.put("0" + n, 1);
                    hashmap.put("00" + n, 2);
                } else {
                    hashmap.put("0" + n, 2);
                    hashmap.put("00" + n, 3);
                }
                continue;
            }

            String nStr = Integer.toString(n);
            if (n < 100) {
                if (n % 11 == 0) {
                    hashmap.put(nStr, 0);
                    hashmap.put("0" + nStr, 0);
                } else if (isNear(nStr)) {
                    hashmap.put(nStr, 1);
                    hashmap.put("0" + nStr, 2);
                } else {
                    hashmap.put(nStr, 2);
                    hashmap.put("0" + nStr, 3);
                }
            } else {
                if ((n % 100) % 11 == 0) {
                    hashmap.put(nStr, 0);
                } else if (isNear(nStr.substring(1))) {
                    hashmap.put(nStr, 2);
                } else {
                    hashmap.put(nStr, 3);
                }
            }
        }

        hashmap.put("123", 1);
        hashmap.put("321", 1);
        hashmap.put("456", 1);
        hashmap.put("654", 1);
        hashmap.put("789", 1);
        hashmap.put("987", 1);
        hashmap.put("147", 1);
        hashmap.put("741", 1);
        hashmap.put("258", 1);
        hashmap.put("852", 1);
        hashmap.put("369", 1);
        hashmap.put("963", 1);
        hashmap.put("159", 1);
        hashmap.put("951", 1);
        hashmap.put("357", 1);
        hashmap.put("753", 1);
        hashmap.put("580", 1);
        hashmap.put("085", 1);
    }

    private static boolean isNear(String n) {
        if (n.length() != 2) {
            return false;
        }

        for (String s : near) {
            if (s.equals(n)) {
                return true;
            }
        }
        return false;
    }
}
