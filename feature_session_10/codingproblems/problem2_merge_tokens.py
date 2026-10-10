"""Problem 2: Merging Two Token Queues.

Two-pointer merge (list lengths m and n):
    Time : O(m + n)
    Space: O(m + n)  for the output list (O(1) extra beyond the output)

Join + sort alternative (counterA + counterB, then sort):
    Time : O((m + n) log(m + n))
    Space: O(m + n)
It ignores the fact that both lists are already sorted, so it does extra work.
Use '<=' when comparing so equal tokens keep A-before-B order (stable merge).
"""


def mergeTokens(counterA, counterB):
    i = j = 0
    merged = []
    while i < len(counterA) and j < len(counterB):
        if counterA[i] <= counterB[j]:
            merged.append(counterA[i])
            i += 1
        else:
            merged.append(counterB[j])
            j += 1
    merged.extend(counterA[i:])     # one of these is empty
    merged.extend(counterB[j:])
    return merged


if __name__ == "__main__":
    assert mergeTokens([3, 8, 15, 20], [5, 8, 12]) == [3, 5, 8, 8, 12, 15, 20]
    assert mergeTokens([], [4, 9]) == [4, 9]
    assert mergeTokens([4, 9], []) == [4, 9]
    assert mergeTokens([], []) == []
    assert mergeTokens([1, 1], [1]) == [1, 1, 1]
    import random
    for _ in range(200):
        a = sorted(random.randint(0, 20) for _ in range(random.randint(0, 10)))
        b = sorted(random.randint(0, 20) for _ in range(random.randint(0, 10)))
        assert mergeTokens(a, b) == sorted(a + b)
    print("Problem 2: all tests passed")
