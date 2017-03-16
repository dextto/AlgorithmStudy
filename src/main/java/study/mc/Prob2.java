package study.mc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Prob2 {
    @Test
    public void test() throws Exception {
        assertEquals(1, 1);
    }

    static int max = 0;
    static int questionCount = 0;
    static int prevQ = -1;
    static int currQ = -1;
    static int ans = -1;
    static int candidateMin = -1;
    static int candidateMax = -1;

    static int CLOSE = 0;
    static int FAR = 1;
    static int SAME = 2;

    static BufferedReader r;

    static BufferedWriter w;

    public static void main(String[] args) throws NumberFormatException, IOException {
        //        r = new BufferedReader(new FileReader("Small.in"));
        //        w = new BufferedWriter(new FileWriter("small_ans.out"));
        //        r = new BufferedReader(new FileReader("B-small.in"));
        //        w = new BufferedWriter(new FileWriter("B-small_ans.out"));
        r = new BufferedReader(new FileReader("B-large.in"));
        w = new BufferedWriter(new FileWriter("B-large_ans.out"));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            getMaxAndQuestionCount(r.readLine());

            candidateMin = -1;
            candidateMax = -1;
            prevQ = -1;
            currQ = -1;
            ans = -1;
            getQandA(r.readLine()); // first question
            findCandidates();

            while (questionCount > 0) {
                checkCandidates();
            }
            System.out.println();
            w.write("\n");
        }

        r.close();
        w.close();
    }

    private static void checkCandidates() throws IOException {
        getQandA(r.readLine());

        if (candidateMin == candidateMax) {
            System.out.print("1 ");
            w.write("1 ");
            return;
        }

        if (ans == SAME) {
            if (prevQ == currQ) {
                print();
                return;
            } else {
                candidateMin = (prevQ + currQ) / 2;
                candidateMax = candidateMin;
                System.out.print("1 ");
                w.write("1 ");
                return;
            }
        }

        int center = Math.abs(currQ + prevQ) / 2;
        if (ans == CLOSE) {
            if ((prevQ < currQ && candidateMin > currQ)
                    || (currQ < prevQ && candidateMax < currQ)) {
                print();
                return;
            }
            if (prevQ < currQ && candidateMin <= center) {
                candidateMin = center + 1;
            }
            if (currQ < prevQ && candidateMax >= center) {
                if ((currQ + prevQ) % 2 == 0) {
                    candidateMax = center - 1;
                } else {
                    candidateMax = center;
                }
            }
        }
        if (ans == FAR) {
            if ((prevQ < currQ && candidateMax < prevQ)
                    || (currQ < prevQ && candidateMin > prevQ)) {
                print();
                return;
            }

            if (prevQ < currQ && candidateMax >= center) {
                if ((currQ + prevQ) % 2 == 0) { // OK
                    candidateMax = center - 1;
                } else {
                    candidateMax = center;
                }
            }
            if (currQ < prevQ && candidateMin <= center) {
                candidateMin = center + 1;
            }
        }

        print();
    }

    private static void print() throws IOException {
        System.out.print((candidateMax - candidateMin + 1) + " ");
        w.write((candidateMax - candidateMin + 1) + " ");
    }

    private static void findCandidates() throws IOException {
        getQandA(r.readLine());

        if (ans == CLOSE) {
            for (int i = 1; i <= max; i++) {
                if (Math.abs(prevQ - i) > Math.abs(currQ - i)) {
                    if (candidateMin == -1) {
                        candidateMin = i;
                    }
                    candidateMax = i;
                }
            }
        } else if (ans == FAR) {
            for (int i = 1; i <= max; i++) {
                if (Math.abs(prevQ - i) < Math.abs(currQ - i)) {
                    if (candidateMin == -1) {
                        candidateMin = i;
                    }
                    candidateMax = i;
                }
            }
        } else { // 거리가 같은 경우는 1가지 후보 밖에 없다.
            candidateMin = (prevQ + currQ) / 2;
            candidateMax = candidateMin;
        }

        print();
    }

    private static void getMaxAndQuestionCount(String line) {
        String[] splits = line.split(" ");
        max = Integer.parseInt(splits[0]);
        questionCount = Integer.parseInt(splits[1]);
    }

    private static void getQandA(String line) {
        String[] splits = line.split(" ");
        prevQ = currQ;
        currQ = Integer.parseInt(splits[0]);
        ans = Integer.parseInt(splits[1]);
        questionCount--;
    }

}
