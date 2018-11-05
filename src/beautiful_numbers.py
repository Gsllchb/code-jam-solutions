"""https://code.google.com/codejam/contest/12254486/dashboard#s=p2"""
import sys
import math


INPUT = "C-large-practice.in"
# INPUT = "C-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N = int(input())
        print("Case #{}: {}".format(i, solve(N)))


def solve(N):
    b = 2
    while b < N ** (1 / 3):
        n = math.log(1 - N * (1 - b), b)
        if is_integer(n):
            return b
        b += 1

    b = (math.sqrt(1 - 4 * (1 - N)) - 1) / 2
    if is_integer(b):
        return int(b)

    return N - 1


def is_integer(n):
    return math.isclose(0, math.fabs(n - round(n)), abs_tol=1e-9)


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
