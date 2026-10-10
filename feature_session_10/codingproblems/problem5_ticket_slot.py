"""Problem 5: Ticket Price Slot Finder.

Linear scan:    Time O(n)       Space O(1)
Binary search:  Time O(log n)   Space O(1)

If the price is not found, the loop ends with low == the insertion index,
because every element left of low is < newPrice and every element from low
onward is > newPrice.
"""


def findSlot_linear(prices, newPrice):
    for i, p in enumerate(prices):
        if p >= newPrice:
            return i
    return len(prices)


def findSlot(prices, newPrice):
    low, high = 0, len(prices) - 1
    while low <= high:
        mid = (low + high) // 2
        if prices[mid] == newPrice:
            return mid
        if prices[mid] < newPrice:
            low = mid + 1
        else:
            high = mid - 1
    return low


if __name__ == "__main__":
    p = [120, 150, 200, 260]
    for f in (findSlot_linear, findSlot):
        assert f(p, 150) == 1
        assert f(p, 210) == 3
        assert f(p, 300) == 4
        assert f(p, 100) == 0
        assert f([], 5) == 0
        assert f([7], 7) == 0
        assert f([7], 9) == 1
    import bisect, random
    for _ in range(200):
        a = sorted(random.sample(range(0, 50), random.randint(0, 15)))
        x = random.randint(-5, 55)
        assert findSlot(a, x) == bisect.bisect_left(a, x)
    print("Problem 5: all tests passed")
