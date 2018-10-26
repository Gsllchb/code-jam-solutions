"""https://code.google.com/codejam/contest/5374486/dashboard#s=p0"""
import sys
from bisect import bisect_left, bisect_right


INPUT = "A-large-practice.in"
# INPUT = "A-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N = int(input())
        A = [int(s) for s in input().split()]
        print("Case #{}: {}".format(i, solve(A)))


def solve(A) -> int:
    length = len(A)
    A.sort()
    numZero = bisect_right(A, 0)
    total = foo2(numZero) * (len(A) - numZero) + foo3(numZero)
    for i in range(numZero, length - 2):
        for j in range(i + 1, length - 1):
            product = A[i] * A[j]
            left = bisect_left(A, product, lo=j + 1)
            right = bisect_right(A, product, lo=j + 1)
            total += right - left
    return total


def foo2(n):
    return n * (n - 1) // 2


def foo3(n):
    return n * (n - 1) * (n - 2) // (2 * 3)


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
