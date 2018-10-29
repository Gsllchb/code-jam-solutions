"""https://code.google.com/codejam/contest/12234486/dashboard#s=p0"""
import sys
from math import inf
from functools import lru_cache


# INPUT = "A-large-practice.in"
INPUT = "A-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        S = input()
        print("Case #{}: {}".format(i, solve(S)))


def solve(S):
    return min_op(S, 0, "#")


@lru_cache(maxsize=None)
def min_op(target, start, clipboard):
    length = len(target)
    if start >= length:
        return 0
    res = inf
    res = min(res, min_op(target, start + 1, clipboard) + 1)
    end = start + len(clipboard)
    if end <= length and clipboard == target[start: end]:
        res = min(res, min_op(target, end, clipboard) + 1)
    for i in range(2, min(start, length - start) + 1):
        prefix = target[start: start + i]
        if prefix in target[: start]:
            res = min(res, min_op(target, start + i, prefix) + 2)
    return res


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()