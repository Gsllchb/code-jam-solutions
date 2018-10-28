"""https://code.google.com/codejam/contest/7254486/dashboard#s=p3"""
import sys
from functools import lru_cache
from math import sqrt, inf

from bigstack import bigstack  # https://pypi.org/project/bigstack/

INPUT = "D-large-practice.in"
# INPUT = "D-small-practice.in"
# INPUT = None


@bigstack
def main():
    T = int(input())
    for i in range(1, T + 1):
        N = int(input())
        print("Case #{}: {}".format(i, solve(N)))


@lru_cache(maxsize=None)
def solve(N):
    if N == 0:
        return 0
    res = inf
    for i in range(1, int(sqrt(N)) + 1):
        res = min(res, solve(N - i * i))
    return res + 1


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
