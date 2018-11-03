"""https://code.google.com/codejam/contest/4344486/dashboard#s=p1"""
import sys


INPUT = "B-large-practice.in"
# INPUT = "B-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N = int(input())
        mat = []
        for _ in range(N):
            mat.append([s == 'X' for s in input()])
        print("Case #{}: {}".format(i, solve(mat)))


def solve(mat):
    length = len(mat)
    col_masker = [False for _ in range(length)]
    row_masker = [False for _ in range(length)]
    for y in range(length):
        if row_masker[y]:
            continue
        for x in range(length):
            if not mat[y][x]:
                continue
            if col_masker[x]:
                continue
            res = form_rect(mat, x, y)
            if res == None:
                return "IMPOSSIBLE"
            row, col = res
            if row_masker[row]:
                return "IMPOSSIBLE"
            if col_masker[col]:
                return "IMPOSSIBLE"
            row_masker[row] = True
            col_masker[col] = True
            row_masker[y] = True
            col_masker[x] = True
            break
    return "POSSIBLE"


def form_rect(mat, x, y):
    length = len(mat)
    row_count = count_row(mat, y)
    col_count = count_col(mat, x)
    if row_count == 1 and col_count == 1:
        return y, x
    if not (row_count == 2 and col_count == 2):
        return None

    row = None
    col = None
    for i in range(length):
        if i == x:
            continue
        if mat[y][i]:
            col = i
    for i in range(length):
        if i == y:
            continue
        if mat[i][x]:
            row = i
    if not mat[row][col]:
        return None
    if count_col(mat, col) == 2 and count_row(mat, row) == 2:
        return row, col
    return None


def count_row(mat, y):
    res = 0
    for i in range(len(mat)):
        if mat[y][i]:
            res += 1
    return res


def count_col(mat, x):
    res = 0
    for i in range(len(mat)):
        if mat[i][x]:
            res += 1
    return res


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
