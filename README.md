# Step_semester_3

Practice solutions and assignments for **STEP Semester 3** (CodInClub | BridgeLabz).

## Sessions
| Branch | Contents |
|---|---|
| `feature_session_5` | Session 5 work |
| `feature_session_6` | Session 6 work |
| `feature_session_7` | Session 7 work |
| [`feature_session_9`](../../tree/feature_session_9/feature_session_9) | **Category B: Data Structures** — practice sheet (coding problems, quiz, concepts) and coding assignment |

## Feature Session 9 — Category B: Data Structures
Located in [`feature_session_9/`](feature_session_9) on the `feature_session_9` branch.

- [`codingproblems/`](feature_session_9/codingproblems) — practice sheet coding problems (binary search, 2D grid, pair sum, two pointers)
- [`assignment/`](feature_session_9/assignment) — coding assignment (2D array, two-pointer merge, hashing, sliding window, binary search)
- [`quiz/`](feature_session_9/quiz) and [`concepts/`](feature_session_9/concepts) — practice sheet quiz and concept answers

### Assignment summary
| Problem | Technique | Time | Extra space |
|---|---|---|---|
| 1. Class Topper Finder | Nested loop | O(m·n) | O(1) |
| 2. Merging Two Token Queues | Two-pointer merge | O(m+n) | O(m+n) output |
| 3. Most Popular Canteen Order | Hash map | O(n) | O(k) |
| 4. Hot Weather Alert Windows | Sliding window | O(n) | O(1) |
| 5. Ticket Price Slot Finder | Binary search | O(log n) | O(1) |

See [`feature_session_9/README.md`](feature_session_9/README.md) for the full breakdown.

## Running the Python solutions
```bash
cd feature_session_9/codingproblems && for f in problem*.py; do python3 "$f"; done
cd ../assignment && for f in problem*.py; do python3 "$f"; done
```
