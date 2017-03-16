package algospot.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ex6_5_Picnic {

    static BufferedReader r;
    static BufferedWriter w;
    
    static int n;
    static boolean areFriends[][];

    public static void main(String[] args) throws NumberFormatException, IOException {
        String inFile = "Picnic.in"; //args[0];
        String outFile = (inFile.split(".in"))[0] + ".out";
        r = new BufferedReader(new FileReader(inFile));
        w = new BufferedWriter(new FileWriter(outFile));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            makeFriends();
            boolean[] taken = new boolean[10];
            int parings = countParings(taken);
            w.write(Integer.toString(parings));
            w.write("\n");
        }

        r.close();
        w.close();
    }

    private static void makeFriends() throws IOException {
        areFriends = new boolean[10][10];
        String str[] = r.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        int c = Integer.parseInt(str[1]);
        String[] pairs = r.readLine().split(" ");
        for(int i = 0; i < c; i++) {
            areFriends[Integer.parseInt(pairs[i * 2])][Integer.parseInt(pairs[i * 2 + 1])] = true;
            areFriends[Integer.parseInt(pairs[i * 2 + 1])][Integer.parseInt(pairs[i * 2])] = true;
        }
    }

    static int countParings(boolean taken[]) {
        int firstFree = -1;
        for (int i = 0; i < n; i++) {
            if (!taken[i]) {
                firstFree = i;
                break;
            }
        }

        if (firstFree == -1) {
            return 1;
        }

        int ret = 0;
        for (int pairWith = firstFree + 1; pairWith < n; ++pairWith) {
            if (!taken[pairWith] && areFriends[firstFree][pairWith]) {
                taken[firstFree] = true;
                taken[pairWith] = true;
                ret += countParings(taken);
                taken[firstFree] = false;
                taken[pairWith] = false;
            }
        }

        return ret;
    }
}
