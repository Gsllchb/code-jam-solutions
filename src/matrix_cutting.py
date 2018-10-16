"""https://code.google.com/codejam/contest/3254486/dashboard#s=p2&a=2
Note that, this program will exceed the time limit on large input. But this algorithm is
correct. In order not to time out, you can use the Java implementation of this algorithm
(MatrixCutting.java)."""
from functools import lru_cache


matrix = None


def main():
    global matrix
    T = int(input())
    for i in range(1, T + 1):
        N, M = (int(s) for s in input().split(" "))
        matrix = tuple(tuple(int(s) for s in input().split(" ")) for _ in range(N))
        print("Case #{}: {}".format(i, solve()))


def solve():
    res = cut(0, len(matrix[0]), 0, len(matrix))[0]
    cut.cache_clear()
    return res


@lru_cache(maxsize=None)
def cut(left, right, top, bottom):
    if left + 1 == right and top + 1 == bottom:
        return 0, matrix[top][left]
    max_total = 0
    overall_min = None
    for i in range(left + 1, right):
        left_total, left_min = cut(left, i, top, bottom)
        right_total, right_min = cut(i, right, top, bottom)
        overall_min = min(left_min, right_min)
        total = overall_min + left_total + right_total
        max_total = max(max_total, total)

    for i in range(top + 1, bottom):
        top_total, top_min = cut(left, right, top, i)
        bottom_total, bottom_min = cut(left, right, i, bottom)
        overall_min = min(top_min, bottom_min)
        total = overall_min + top_total + bottom_total
        max_total = max(max_total, total)

    return max_total, overall_min


if __name__ == '__main__':
    main()
