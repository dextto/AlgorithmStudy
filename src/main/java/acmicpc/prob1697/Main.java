package acmicpc.prob1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int MAX = 100_000;
    static BufferedReader r;

    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));

        String line = r.readLine();

        String[] s = line.split(" ");
        int cur = Integer.parseInt(s[0]);
        int target = Integer.parseInt(s[1]);
        System.out.println(count(cur, target));

        r.close();
    }

    static int[] visited;

    private static int count(int cur, int target) {
        visited = new int[MAX + 1];

        Queue<Integer> q = new LinkedList<>();
        q.add(cur);
        while(!q.isEmpty()) {
            int n = q.remove();
            if (n == target) {
                return visited[n];
            }

            if (n - 1 >= 0 && visited[n - 1] == 0) {
                q.add(n - 1);
                visited[n - 1] = visited[n] + 1;
            }
            if (n + 1 <= MAX && visited[n + 1] == 0) {
                q.add(n + 1);
                visited[n + 1] = visited[n] + 1;
            }
            if (n * 2 <= MAX && visited[n * 2] == 0) {
                q.add(n * 2);
                visited[n * 2] = visited[n] + 1;
            }
        }
        return 0;
    }
}
