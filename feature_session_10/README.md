# Step Semester 3 — Feature Session 10
**Category B: Data Structures — Coding Assignment** (CodInClub | BridgeLabz)

Topics: arrays, hashing, two pointers, sliding window, binary search.

## Structure
```
feature_session_10/
├── README.md
└── codingproblems/
    ├── problem1_class_topper.py     # 2D array traversal
    ├── problem2_merge_tokens.py     # Two-pointer merge
    ├── problem3_popular_order.py    # Hash map counting
    ├── problem4_alert_windows.py    # Sliding window
    └── problem5_ticket_slot.py      # Binary search (insert position)
```

## Complexity summary
| Problem | Technique | Time | Extra space |
|---|---|---|---|
| 1. Class Topper Finder | Nested loop, strict `>` so ties keep the smallest row index | O(m·n) | O(1) |
| 2. Merging Two Token Queues | Two-pointer merge (join + sort: O((m+n) log(m+n))) | O(m+n) | O(m+n) output |
| 3. Most Popular Canteen Order | Hash map + second pass for tie-break (re-scan: O(n²)) | O(n) | O(k) distinct items |
| 4. Hot Weather Alert Windows | Sliding window, `sum >= k·threshold` (recompute: O(n·k)) | O(n) | O(1) |
| 5. Ticket Price Slot Finder | Binary search, return `low` (linear scan: O(n)) | O(log n) | O(1) |

## Run the tests
Each file has built-in assertions (Python 3):
```bash
cd feature_session_10/codingproblems
for f in problem*.py; do python3 "$f"; done
```
