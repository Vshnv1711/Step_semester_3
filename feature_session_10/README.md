# Step Semester 3 — Feature Session 10
**Category B: Data Structures — Coding Assignments** (CodInClub | BridgeLabz)

## Structure
```
feature_session_10/
├── README.md
├── codingproblems/                      # Python: arrays, hashing, two pointers, sliding window, binary search
│   ├── problem1_class_topper.py         # 2D array traversal
│   ├── problem2_merge_tokens.py         # Two-pointer merge
│   ├── problem3_popular_order.py        # Hash map counting
│   ├── problem4_alert_windows.py        # Sliding window
│   └── problem5_ticket_slot.py          # Binary search (insert position)
└── assignments/                         # Java: singly linked lists (own Node class, no java.util.LinkedList)
    ├── PriorityTokenInsertion.java      # Problem 1: addFirst / addLast / insertAt
    ├── CancelledOrdersCleanup.java      # Problem 2: removeAll(head, code)
    └── ReverseTheQueue.java             # Problem 3: reverse(head) in place
```

## `codingproblems/` — complexity summary
| Problem | Technique | Time | Extra space |
|---|---|---|---|
| 1. Class Topper Finder | Nested loop, strict `>` so ties keep the smallest row index | O(m·n) | O(1) |
| 2. Merging Two Token Queues | Two-pointer merge (join + sort: O((m+n) log(m+n))) | O(m+n) | O(m+n) output |
| 3. Most Popular Canteen Order | Hash map + second pass for tie-break (re-scan: O(n²)) | O(n) | O(k) distinct items |
| 4. Hot Weather Alert Windows | Sliding window, `sum >= k·threshold` (recompute: O(n·k)) | O(n) | O(1) |
| 5. Ticket Price Slot Finder | Binary search, return `low` (linear scan: O(n)) | O(log n) | O(1) |

## `assignments/` — linked list problems (Java)
| Problem | Approach | Time | Extra space |
|---|---|---|---|
| 1. Priority Token Insertion | `addFirst` | O(1) | O(1) |
| | `addLast` (walk to the end; O(1) with a tail pointer) | O(n) | O(1) |
| | `insertAt(index, token)` (walk to node before index; O(1) when index is 0) | O(n) | O(1) |
| 2. Cancelled Orders Cleanup | Move head past matches, then walk with `prev` skipping matches | O(n) | O(1) |
| 3. Reverse the Queue | Three references: `previous`, `current`, `next`; no new nodes | O(n) | O(1) |

Examples:
- `101 -> 102 -> 103` + `addFirst(100)`, `addLast(104)`, `insertAt(2, 150)` gives `100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null`
- `5 -> 3 -> 5 -> 8 -> 5`, remove `5` gives `3 -> 8 -> null`
- `1 -> 2 -> 3 -> 4` reversed gives `4 -> 3 -> 2 -> 1 -> null`

## Run the tests
Each file has built-in assertions.
```bash
# Python 3
cd feature_session_10/codingproblems
for f in problem*.py; do python3 "$f"; done

# Java (JDK 11+)
cd ../assignments
for c in PriorityTokenInsertion CancelledOrdersCleanup ReverseTheQueue; do
  javac $c.java && java $c      # or: java $c.java
done
```
