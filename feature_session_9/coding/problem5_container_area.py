"""Problem 5: Maximize Area Between Two Boundaries (Container With Most Water).

Brute force : check all pairs   -> Time O(n^2), Space O(1)
Two pointers: Time O(n), Space O(1)

Why moving the shorter wall is safe: area = min(h[l], h[r]) * (r - l).
With the width fixed, the area is capped by the shorter wall. Moving the
TALLER pointer inward shrinks width and cannot raise the cap, so it can never
help. Moving the shorter pointer is the only move that might find a taller
wall and a larger area. Hence every pair we skip is provably not better.

For very large inputs (e.g. n = 10^6) O(n^2) needs ~10^12 operations while
O(n) needs ~10^6, so the optimal solution is the only practical one.
"""


def maxContainerArea_bruteforce(heights):
    best = 0
    n = len(heights)
    for i in range(n):
        for j in range(i + 1, n):
            best = max(best, min(heights[i], heights[j]) * (j - i))
    return best


def maxContainerArea(heights):
    l, r = 0, len(heights) - 1
    best = 0
    while l < r:
        best = max(best, min(heights[l], heights[r]) * (r - l))
        if heights[l] < heights[r]:
            l += 1
        else:
            r -= 1
    return best


if __name__ == "__main__":
    h = [1, 8, 6, 2, 5, 4, 8, 3, 7]
    for f in (maxContainerArea_bruteforce, maxContainerArea):
        assert f(h) == 49
        assert f([1, 1]) == 1
        assert f([5]) == 0
        assert f([]) == 0
        assert f([0, 0, 0]) == 0
    print("Problem 5: all tests passed")
