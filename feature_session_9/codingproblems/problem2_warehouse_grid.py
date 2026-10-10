"""Problem 2: Warehouse Grid Summary.

Time : O(m * n)  - every cell visited once
Space: O(1)      - only total, max value and coordinates are stored
"""


def warehouseSummary(grid):
    total = 0
    max_val = -1            # items are non-negative, so -1 is a safe start
    max_coord = None
    for r, row in enumerate(grid):
        for c, val in enumerate(row):
            total += val
            if val > max_val:   # strict '>' keeps the FIRST maximum found
                max_val = val
                max_coord = (r, c)
    return (total, max_coord)


if __name__ == "__main__":
    grid = [[4, 9, 2], [7, 1, 6], [3, 12, 5]]
    assert warehouseSummary(grid) == (49, (2, 1))
    assert warehouseSummary([[5, 5], [5, 5]]) == (20, (0, 0))   # tie -> first
    assert warehouseSummary([[0]]) == (0, (0, 0))
    print("Problem 2: all tests passed")
