/**
 * Category B - Problem 3: Reverse the Queue
 *
 * Node reverse(Node head) reverses a singly linked list IN PLACE and returns
 * the new head. No new nodes are created. Own Node class; no java.util.LinkedList.
 *
 * Complexity (n = length of list):
 *   Time : O(n)  - each node is visited once
 *   Space: O(1)  - only the previous / current / next references
 */
public class ReverseTheQueue {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node reverse(Node head) {
        Node previous = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;   // remember the rest of the list
            current.next = previous;    // turn this node's pointer around
            previous = current;         // move previous forward
            current = next;             // move current forward
        }
        return previous;                // previous is the new head
    }

    // ---------- helpers ----------
    static Node build(int... values) {
        Node head = null, tail = null;
        for (int v : values) {
            Node n = new Node(v);
            if (head == null) head = n; else tail.next = n;
            tail = n;
        }
        return head;
    }

    static String render(Node head) {
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data).append(" -> ");
        }
        return sb.append("null").toString();
    }

    static void check(String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError("Expected: " + expected + " but got: " + actual);
        }
    }

    public static void main(String[] args) {
        // Sample 1
        Node r1 = reverse(build(1, 2, 3, 4));
        System.out.println(render(r1));          // 4 -> 3 -> 2 -> 1 -> null
        check(render(r1), "4 -> 3 -> 2 -> 1 -> null");

        // Sample 2
        Node r2 = reverse(build(9));
        System.out.println(render(r2));          // 9 -> null
        check(render(r2), "9 -> null");

        // Edge cases
        check(render(reverse(null)), "null");                              // empty list
        check(render(reverse(build(1, 2))), "2 -> 1 -> null");             // two nodes

        // "No new nodes may be created": the very same node objects must be reused
        Node a = new Node(1), b = new Node(2), c = new Node(3);
        a.next = b; b.next = c;
        Node newHead = reverse(a);
        if (newHead != c || c.next != b || b.next != a || a.next != null) {
            throw new AssertionError("reverse must reuse the original nodes");
        }

        // Reversing twice restores the original order
        check(render(reverse(reverse(build(5, 6, 7)))), "5 -> 6 -> 7 -> null");
        System.out.println("All tests passed");
    }
}
