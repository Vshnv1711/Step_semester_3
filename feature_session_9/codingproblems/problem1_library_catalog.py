"""Problem 1: Library Catalog Lookup (binary search).

Time : O(log n)   - search space halves every iteration
Space: O(1)       - iterative, only a few index variables
"""


def findBook(catalog, targetIsbn):
    low, high = 0, len(catalog) - 1
    while low <= high:
        mid = (low + high) // 2
        isbn, title = catalog[mid]
        if isbn == targetIsbn:
            return title
        if isbn < targetIsbn:   # fixed-length numeric strings compare correctly
            low = mid + 1
        else:
            high = mid - 1
    return "Not Found"


if __name__ == "__main__":
    catalog = [("0001112223", "Introduction to Algebra"),
               ("0002223334", "Beginning Python"),
               ("0003334445", "Classic Mythology"),
               ("0004445556", "Data and Society"),
               ("0005556667", "European History")]
    assert findBook(catalog, "0003334445") == "Classic Mythology"
    assert findBook(catalog, "0009998887") == "Not Found"
    assert findBook([], "1") == "Not Found"
    print("Problem 1: all tests passed")
