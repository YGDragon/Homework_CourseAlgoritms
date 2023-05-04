package Lesson4;

public class Task1 {
    public static void task1() {
        RBTree tree = new RBTree();
        tree.insertNode(5);
        tree.insertNode(3);
        tree.insertNode(7);
        tree.insertNode(4);
        tree.insertNode(8);
        tree.insertNode(2);
        tree.insertNode(9);
        tree.printTree(tree.getRoot());
        System.out.println();
        System.out.println("ROOT->" + tree.getRoot());
    }
}
