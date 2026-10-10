# Step Semester 3 — Feature Session 9
**Category B: Data Structures** (CodInClub | BridgeLabz)

Contains the Category B practice sheet solutions and the Category B coding assignment.

## Structure
```
feature_session_9/
├── codingproblems/                     # Practice sheet - Part A
│   ├── problem1_library_catalog.py     # Binary search
│   ├── problem2_warehouse_grid.py      # 2D grid total + first max
│   ├── problem3_pair_sum.py            # Brute force vs hash set
│   ├── problem4_pair_sum_array.py      # Brute force vs hash set vs two pointers
│   └── problem5_container_area.py      # Brute force vs two pointers
├── assignment/                         # Coding assignment
│   ├── problem1_class_topper.py        # 2D array traversal
│   ├── problem2_merge_tokens.py        # Two-pointer merge
│   ├── problem3_popular_order.py       # Hash map counting
│   ├── problem4_alert_windows.py       # Sliding window
│   └── problem5_ticket_slot.py         # Binary search (insert position)
├── quiz/part_b_quiz_answers.md         # Practice sheet - Part B
└── concepts/part_c_concept_answers.md  # Practice sheet - Part C
```

## Practice sheet — complexity summary (`codingproblems/`)
| Problem | Approach | Time | Extra space |
|---|---|---|---|
| 1. Library Catalog | Binary search | O(log n) | O(1) |
| 2. Warehouse Grid | Single scan | O(m·n) | O(1) |
| 3. Pair Sum | Brute force / Hash set | O(n²) / O(n) | O(1) / O(n) |
| 4. Pair Sum (Array) | Brute force / Hash set / Sort + two pointers | O(n²) / O(n) / O(n log n) | O(1) / O(n) / O(n) |
| 5. Container Area | Brute force / Two pointers | O(n²) / O(n) | O(1) / O(1) |

## Coding assignment — complexity summary (`assignment/`)
| Problem | Technique | Time | Extra space |
|---|---|---|---|
| 1. Class Topper Finder | Nested loop, strict `>` for ties | O(m·n) | O(1) |
| 2. Merging Two Token Queues | Two-pointer merge (vs join + sort: O((m+n) log(m+n))) | O(m+n) | O(m+n) output |
| 3. Most Popular Canteen Order | Hash map + second pass for tie-break (vs re-scan: O(n²)) | O(n) | O(k) distinct items |
| 4. Hot Weather Alert Windows | Sliding window, `sum >= k·threshold` (vs recompute: O(n·k)) | O(n) | O(1) |
| 5. Ticket Price Slot Finder | Binary search, return `low` (vs linear scan: O(n)) | O(log n) | O(1) |

## Run the tests
Each file has built-in assertions (Python 3):
```bash
cd feature_session_9/codingproblems && for f in problem*.py; do python3 "$f"; done
cd ../assignment && for f in problem*.py; do python3 "$f"; done
```
