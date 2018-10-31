"""https://code.google.com/codejam/contest/8284487/dashboard#s=p2&a=2"""
import sys
from scipy import optimize

# INPUT = "C-large-practice.in"
INPUT = "C-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, P, H = (int(s) for s in input().split())
        X = []
        Y = []
        for _ in range(N):
            x, y = (int(s) for s in input().split())
            X.append(x)
            Y.append(y)
        print("Case #{}: {}".format(i, solve(P, H, X, Y)))


def solve(P, H, X, Y):
    assert len(X) == len(Y) == 1
    lower = Y[0]
    upper = H
    while upper - lower >= 1E-4:
        mi = (lower + upper) / 2
        if is_valid(mi, P, H, X, Y):
            lower = mi
        else:
            upper = mi
    return lower


def is_valid(R, P, H, X, Y):
    a = 4 * (R - H) / P / P
    xs = X[0]
    ys = Y[0]
    f = lambda x: x - xs + a * (a * x * x - a * P * x - ys) * (2 * x - P)
    xc = optimize.brenth(f, 0, P, xtol=1e-13)
    dist = (xs - xc) ** 2 + (ys - a * xc * (xc - P)) ** 2
    return dist >= (R ** 2)


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
