"""https://code.google.com/codejam/contest/12254486/dashboard#s=p0"""
import sys


INPUT = "A-large-practice.in"
# INPUT = "A-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        S = input()
        I, J = (int(s) for s in input().split())
        print("Case #{}: {}".format(i, solve(S, I, J)))


def solve(S, I, J):
    return count_blue(S, J) - count_blue(S, I - 1)


def count_blue(S, end):
    div, mod = divmod(end, len(S))
    return div * sum(s == 'B' for s in S) + sum(s == 'B' for s in S[:mod])


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
