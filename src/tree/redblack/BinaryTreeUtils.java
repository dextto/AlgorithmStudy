package tree.redblack;

public class BinaryTreeUtils {

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
