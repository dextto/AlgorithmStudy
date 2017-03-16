package algorithm;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Node {
    int val;
    List<Node> neighbors = new ArrayList<Node>();

    Node(int n) {
        val = n;
    }

    public void addNeighbor(Node node) {
        neighbors.add(node);
    }
}

public class Graph {
    @Test
    public void test() throws Exception {
        Graph g = new Graph();
        g.add(1, 3);
        g.add(2, 3);
        g.add(0, 1);
        g.add(0, 2);
        assertTrue(g.find(1).neighbors.contains(g.find(3)));
        assertTrue(g.find(1).neighbors.contains(g.find(3)));
        assertTrue(g.find(2).neighbors.contains(g.find(0)));
        assertTrue(g.find(2).neighbors.contains(g.find(3)));

        g.getShortestPath(new ArrayList<Node>(), g.find(0), g.find(3));
        List<Node> path = g.shortestPath;
        assertTrue(path.get(0).val == 0);
        assertTrue(path.get(1).val == 1 || path.get(1).val == 2);
        assertTrue(path.get(2).val == 3);
    }

    Set<Node> graph = new HashSet<Node>();

    private Node find(int n) {
        for (Node node : graph) {
            if (node.val == n) {
                return node;
            }
        }
        return new Node(n);
    }

    public void add(int i, int j) {
        Node node = find(i);
        Node neighbor = find(j);
        node.addNeighbor(neighbor);
        neighbor.addNeighbor(node);
        graph.add(node);
        graph.add(neighbor);
    }

    List<Node> shortestPath = new ArrayList<Node>();

    public void getShortestPath(List<Node> path, int from, int to) {
        getShortestPath(path, find(from), find(to));
    }

    public void getShortestPath(List<Node> path, Node from, Node to) {
        if (path.contains(from)) {
            return;
        }
        path.add(from);
        if (from.equals(to)) {
            copyToShortestPath(path);
            path.remove(from);
            return;
        }
        if (from.neighbors.size() > 0) {
            for (Node neighbor : from.neighbors) {
                getShortestPath(path, neighbor, to);
            }
        }
        path.remove(from);
    }

    private void copyToShortestPath(List<Node> path) {
        if (shortestPath.size() != 0 && path.size() >= shortestPath.size()) {
            return;
        }
        shortestPath.clear();
        shortestPath.addAll(path);
    }
}
