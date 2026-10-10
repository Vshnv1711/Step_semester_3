/**
 * Category B - Problem 2: Cancelled Orders Cleanup
 *
 * Node removeAll(Node head, int code) removes every node whose data == code
 * and returns the (possibly new) head. Own Node class; no java.util.LinkedList.
 *
 * Complexity (n = length of list):
 *   Time : O(n)  - every node is visited exactly once
 *   Space: O(1)  - only the 'prev'/'cur' references are used (removal is in place)
 */
public class CancelledOrdersCleanup {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node removeAll(Node head, int code) {
        // 1) Remove matching nodes from the front by moving head
        while (head != null && head.data == code) {
            head = head.next;
        }
        // 2) Walk with a previous reference and skip matching nodes
        Node prev = head;
        while (prev != null && prev.next != null) {
            if (prev.next.data == code) {
                prev.next = prev.next.next;   // unlink; do NOT advance prev (next one may also match)
            } else {
                prev = prev.next;
            }
        }
        return head;
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
        Node r1 = removeAll(build(5, 3, 5, 8, 5), 5);
        System.out.println(render(r1));          // 3 -> 8 -> null
        check(render(r1), "3 -> 8 -> null");

        // Sample 2
        Node r2 = removeAll(build(5, 5, 5), 5);
        System.out.println(render(r2));          // null
        check(render(r2), "null");

        // Edge cases
        check(render(removeAll(null, 1)), "null");                         // empty list
        check(render(removeAll(build(1, 2, 3), 9)), "1 -> 2 -> 3 -> null"); // code not present
        check(render(removeAll(build(1, 2, 2, 2, 3), 2)), "1 -> 3 -> null"); // consecutive in middle
        check(render(removeAll(build(2, 2, 1, 2), 2)), "1 -> null");        // front and tail
        check(render(removeAll(build(7), 7)), "null");                      // single node removed
        check(render(removeAll(build(7), 8)), "7 -> null");                 // single node kept
        System.out.println("All tests passed");
    }
}
