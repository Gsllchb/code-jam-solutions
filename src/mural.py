"""https://code.google.com/codejam/contest/3324486/dashboard#s=p1&a=1"""
import sys
from itertools import accumulate

INPUT = "B-large.in"
# INPUT = "B-small-attempt1.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N = int(input())
        sections = [int(s) for s in input()]
        print("Case #{}: {}".format(i, solve(sections)))


def solve(sections):
    prefix_sum = list(accumulate(sections))
    length = (len(sections) + 1) // 2
    score = 0
    for i in range(-1, len(sections) - length):
        score = max(score, prefix_sum[i + length] - (prefix_sum[i] if i >= 0 else 0))
    return score


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
