package tree;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTree {
    @Test
    public void test() {
        BinarySearchTree bst = new BinarySearchTree();
        Node fox = new Node("fox");
        Node bear = new Node("bear");
        Node goose = new Node("goose");
        Node ant = new Node("ant");
        Node dog = new Node("dog");
        Node hippo = new Node("hippo");
        Node cat = new Node("cat");
        Node eagle = new Node("eagle");
        Node iguana = new Node("iguana");

        bst.insert(fox);
        bst.insert(bear);
        bst.insert(goose);
        bst.insert(ant);
        bst.insert(dog);
        bst.insert(hippo);
        bst.insert(cat);
        bst.insert(eagle);
        bst.insert(iguana);

        assertThat(bst.mRoot.mValue, is("fox"));
        assertThat(bst.mRoot.mLeft.mValue, is("bear"));
        assertThat(bst.mRoot.mRight.mValue, is("goose"));
        assertThat(bst.mRoot.mLeft.mLeft.mValue, is("ant"));
        assertThat(bst.mRoot.mLeft.mRight.mValue, is("dog"));
        assertNull(bst.mRoot.mRight.mLeft);
        assertThat(bst.mRoot.mRight.mRight.mValue, is("hippo"));
        assertThat(bst.mRoot.mLeft.mRight.mLeft.mValue, is("cat"));
        assertThat(bst.mRoot.mLeft.mRight.mRight.mValue, is("eagle"));
        assertNull(bst.mRoot.mRight.mRight.mLeft);
        assertThat(bst.mRoot.mRight.mRight.mRight.mValue, is("iguana"));
        
        traverseInorder(bst.mRoot);
        System.out.println();
        traversePreorder(bst.mRoot);
        System.out.println();
        traversePostorder(bst.mRoot);
        
        bst.delete(bear);
        assertThat(bst.mRoot.mLeft.mValue, is("dog"));
        bst.delete(goose);
        assertThat(bst.mRoot.mRight.mValue, is("hippo"));
        bst.delete(fox);
        assertThat(bst.mRoot.mValue, is("hippo"));
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
    
    // TODO:
    public void search(String value) {
        
    }

    // TODO:
    public String max() {
        return null;
    }
    
    // TODO:
    public String min() {
        return null;
    }
    
    // TODO:
    public void delete(Node node) {
        Node parent = null;
        if (node.mLeft == null || node.mRight == null) {
        }
    }

    public static void traverseInorder(Node node) {
        if (node == null) return;
        
        traverseInorder(node.mLeft);
        System.out.print(node.mValue + "-");
        traverseInorder(node.mRight);
    }

    public static void traversePreorder(Node node) {
        if (node == null) return;
        
        System.out.print(node.mValue + "-");
        traversePreorder(node.mLeft);
        traversePreorder(node.mRight);
    }

    public static void traversePostorder(Node node) {
        if (node == null) return;
        
        traversePostorder(node.mLeft);
        traversePostorder(node.mRight);
        System.out.print(node.mValue + "-");
    }
}
