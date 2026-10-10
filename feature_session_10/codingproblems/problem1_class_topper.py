"""Problem 1: Class Topper Finder.

Time : O(m * n)  - every mark is added once (m students, n subjects)
Space: O(1)      - only best index/total and a running row total
"""


def findTopper(marks):
    best_idx, best_total = 0, -1
    for i, row in enumerate(marks):
        total = 0
        for score in row:
            total += score
        if total > best_total:      # strict '>' keeps the smallest row index on ties
            best_idx, best_total = i, total
    return (best_idx, best_total)


if __name__ == "__main__":
    assert findTopper([[78, 85, 90], [88, 92, 79], [65, 70, 95]]) == (1, 259)
    assert findTopper([[50, 50], [60, 40], [10, 10]]) == (0, 100)   # tie -> smallest index
    assert findTopper([[0]]) == (0, 0)
    assert findTopper([[0, 0], [0, 1]]) == (1, 1)
    print("Problem 1: all tests passed")
