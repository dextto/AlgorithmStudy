package tree;

public class Node {

    private String mValue;
    private Node mLeft;
    private Node mRight;

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

    public final Node getLeft() {
        return mLeft;
    }
    
    public final Node getRight() {
        return mRight;
    }

}
