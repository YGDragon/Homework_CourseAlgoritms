package Lesson4;

public class RBTree {
    private static Node root;

    public Node getRoot() {
        return root;
    }

    // инициализация узла для КЧ-дерева
    static class Node {
        Integer data;
        String color;
        Node parent;
        Node leftChild;
        Node rightChild;

        public Node(Integer data) {
            this.data = data;
            this.color = "R";
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

        // определение ноды-деда
        static Node grandParentNode(Node node) {
            if (node != null && node.parent != null)
                return node.parent.parent;
            return null;
        }

        // определение ноды-дяди
        static Node uncleNode(Node node) {
            Node grand = grandParentNode(node);
            if (grand != null) {
                if (node.parent == grand.leftChild)
                    return grand.rightChild;
                else if (node.parent == grand.rightChild) {
                    return grand.leftChild;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return data + color;
        }
    }

    // процедура вывода КЧ-дерева на печать
    public void printTree(Node node) {
        if (node != null) {
            printTree(node.leftChild);
            System.out.println(node.data + node.color +
                    ".." + node.leftChild + ',' + node.rightChild);
            printTree(node.rightChild);
        }
    }

    // процедура добавления нового узла в КЧ-дерево
    public void insertNode(int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            root.color = "B";
            return;
        } else {
            Node downNode = root;
            Node upNode = null;
            while (downNode != null) {
                upNode = downNode;
                if (downNode.data < node.data) {
                    downNode = downNode.rightChild;
                } else {
                    downNode = downNode.leftChild;
                }
            }
            node.parent = upNode;
            if (upNode.data < node.data) {
                upNode.rightChild = node;
            } else {
                upNode.leftChild = node;
            }
        }
        balanceTree(node);
    }

    // процедура малого левого поворота
    public void leftRotate(Node node) {
        Node grand = Node.grandParentNode(node);
        if (grand != null) {
            if (grand.leftChild == node.parent) {
                grand.leftChild = node;
            } else if (grand.rightChild == node.parent) {
                grand.rightChild = node;
            }
            node.parent.rightChild = node.leftChild;
            node.parent.parent = node;
            node.parent.color = "R";
            node.leftChild = node.parent;
            node.parent = grand;
        } else {
            node.parent.rightChild = node.leftChild;
            node.parent.parent = node;
            node.parent.color = "R";
            node.leftChild = node.parent;
            node.parent = null;
        }
        node.color = "B";
        root.color = "B";
    }

    // процедура малого правого поворота
    public void rightRotate(Node node) {
        node.parent.leftChild = node.rightChild;
        node.parent.parent = node;
        node.parent.color = "R";
        node.rightChild = node.parent;
        Node grand = Node.grandParentNode(node);
        if (grand != null) {
            node.parent = grand;
            root = node;
        } else {
            node.parent = null;
        }
        node.color = "B";
        root.color = "B";
    }

    public void swapColor(Node node) {
        node.parent.leftChild.color = "B";
        node.parent.rightChild.color = "B";
        node.parent.color = "R";
    }

    // процедура балансировки красно-черного дерева
    public void balanceTree(Node node) {
        boolean needBalance;

        Node uncle = Node.uncleNode(node);
        // левый ребенок - красный и его левый ребенок - красный, то применяем малый правый поворот
        if (node.parent != null) {
            if (node == node.parent.leftChild && node.parent.color.equals("R")) {
                rightRotate(node);
            }
        }
        // оба ребенка красные – делаем смену цвета
        if (node.leftChild != null && node.rightChild != null) {
            if (node.leftChild.color.equals("R") && node.rightChild.color.equals("R")) {
                swapColor(node);
            }
            root.color = "B";
        }

        // правый ребенок – красный и левый - черный, то применяем малый левый поворот
        if (node.parent != null) {
            if (node == node.parent.rightChild) {
                if (uncle != null && uncle.color.equals("B")) {
                    leftRotate(node);
                } else {
                    leftRotate(node);
                }
            }
        }

        // восставление черного цвета корневой ноды
        root.color = "B";
    }
}