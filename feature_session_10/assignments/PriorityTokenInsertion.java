/**
 * Category B - Problem 1: Priority Token Insertion
 *
 * Singly linked list with a hand-written Node class (no java.util.LinkedList).
 *
 * Complexity (n = current size of the list):
 *   addFirst(token)        : O(1)  - new node points at the old head
 *   addLast(token)         : O(n)  - walk to the last node
 *                            (O(1) if the list also stores a tail pointer)
 *   insertAt(index, token) : O(n)  - walk to the node before 'index'
 *                            (O(1) when index == 0, since it is just addFirst)
 * Extra space: O(1) for each operation (only one new node is created).
 */
public class PriorityTokenInsertion {

    // Node with data and next fields
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        private Node head;
        private int size;

        // Priority token: add at the front
        void addFirst(int token) {
            Node node = new Node(token);
            node.next = head;
            head = node;
            size++;
        }

        // Normal token: add at the end
        void addLast(int token) {
            Node node = new Node(token);
            if (head == null) {
                head = node;
            } else {
                Node cur = head;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = node;
            }
            size++;
        }

        // Rescheduled token: insert at 0-based index (0 <= index <= size)
        void insertAt(int index, int token) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException(
                        "index " + index + " out of range for size " + size);
            }
            if (index == 0) {
                addFirst(token);
                return;
            }
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {   // stop at node just before index
                prev = prev.next;
            }
            Node node = new Node(token);
            node.next = prev.next;   // 1) new node first points to the rest of the list
            prev.next = node;        // 2) then link previous node to the new node
            size++;
        }

        // Builds the "100 -> 101 -> ... -> null" string
        String render() {
            StringBuilder sb = new StringBuilder();
            for (Node cur = head; cur != null; cur = cur.next) {
                sb.append(cur.data).append(" -> ");
            }
            return sb.append("null").toString();
        }

        void print() {
            System.out.println(render());
        }
    }

    private static void check(String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError("Expected: " + expected + " but got: " + actual);
        }
    }

    public static void main(String[] args) {
        // Example from the problem: start = 101 -> 102 -> 103
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        list.print();   // 100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null
        check(list.render(), "100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null");

        // Edge cases
        SinglyLinkedList empty = new SinglyLinkedList();
        empty.insertAt(0, 1);                     // insert into empty list
        check(empty.render(), "1 -> null");
        empty.insertAt(1, 3);                     // index == size -> append
        empty.insertAt(1, 2);                     // middle
        empty.insertAt(0, 0);                     // front
        check(empty.render(), "0 -> 1 -> 2 -> 3 -> null");

        SinglyLinkedList none = new SinglyLinkedList();
        check(none.render(), "null");             // empty list prints just null

        try {
            list.insertAt(99, 5);                 // out of range
            throw new AssertionError("expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException expected) {
            // ok
        }
        System.out.println("All tests passed");
    }
}
