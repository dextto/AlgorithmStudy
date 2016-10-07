package PukyungUniv;

import tree.Node;
import tree.BinaryTreeUtils;

public class Ex08_BinaryTreeTraverse {
    public static void main(String[] args) {
        Node root = new Node("fox");
        root.add(new Node("bear"));
        root.add(new Node("goose"));
        root.add(new Node("ant"));
        root.add(new Node("dog"));
        root.add(new Node("hippo"));
        root.add(new Node("cat"));
        root.add(new Node("eagle"));
        root.add(new Node("iguana"));
        
        BinaryTreeUtils.traverseInorder(root);
        System.out.println();
        BinaryTreeUtils.traversePreorder(root);
        System.out.println();
        BinaryTreeUtils.traversePostorder(root);
        System.out.println();
    }
}
