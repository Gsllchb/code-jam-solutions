"""https://code.google.com/codejam/contest/3324486/dashboard#s=p0&a=1"""
import sys
import itertools

INPUT = "A-large.in"
# INPUT = "A-small-attempt0.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, P = (int(s) for s in input().split())
        forbidden = []
        for _ in range(P):
            forbidden.append(input())
        print("Case #{}: {}".format(i, solve(N, forbidden)))


def solve(N, forbidden):
    total = 2 ** N
    forbidden = clean(forbidden)
    remain = 0
    for prefix in forbidden:
        remain += 2 ** (N - len(prefix))
    return total - remain


def clean(forbidden):
    res = []
    for prefix in forbidden:
        has_prefix = False
        for p in forbidden:
            if len(p) >= len(prefix):
                continue
            if all(s1 == s2 for s1, s2 in zip(itertools.islice(prefix, len(p)), p)):
                has_prefix = True
                break
        if not has_prefix:
            res.append(prefix)
    return res


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
