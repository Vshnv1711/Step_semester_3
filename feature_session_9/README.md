# Step Semester 3 — Feature Session 9
**Category B: Data Structures Practice** (CodInClub | BridgeLabz)

Solutions to the Category B practice sheet: coding problems, quiz and concept questions.

## Structure
```
feature_session_9/
├── codingproblems/
│   ├── problem1_library_catalog.py     # Binary search
│   ├── problem2_warehouse_grid.py      # 2D grid total + first max
│   ├── problem3_pair_sum.py            # Brute force vs hash set
│   ├── problem4_pair_sum_array.py      # Brute force vs hash set vs two pointers
│   └── problem5_container_area.py      # Brute force vs two pointers
├── quiz/part_b_quiz_answers.md
└── concepts/part_c_concept_answers.md
```

## Complexity summary
| Problem | Approach | Time | Extra space |
|---|---|---|---|
| 1. Library Catalog | Binary search | O(log n) | O(1) |
| 2. Warehouse Grid | Single scan | O(m·n) | O(1) |
| 3. Pair Sum | Brute force | O(n²) | O(1) |
| 3. Pair Sum | Hash set | O(n) | O(n) |
| 4. Pair Sum | Brute force / Hash set / Sort + two pointers | O(n²) / O(n) / O(n log n) | O(1) / O(n) / O(n) |
| 5. Container Area | Brute force | O(n²) | O(1) |
| 5. Container Area | Two pointers | O(n) | O(1) |

## Run the tests
Each file has built-in assertions (Python 3):
```bash
cd feature_session_9/codingproblems
for f in problem*.py; do python3 "$f"; done
```
