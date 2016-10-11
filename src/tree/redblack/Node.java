package tree.redblack;

public class Node {

    public static final int BLACK = 0;
    public static final int RED = 1;

    int mValue;
    Node mLeft;
    Node mRight;
    Node mParent;
    int color;

    public Node(int value) {
        this(value, BLACK);
    }

    public Node(int value, int color) {
        this.mValue = value;
        this.color = color;
    }

    public void add(Node node) {
        if (node.mValue < mValue) {
            if (mLeft == null) {
                mLeft = node;
                mLeft.mParent = this;
            } else {
                mLeft.add(node);
            }
        } else {
            if (mRight == null) {
                mRight = node;
                mRight.mParent = this;
            } else {
                mRight.add(node);
            }
        }
    }
}
