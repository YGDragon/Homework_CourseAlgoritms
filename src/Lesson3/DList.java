package Lesson3;

public class DList {
    private static Node head;
    private static Node tail;

    public DList() {
        head = null;
        tail = null;
    }

    public Node getHead() {
        return head;
    }

    // функция заполненич списка из элементов массива
    public static void setList(int[] arrayData) {
        for (int el : arrayData) {
            addAtEnd(el);
        }
    }

    // процедура добавление элемента в конец двусвязного списка
    public static void addAtEnd(int value) {
        Node node = new Node();
        node.value = value;
        if (head == null) {
            head = node;
            tail = node;
            return;
        } else if (tail == head) {
            tail = node;
            head.nextNode = tail;
            tail.prevNode = head;
            return;
        }
        Node before = tail;
        tail = node;
        tail.prevNode = before;
        before.nextNode = tail;
    }

    // процедура вывода элементов списка на печать
    public static void printNode(Node node) {
        if (node == null) {
            System.out.print("null");
            return;
        }
        while (true) {
            assert node != null;
            if (node.nextNode == null) break;
            System.out.print(node.value + " <-> ");
            node = node.nextNode;
        }
        System.out.println(node.value);
    }


    // функция разворота двусвязного списка
    public static Node reverse(Node head) {
        Node before = null;
        Node current = head;
        while (current != null) {
            swapSingle(current);
            before = current;
            current = current.prevNode;
        }
        if (before != null) {
            head = before;
        }
        return head;
    }

    // процедура замены ссылок предудущего на следующий элемент узла
    public static void swapSingle(Node node) {
        Node temp = node.prevNode;
        node.prevNode = node.nextNode;
        node.nextNode = temp;
    }

    // процедура перестановки предудущего и следующего элементов узла
    public static void swapDual(Node node1, Node node2) {
        Node temp1 = node1.prevNode;
        Node temp2 = node1.nextNode;
        Node temp3 = node2.prevNode;
        Node temp4 = node2.nextNode;

        node1.prevNode = temp2;
        node1.nextNode = temp4;

        node2.prevNode = temp1;
        node2.nextNode = temp3;


        if (node2.prevNode != null) {
            node2.prevNode.nextNode = node2;
        }
        if (node1.nextNode != null) {
            node1.nextNode.prevNode = node1;
        }
    }

    // процедура сортировки пузырьком двусвязного списка
    public static Node bubbleSort(Node head) {
        boolean marker;
        do {
            marker = false;
            Node current = head;
            Node after = head.nextNode;

            while (after != null) {
                if (after.value < current.value) {
                    swapDual(current, after);
                    marker = true;
                } else {
                    current.nextNode.prevNode = current;
                    current = current.nextNode;
                }
                if (head == current) {
                    head = after;
                }
                if (current.nextNode == null) {  // преопределение хвостового элемента списка
                    tail = current;
                }
                after = current.nextNode;

            }
        } while (marker);
        return head;
    }
}