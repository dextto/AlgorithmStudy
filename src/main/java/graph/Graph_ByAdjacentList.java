package graph;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Before;
import org.junit.Test;

public class Graph_ByAdjacentList {
    private final int SIZE = 8;

    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    Node node7 = new Node(7);
    Node node8 = new Node(8);

    @SuppressWarnings("unchecked")
    List<Node>[] mGraph = new ArrayList[SIZE + 1]; // mGraph[0] is always null
    List<Node> mNodes = new ArrayList<>();

    // mDistance and mPredecessor are made by traverseBFS.
    int[] mDistance = new int[SIZE + 1];
    Node[] mPredecessor = new Node[SIZE + 1];

    @Before
    public void setUp() {
        makeGraph();
    }

    @Test
    public void testBFS() {
        Node start = node1;
        traverseBFS(start);

        assertThat(mDistance[node1.mKey], is(0));
        assertThat(mDistance[node8.mKey], is(2));
        assertThat(mDistance[node6.mKey], is(3));

        assertThat(mPredecessor[node1.mKey], is(nullValue()));
        assertThat(mPredecessor[node8.mKey], is(node3));
        assertThat(mPredecessor[node6.mKey], is(node5));
    }

    @Test
    public void testShortestPath() {
        traverseBFS(node1);

        assertThat(shortestPath(node1, node8), is(Arrays.asList(node1, node3, node8)));
        assertThat(shortestPath(node1, node6), anyOf(is(Arrays.asList(node1, node2, node5, node6)),
                is(Arrays.asList(node1, node3, node5, node6))));

        traverseBFS(node7);
        assertThat(shortestPath(node7, node6), is(Arrays.asList(node7, node3, node5, node6)));
    }

    @Test
    public void testDFS() {
        traverseDFSAll();
    }

    private void makeGraph() {
        mNodes.add(node1);
        mNodes.add(node2);
        mNodes.add(node3);
        mNodes.add(node4);
        mNodes.add(node5);
        mNodes.add(node6);
        mNodes.add(node7);
        mNodes.add(node8);

        List<Node> list1 = new ArrayList<>();
        list1.add(node2);
        list1.add(node3);
        mGraph[1] = list1;

        List<Node> list2 = new ArrayList<>();
        list2.add(node1);
        list2.add(node4);
        list2.add(node5);
        mGraph[2] = list2;

        List<Node> list3 = new ArrayList<>();
        list3.add(node1);
        list3.add(node7);
        list3.add(node5);
        list3.add(node8);
        mGraph[3] = list3;

        List<Node> list4 = new ArrayList<>();
        list4.add(node2);
        list4.add(node5);
        mGraph[4] = list4;

        List<Node> list5 = new ArrayList<>();
        list5.add(node2);
        list5.add(node4);
        list5.add(node3);
        list5.add(node6);
        mGraph[5] = list5;

        List<Node> list6 = new ArrayList<>();
        list6.add(node5);
        mGraph[6] = list6;

        List<Node> list7 = new ArrayList<>();
        list7.add(node3);
        list7.add(node8);
        mGraph[7] = list7;

        List<Node> list8 = new ArrayList<>();
        list8.add(node7);
        list8.add(node3);
        mGraph[8] = list8;
    }

    private void traverseBFS(Node start) {
        Queue<Node> queue = new LinkedBlockingQueue<>();
        mDistance = new int[SIZE + 1];
        mPredecessor = new Node[SIZE + 1];

        boolean[] visited = new boolean[SIZE + 1];

        visited[start.mKey] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Node u = queue.poll();
            int key = u.mKey;
            System.out.println(key);

            for (Node node : mGraph[key]) {
                if (!visited[node.mKey]) {
                    visited[node.mKey] = true;
                    mDistance[node.mKey] = mDistance[key] + 1;
                    mPredecessor[node.mKey] = u;
                    queue.add(node);
                }
            }
        }
    }

    private List<Node> shortestPath(Node from, Node to) {
        List<Node> list = new ArrayList<>();
        shortestPath(list, from, to);
        return list;
    }

    private void shortestPath(List<Node> list, Node from, Node to) {
        if (from.equals(to)) {
            list.add(from);
        } else if (mPredecessor[to.mKey] == null) {
            System.out.println("No path from -> to");
        } else {
            shortestPath(list, from, mPredecessor[to.mKey]);
            list.add(to);
        }
    }

    private void traverseDFS(boolean[] visited, Node node) {
        int key = node.mKey;
        visited[key] = true;
        System.out.println(key);
        for (Node n : mGraph[key]) {
            if (!visited[n.mKey]) {
                traverseDFS(visited, n);
            }
        }
    }

    // include all node even if graph is disconnected or directed graph.
    private void traverseDFSAll() {
        boolean[] visited = new boolean[SIZE + 1];
        for (Node node : mNodes) {
            if (!visited[node.mKey]) {
                traverseDFS(visited, node);
            }
        }
    }

}
