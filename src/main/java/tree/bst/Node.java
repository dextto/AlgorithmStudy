package tree.bst;

public class Node {

    protected String mValue;
    protected Node mLeft;
    protected Node mRight;
    protected Node mParent;

    public Node(String value) {
        this.mValue = value;
    }

    public final String getValue() {
        return mValue;
    }

    public void add(Node node) {
        String childValue = node.getValue();
        if ((childValue.compareTo(mValue) < 0)) {
            if (mLeft == null) {
                mLeft = node;
            } else {
                mLeft.add(node);
            }
        } else {
            if (mRight == null) {
                mRight = node;
            } else {
                mRight.add(node);
            }
        }
    }
}
