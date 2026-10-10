"""Problem 3: Pair With Target Sum (Unsorted).

Approach 1 - Brute force (check every pair)
    Time : O(n^2)   Space: O(1)
Approach 2 - Hash set of seen values (check complement)
    Time : O(n)     Space: O(n)   <- optimal in time
(Approach 3 - sort + two pointers: O(n log n) time; see problem 4.)
"""


def hasPairWithSum_bruteforce(nums, target):
    n = len(nums)
    for i in range(n):
        for j in range(i + 1, n):          # j > i => two distinct positions
            if nums[i] + nums[j] == target:
                return True
    return False


def hasPairWithSum(nums, target):
    seen = set()
    for x in nums:
        if target - x in seen:             # complement appeared earlier
            return True
        seen.add(x)                        # add AFTER check: x can't pair with itself
    return False


if __name__ == "__main__":
    for f in (hasPairWithSum_bruteforce, hasPairWithSum):
        assert f([2, 7, 11, 15], 9) is True
        assert f([3, 4, 6], 20) is False
        assert f([3], 6) is False          # single element can't pair with itself
        assert f([3, 3], 6) is True        # duplicates are distinct elements
        assert f([-4, 1, 9], 5) is True    # negatives
        assert f([], 0) is False
    print("Problem 3: all tests passed")
