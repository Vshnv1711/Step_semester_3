"""Problem 4: Pair With Target Sum (Unsorted Array).

Approach 1 - Brute force
    Time : O(n^2)        Space: O(1)
Approach 2 - Hash set (complement lookup)
    Time : O(n)          Space: O(n)
Approach 3 - Sort a copy + two pointers
    Time : O(n log n)    Space: O(n) for the copy (O(1) if sorting in place)
"""


def pair_bruteforce(nums, target):
    for i in range(len(nums)):
        for j in range(i + 1, len(nums)):
            if nums[i] + nums[j] == target:
                return True
    return False


def pair_hashset(nums, target):
    seen = set()
    for x in nums:
        if target - x in seen:
            return True
        seen.add(x)
    return False


def pair_two_pointers(nums, target):
    a = sorted(nums)
    i, j = 0, len(a) - 1
    while i < j:
        s = a[i] + a[j]
        if s == target:
            return True
        if s < target:
            i += 1
        else:
            j -= 1
    return False


if __name__ == "__main__":
    for f in (pair_bruteforce, pair_hashset, pair_two_pointers):
        assert f([2, 7, 11, 15], 9) is True
        assert f([3, 4, 6], 20) is False
        assert f([3, 3], 6) is True
        assert f([5], 10) is False
        assert f([-3, 4, 1], -2) is True
    print("Problem 4: all tests passed")
