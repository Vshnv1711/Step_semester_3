"""Problem 4: Hot Weather Alert Windows.

Recalculating every block's sum from scratch:
    Time : O((n - k + 1) * k) = O(n*k)   Space: O(1)
Sliding window (this solution):
    Time : O(n)   Space: O(1)

Compare window_sum >= k * threshold instead of dividing, to avoid decimals.
"""


def countAlerts_bruteforce(readings, k, threshold):
    count = 0
    for i in range(len(readings) - k + 1):
        if sum(readings[i:i + k]) >= k * threshold:
            count += 1
    return count


def countAlerts(readings, k, threshold):
    need = k * threshold
    window = sum(readings[:k])
    count = 1 if window >= need else 0
    for i in range(k, len(readings)):
        window += readings[i] - readings[i - k]   # add new reading, drop the one that left
        if window >= need:
            count += 1
    return count


if __name__ == "__main__":
    for f in (countAlerts_bruteforce, countAlerts):
        assert f([2, 2, 2, 2, 5, 5, 5, 8], 3, 4) == 3
        assert f([5], 1, 5) == 1
        assert f([5], 1, 6) == 0
        assert f([1, 2, 3], 3, 2) == 1     # average exactly equals threshold
        assert f([0, 0, 0], 2, 0) == 2
    import random
    for _ in range(200):
        r = [random.randint(0, 20) for _ in range(random.randint(1, 15))]
        k = random.randint(1, len(r)); t = random.randint(0, 20)
        assert countAlerts(r, k, t) == countAlerts_bruteforce(r, k, t)
    print("Problem 4: all tests passed")
