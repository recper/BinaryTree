import java.util.concurrent.ArrayBlockingQueue;

public class BinaryTree<T extends Comparable<T>> {
    public BinaryTree<T> Left;
    public BinaryTree<T> Right;
    T data;

    public BinaryTree() {
        data = null;
        Left = null;
        Right = null;
    }
    public BinaryTree(T a) {
        this();
        data = a;
    }

    private void printByDepth(BinaryTree tree) {
        System.out.print(tree.data + ", ");
        if (tree.Left != null)
            printByDepth(tree.Left);
        if (tree.Right != null)
            printByDepth(tree.Right);
    }
    private BinaryTree getElementsParent(BinaryTree tree, T element) {
        if (tree == null)
            return null;
        if (tree.Left.data.compareTo(element) == 0 || tree.Right.data.compareTo(element) == 0) {
            return tree;
        }
        if (tree.data.compareTo(element) > 0)
            return getElementsParent(tree.Right, element);
        else
            return getElementsParent(tree.Left, element);
    }

    public void Add(T element) {
        if (this.data == null) {
            this.data = element;
            return;
        }
        BinaryTree<T> current = this;
        while (true) {
            if (element.compareTo(current.data) > 0) {
                if (current.Right == null) {
                    current.Right = new BinaryTree<T>(element);
                    return;
                } else {
                    current = current.Right;
                    continue;
                }
            }
            if (element.compareTo(current.data) < 0) {
                if (current.Left == null) {
                    current.Left = new BinaryTree<T>(element);
                    return;
                } else {
                    current = current.Left;
                    continue;
                }
            }
            return; // If this.data == element, do nothing
        }
    }
    public boolean Exists(T element) {
        if (this.data == null) {
            return false;
        }
        BinaryTree<T> current = this;
        while (true) {
            if (element.compareTo(current.data) > 0) {
                if (current.Right == null) {
                    return false;
                } else {
                    current = current.Right;
                    continue;
                }
            }
            if (element.compareTo(current.data) < 0) {
                if (current.Left == null) {
                    return false;
                } else {
                    current = current.Left;
                    continue;
                }
            }
            return true; // If current.data == element
        }
    }
    public boolean Remove(T element) {
        BinaryTree<T> elementsParent = getElementsParent(this, element);
        if (elementsParent == null)
            return false;
        BinaryTree<T> nodeWithElementData;
        boolean isLeft = true;
        if (elementsParent.Right.data.compareTo(element) == 0) {
            nodeWithElementData = elementsParent.Right;
            elementsParent.Right = nodeWithElementData.Left;
            isLeft = false;
        } else {
            nodeWithElementData = elementsParent.Left;
            elementsParent.Left = nodeWithElementData.Left;
            isLeft = true;
        }
        if (nodeWithElementData.Left == null && nodeWithElementData.Right == null) {
            if (isLeft) {
                elementsParent.Left = null;
            } else {
                elementsParent.Right = null;
            }
            return true;
        }
        BinaryTree<T> i = nodeWithElementData.Left;
        if (i == null) {
            elementsParent.Left = nodeWithElementData.Right;
        }
        for (; i.Right != null; i = i.Right) ;
        i.Right = nodeWithElementData.Right;
        return true;
    }
    public void printByDepth() {
        printByDepth(this);
    }
    public void printByWidth() {
        ArrayBlockingQueue<BinaryTree<T>> queue = new ArrayBlockingQueue<BinaryTree<T>>(50);
        BinaryTree<T> current = this;
        queue.add(current);
        while (!queue.isEmpty()) {
            System.out.print((current = queue.poll()).data);
            if (current.Left != null) {
                queue.add(current.Left);
            }
            if (current.Right != null) {
                queue.add(current.Right);
            }
        }
    }
}
