// 완전 탐색말고 다른 방법으로 풀어보자
// TODO: 동적계획법 (9.11절 p317)
// TODO: 조합탐색 (11.2절 p402)

package algospot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TSP2 {

    static BufferedReader r;
    static BufferedWriter w;

    static int n = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        String inFile = "TSP1.in";
        String outFile = (inFile.split(".in"))[0] + ".out";
        r = new BufferedReader(new FileReader(inFile));
        w = new BufferedWriter(new FileWriter(outFile));

        int cases = Integer.parseInt(r.readLine());
        while (cases-- > 0) {
            n = Integer.parseInt(r.readLine());
            double dist[][] = makeDistanceMap(n);

            double ans = Double.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                boolean[] visited = new boolean[n];
                List<Integer> path = new ArrayList<>();
                path.add(i);
                visited[i] = true;
                double cand = shortestPath(path, visited, 0, dist);
                ans = Math.min(ans, cand);
            }

            System.out.println(ans);
            w.write(Double.toString(ans) + "\n");
        }

        r.close();
        w.close();
    }

    private static double[][] makeDistanceMap(int n) throws IOException {
        double dist[][] = new double[n][n];
        for (int i = 0; i < n; i++) {
            String str[] = r.readLine().split("  ");
            for(int j = 0; j < n; j++) {
                dist[i][j] = Double.parseDouble(str[j]);
            }
        }
        return dist;
    }

    private static double shortestPath(List<Integer> path, boolean[] visited, double currentLength
            , double[][] dist) {
        if (path.size() == n) {
            return currentLength;
        }

        double ret = Double.MAX_VALUE;
        for (int next = 0; next < n; next++) {
            if (visited[next]) continue;
            int here = last(path);
            path.add(next);
            visited[next] = true;
            double cand = shortestPath(path, visited, currentLength + dist[here][next], dist);
            ret = Math.min(ret, cand);
            visited[next] = false;
            removeLast(path);
        }
        return ret;
    }

    private static void removeLast(List<Integer> path) {
        path.remove(path.size() - 1);
    }

    private static Integer last(List<Integer> path) {
        return path.get(path.size() -1);
    }
}
