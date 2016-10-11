package tree.redblack;

import static org.junit.Assert.assertEquals;
import static tree.redblack.Node.BLACK;
import static tree.redblack.Node.RED;

import org.junit.Test;

public class RedBlackTree {

    // TODO: Insert
    // TODO: Delete
    // TODO: Delete Fixup

    Node mRoot;

    private void makeTree1() {
        // https://dl.dropboxusercontent.com/u/16142350/algorithm2016/chap04.pdf
        // p71
        mRoot = new Node(7);
        mRoot.add(new Node(4));
        mRoot.add(new Node(11));
        mRoot.add(new Node(3));
        mRoot.add(new Node(6));
        mRoot.add(new Node(9));
        mRoot.add(new Node(18));
        mRoot.add(new Node(2));
        mRoot.add(new Node(14));
        mRoot.add(new Node(19));
        mRoot.add(new Node(12));
        mRoot.add(new Node(17));
        mRoot.add(new Node(22));
        mRoot.add(new Node(20));
    }

    @Test
    public void testLeftRotation() {
        makeTree1();
        leftRotation(mRoot.mRight);
        assertEquals(18, mRoot.mRight.mValue);
        assertEquals(11, mRoot.mRight.mLeft.mValue);
        assertEquals(19, mRoot.mRight.mRight.mValue);
        assertEquals(9, mRoot.mRight.mLeft.mLeft.mValue);
        assertEquals(14, mRoot.mRight.mLeft.mRight.mValue);
    }

    private void leftRotation(Node x) {
        Node y = x.mRight;
        x.mRight = y.mLeft;
        y.mLeft.mParent = x;
        y.mParent = x.mParent;
        if (x.mParent == null) {
            mRoot = y;
        } else {
            if (x.equals(x.mParent.mLeft)) {
                x.mParent.mLeft = y;
            } else {
                x.mParent.mRight = y;
            }
        }
        y.mLeft = x;
        x.mParent = y;
    }

    @Test
    public void testRightRotation() {
        makeTree1();
        leftRotation(mRoot.mRight);
        rightRotation(mRoot.mRight);
        assertEquals(11, mRoot.mRight.mValue);
        assertEquals(9, mRoot.mRight.mLeft.mValue);
        assertEquals(18, mRoot.mRight.mRight.mValue);
        assertEquals(14, mRoot.mRight.mRight.mLeft.mValue);
        assertEquals(19, mRoot.mRight.mRight.mRight.mValue);
    }

    private void rightRotation(Node y) {
        Node x = y.mLeft;
        y.mLeft = x.mRight;
        x.mRight.mParent = y;
        x.mParent = y.mParent;
        if (y.mParent == null) {
            mRoot = x;
        } else {
            if (y.equals(y.mParent.mLeft)) {
                y.mParent.mLeft = x;
            } else {
                y.mParent.mRight = x;
            }
        }
        x.mRight = y;
        y.mParent = x;
    }

    @Test
    public void testInsertFixup() {
        makeTree2();
    }

    private void makeTree2() {
        // https://dl.dropboxusercontent.com/u/16142350/algorithm2016/chap04.pdf
        // p80
        mRoot = new Node(11, BLACK);
        mRoot.add(new Node(2, RED));
        mRoot.add(new Node(14, BLACK));
        mRoot.add(new Node(1, BLACK));
        mRoot.add(new Node(7, BLACK));
        mRoot.add(new Node(15, RED));
        mRoot.add(new Node(5, RED));
        mRoot.add(new Node(8, RED));
        mRoot.add(new Node(4, RED)); // This is Last node with RED color
    }
}
