"""Problem 3: Most Popular Canteen Order.

Re-scan counting (for each item, scan whole list again, e.g. list.count):
    Time : O(n^2)   Space: O(1)
Hash map (this solution), n orders, k distinct items:
    Time : O(n)     Space: O(k)  (<= O(n))

Tie-break: after counting, walk the original list in order and pick the first
item whose count equals the maximum -> the item that appears first wins.
"""


def mostPopular_bruteforce(orders):
    best_item, best_count = None, 0
    for item in orders:
        c = 0
        for other in orders:
            if other == item:
                c += 1
        if c > best_count:          # strict '>' => first appearance wins ties
            best_item, best_count = item, c
    return (best_item, best_count)


def mostPopular(orders):
    counts = {}
    for item in orders:
        counts[item] = counts.get(item, 0) + 1
    max_count = max(counts.values())
    for item in orders:             # original order => correct tie-break
        if counts[item] == max_count:
            return (item, max_count)


if __name__ == "__main__":
    for f in (mostPopular_bruteforce, mostPopular):
        assert f(["dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"]) == ("dosa", 3)
        assert f(["tea", "coffee", "coffee", "tea"]) == ("tea", 2)
        assert f(["tea"]) == ("tea", 1)
        assert f(["a", "b", "c"]) == ("a", 1)
    print("Problem 3: all tests passed")
