# Part C — Concept Questions

## Question 1: Searching an unsorted vs a sorted 1D array
In an unsorted array the only option is linear search: the target could be anywhere, so in the worst case every element must be checked, giving **O(n)**. In a sorted array we can use binary search, which gives **O(log n)**. The fundamental reason is that sorted order carries information: comparing the target with the middle element tells us which half it must lie in, so half of the remaining elements can be discarded each step. In an unsorted array a comparison reveals nothing about where the other elements are, so no part of the array can be safely ignored.

## Question 2: Why 2n is O(n), not O(2n)
Big-O describes how running time **grows** as input size n becomes very large, not the exact number of operations. A constant factor such as 2 only scales the curve; it doesn't change its shape. Doubling n still roughly doubles the work whether the cost is n or 2n, so both are linear. Formally, 2n ≤ c·n for c = 2 (or any c ≥ 2) for all n ≥ 1, so 2n is in O(n). Constants also depend on hardware, language and compiler, so ignoring them lets us compare algorithms independent of machine details.

## Question 3: Traversal in linear vs non-linear structures
In a linear structure (array, linked list) elements form a single sequence: each element has at most one predecessor and one successor. There is essentially one natural traversal order, start to end, visiting each element in turn with a simple loop. In a non-linear structure (tree, graph) an element can be related to several others (a node may have multiple children). There is no single "next" element, so we must choose a strategy, such as depth-first (pre/in/post-order) or breadth-first (level-order), and typically keep track of pending nodes using recursion, a stack or a queue. For graphs we also need a visited set to avoid revisiting nodes.
