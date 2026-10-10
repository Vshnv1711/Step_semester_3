# Step Semester 3 — Feature Session 8
**Category B: Coding Assignment — Linked Lists** (CodInClub | BridgeLabz)

## Structure
```
feature_session_8/
├── README.md
└── practiseproblems/
    └── PriorityTokenInsertion.java   # Problem 1: addFirst / addLast / insertAt (Java)
```

## Problem 1: Priority Token Insertion
Singly linked list with a hand-written `Node` class (`data`, `next`); `java.util.LinkedList` is **not** used.

| Operation | Time | Extra space |
|---|---|---|
| `addFirst(token)` | O(1) | O(1) |
| `addLast(token)` | O(n) — O(1) if a tail pointer is kept | O(1) |
| `insertAt(index, token)` | O(n) — O(1) when `index == 0` | O(1) |

`insertAt` walks to the node just before the index, sets the new node's `next` first, then links the previous node to it.

Example: `101 -> 102 -> 103` after `addFirst(100)`, `addLast(104)`, `insertAt(2, 150)` gives
`100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null`.

## Run the tests
Built-in assertions (JDK 11+):
```bash
cd feature_session_8/practiseproblems
javac PriorityTokenInsertion.java && java PriorityTokenInsertion
# or run the source file directly
java PriorityTokenInsertion.java
```
