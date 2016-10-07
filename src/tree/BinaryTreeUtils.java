package tree;

public class BinaryTreeUtils {

    public static void traverseInorder(Node node) {
        if (node == null) return;
        
        traverseInorder(node.getLeft());
        System.out.print(node.getValue() + "-");
        traverseInorder(node.getRight());
    }

    public static void traversePreorder(Node node) {
        if (node == null) return;
        
        System.out.print(node.getValue() + "-");
        traversePreorder(node.getLeft());
        traversePreorder(node.getRight());
    }

    public static void traversePostorder(Node node) {
        if (node == null) return;
        
        traversePostorder(node.getLeft());
        traversePostorder(node.getRight());
        System.out.print(node.getValue() + "-");
    }

}
