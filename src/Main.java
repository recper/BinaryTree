

public class Main {

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(4);
        tree.Add(2);
        tree.Add(10);
        tree.Add(1);
        tree.Add(3);
        tree.Add(12);
        tree.Add(9);
        tree.printByWidth();
    }
}
