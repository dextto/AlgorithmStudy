package tree.bst;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTree {
    // TODO: change value type as Object and change Node class that implements Comparable

    // This objects are for test
    BinarySearchTree bst;
    Node fox = new Node("fox");
    Node bear = new Node("bear");
    Node goose = new Node("goose");
    Node ant = new Node("ant");
    Node dog = new Node("dog");
    Node hippo = new Node("hippo");
    Node cat = new Node("cat");
    Node eagle = new Node("eagle");
    Node iguana = new Node("iguana");

    @Before
    public void setUp() {
        bst = new BinarySearchTree();
        bst.insert(bst.fox);
        bst.insert(bst.bear);
        bst.insert(bst.goose);
        bst.insert(bst.ant);
        bst.insert(bst.dog);
        bst.insert(bst.hippo);
        bst.insert(bst.cat);
        bst.insert(bst.eagle);
        bst.insert(bst.iguana);
    }

    @Test
    public void testInsert() {
        assertThat(bst.mRoot, is(bst.fox));
        assertThat(bst.mRoot.mLeft, is(bst.bear));
        assertThat(bst.mRoot.mRight, is(bst.goose));
        assertThat(bst.mRoot.mLeft.mLeft, is(bst.ant));
        assertThat(bst.mRoot.mLeft.mRight, is(bst.dog));
        assertNull(bst.mRoot.mRight.mLeft);
        assertThat(bst.mRoot.mRight.mRight, is(bst.hippo));
        assertThat(bst.mRoot.mLeft.mRight.mLeft, is(bst.cat));
        assertThat(bst.mRoot.mLeft.mRight.mRight, is(bst.eagle));
        assertNull(bst.mRoot.mRight.mRight.mLeft);
        assertThat(bst.mRoot.mRight.mRight.mRight, is(bst.iguana));

        traverseInorder(bst.mRoot);
        System.out.println();
        traversePreorder(bst.mRoot);
        System.out.println();
        traversePostorder(bst.mRoot);
    }

    @Test
    public void testSearch() {
        assertThat(bst.searchRecursive(bst.mRoot, "fox"), is(bst.fox));
        assertThat(bst.searchRecursive(bst.mRoot, "dog"), is(bst.dog));
        assertThat(bst.searchRecursive(bst.mRoot, "iguana"), is(bst.iguana));

        assertThat(bst.searchIterative(bst.mRoot, "fox"), is(bst.fox));
        assertThat(bst.searchIterative(bst.mRoot, "dog"), is(bst.dog));
        assertThat(bst.searchIterative(bst.mRoot, "iguana"), is(bst.iguana));
    }

    @Test
    public void testMin() {
        assertThat(bst.min(bst.mRoot), is(bst.ant));
    }

    @Test
    public void testMax() {
        assertThat(bst.max(bst.mRoot), is(bst.iguana));
    }

    @Test
    public void testSuccessor() {
        assertThat(bst.successor(bst.fox), is(bst.goose));
        assertThat(bst.successor(bst.bear), is(bst.cat));
        assertThat(bst.successor(bst.ant), is(bst.bear));
        assertThat(bst.successor(bst.eagle), is(bst.fox));
    }

    @Test
    public void testDelete() {
        bst.delete(bst.bear);
        assertThat(bst.mRoot.mLeft.mValue, is(bst.cat.mValue));
        bst.delete(bst.goose);
        assertThat(bst.mRoot.mRight.mValue, is(bst.hippo.mValue));
        bst.delete(bst.fox);
        assertThat(bst.mRoot.mValue, is(bst.hippo.mValue));
    }

    Node mRoot;

    public void insert(Node node) {
        Node parent = null;
        Node x = mRoot;
        while (x != null) {
            parent = x;
            if (node.mValue.compareTo(x.mValue) < 0) {
                x = x.mLeft;
            } else {
                x = x.mRight;
            }
        }

        node.mParent = parent;
        if (parent == null) {
            mRoot = node;
        } else {
            if (node.mValue.compareTo(parent.mValue) < 0) {
                parent.mLeft = node;
            } else {
                parent.mRight = node;
            }
        }
    }

    public Node searchRecursive(Node node, String value) {
        while (node != null) {
            int compare = value.compareTo(node.mValue);
            if (compare == 0) {
                return node;
            } else if (compare < 0) {
                return searchRecursive(node.mLeft, value);
            } else {
                return searchRecursive(node.mRight, value);
            }
        }
        return node;
    }

    public Node searchIterative(Node node, String value) {
        while (node != null) {
            int compare = value.compareTo(node.mValue);
            if (compare == 0) {
                return node;
            } else if (compare < 0) {
                node = node.mLeft;
            } else {
                node = node.mRight;
            }
        }
        return node;
    }

    public Node min(Node node) {
        while (node != null && node.mLeft != null) {
            node = node.mLeft;
        }
        return node;
    }

    public Node max(Node node) {
        while (node != null && node.mRight != null) {
            node = node.mRight;
        }
        return node;
    }

    // Successor of a node x is the node having smallest node in the largest nodes than x.
    // NOTE: Assume that all values are different.
    public Node successor(Node node) {
        if (node == null)
            return null;

        if (node.mRight != null) {
            return min(node.mRight);
        }

        Node parent = node.mParent;
        while (parent != null && node.equals(parent.mRight)) {
            node = parent;
            parent = parent.mParent;
        }
        return parent;
    }

    public void delete(Node node) {
        if (node == null)
            return;

        Node x = null;
        Node y = null; // node to be deleted

        if (node.mLeft == null || node.mRight == null) {
            y = node;
        } else {
            y = successor(node);
        }

        // case 1 or 2
        // case 1: y has no child
        // case 2: y has only one child
        x = (y.mLeft != null) ? y.mLeft : y.mRight;

        if (x != null) {
            x.mParent = y.mParent;
        }

        if (y.mParent == null) {
            mRoot = x;
        } else {
            if (y.equals(y.mParent.mLeft)) {
                y.mParent.mLeft = x;
            } else {
                y.mParent.mRight = x;
            }
        }

        // case 3 : y has two children
        if (!y.equals(node)) {
            node.mValue = y.mValue; // copy all data
        }
    }

    public static void traverseInorder(Node node) {
        if (node == null)
            return;

        traverseInorder(node.mLeft);
        System.out.print(node.mValue + "-");
        traverseInorder(node.mRight);
    }

    public static void traversePreorder(Node node) {
        if (node == null)
            return;

        System.out.print(node.mValue + "-");
        traversePreorder(node.mLeft);
        traversePreorder(node.mRight);
    }

    public static void traversePostorder(Node node) {
        if (node == null)
            return;

        traversePostorder(node.mLeft);
        traversePostorder(node.mRight);
        System.out.print(node.mValue + "-");
    }
}
