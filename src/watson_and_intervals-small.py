"""https://code.google.com/codejam/contest/12254486/dashboard#s=p2"""
import sys


INPUT = "D-large-practice.in"
# INPUT = "D-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, L1, R1, A, B, C1, C2, M = (int(s) for s in input().split())
        x = [L1, ]
        y = [R1, ]
        for _ in range(N - 1):
            x.append((A * x[-1] + B * y[-1] + C1) % M)
            y.append((A * y[-1] + B * x[-2] + C2) % M)
        L = [min(xi, yi) for xi, yi in zip(x, y)]
        R = [max(xi, yi) for xi, yi in zip(x, y)]
        print("Case #{}: {}".format(i, solve(L, R)))


def solve(L, R):
    R = [r + 1 for r in R]
    arr = [(i, 0) for i in sorted(set(L + R))]
    for l, r in zip(L, R):
        start = search(arr, l)
        stop = search(arr, r)
        for i in range(start, stop):
            v, w = arr[i]
            arr[i] = (v, w + 1)

    assert arr[-1][1] == 0
    stat = {arr[-1][0]: 0}
    for i in range(len(arr) - 2, -1, -1):
        v, w = arr[i]
        value = (w == 1) * (arr[i + 1][0] - v) + stat[arr[i + 1][0]]
        stat[v] = value

    return total_cover(arr) - max_decreasing(stat, L, R)


def max_decreasing(stat, L, R):
    res = -1
    for l, r in zip(L, R):
        res = max(res, stat[l] - stat[r])
    return res


def total_cover(arr):
    total = 0
    for i in range(len(arr) - 1):
        if arr[i][1] > 0:
            total += arr[i + 1][0] - arr[i][0]
    return total


def search(arr, target, lo=0, hi=None):
    if hi is None:
        hi = len(arr)
    mi = (lo + hi) // 2
    v, _ = arr[mi]
    if v == target:
        return mi
    if target < v:
        return search(arr, target, lo, mi)
    else:
        return search(arr, target, mi + 1, hi)


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
