package PukyungUniv;

import java.util.ArrayList;
import java.util.List;

// 하나의 그래프에 대한 인접행렬이 입력으로 주어질 때 그 그래프에 대한 인접리스트를 만드는 프로그램을 작성하라.
// 입력으로 먼저 정점의 개수 n이 주어지고, 이어서 n*n크기의 행렬이 주어진다. 인접리스트를 만든 후
// 각 정점에 대해서 인접한 정점들을 출력하라. 정점의 번호는 1번에서 n번까지이다. 
// 아래의 입출력 예는 다음 그림의 그래프의 경우이다. (출력만 해서는 안되며 실제로 인접리스트를 만들어야 한다.)

public class Ex12_1_Graph_AdjacentList {
    public static String in[] = { "5",
            "0 1 0 0 1",
            "1 0 1 1 1",
            "0 1 0 1 0",
            "0 1 1 0 1",
            "1 1 0 1 0" };

    public static void main(String[] args) {
        int[][] adjMatrix = makeAdjacentMatrix(in);
        List<Integer>[] adjList = makeAdjacentList(adjMatrix);
        printAdjacentList(adjList);
    }

    private static int[][] makeAdjacentMatrix(String[] in) {
        final int n = Integer.parseInt(in[0]);
        int[][] adjMatrix = new int[n][n];
        for (int i = 1; i < in.length; i++) {
            String[] edges = in[i].split(" ");
            for (int j = 0; j < n; j++) {
                adjMatrix[i - 1][j] = Integer.parseInt(edges[j]);
            }
        }

//        printAdjacentMatrix(adjMatrix);

        return adjMatrix;
    }

    private static void printAdjacentMatrix(int[][] adjMatrix) {
        for (int[] row : adjMatrix) {
            for (int item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Integer>[] makeAdjacentList(int[][] adjMatrix) {
        final int n = adjMatrix.length;
        List<Integer>[] adjList = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            adjList[i] = list;
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] == 1) {
                    list.add(j);
                }
            }
        }
        return adjList;
    }

    private static void printAdjacentList(List<Integer>[] adjList) {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print((i + 1) + ": ");
            List<Integer> list = adjList[i];
            for (Integer edge : list) {
                System.out.print((edge + 1) + " ");
            }
            System.out.println();
        }
    }
}
