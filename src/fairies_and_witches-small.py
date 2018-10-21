"""https://code.google.com/codejam/contest/4384486/dashboard#s=p1"""
import sys

INPUT = "B-large-practice.in"
# INPUT = "B-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N = int(input())
        matrix = []
        for j in range(N):
            line = [int(s) for s in input().split()]
            matrix.append(line)
        print("Case #{}: {}".format(i, solve(matrix)))


def solve(matrix) -> int:
    res = set()
    find_all_sides(
        res,
        matrix,
        frozenset(),
        0,
        set(),
        set()
    )
    return len(res)


def find_all_sides(res: set, matrix, sides: frozenset, row: int, row_mask, col_mask):
    length = len(matrix)
    for i in range(row, length):
        if i in row_mask:
            continue
        for j in range(i, length):
            if j in col_mask:
                continue
            if matrix[i][j] == 0:
                continue
            find_all_sides(
                res,
                matrix,
                sides | frozenset([(i, j, matrix[i][j]), ]),
                row + 1,
                row_mask | {i, j},
                col_mask | {i, j}
            )
    if is_convex(sides):
        res.add(sides)


def is_convex(sides: frozenset) -> bool:
    if len(sides) < 3:
        return False
    lengths = [side[2] for side in sides]
    total = sum(lengths)
    for length in lengths:
        if length >= total - length:
            return False
    return True


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
