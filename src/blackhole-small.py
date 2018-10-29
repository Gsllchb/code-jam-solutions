"""https://code.google.com/codejam/contest/12234486/dashboard#s=p2&a=2"""
import sys


# INPUT = "B-large-practice.in"
INPUT = "C-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        X1, Y1, Z1 = (int(s) for s in input().split())
        X2, Y2, Z2 = (int(s) for s in input().split())
        X3, Y3, Z3 = (int(s) for s in input().split())
        print("Case #{}: {}".format(i, solve(X1, Y1, Z1, X2, Y2, Z2, X3, Y3, Z3)))


def solve(X1, Y1, Z1, X2, Y2, Z2, X3, Y3, Z3):
    return (max(X1, X2, X3) - min(X1, X2, X3)) / 3 / 2


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
