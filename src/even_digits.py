"""https://code.google.com/codejam/contest/9234486/dashboard#s=p0"""
from functools import lru_cache


def main():
    T = int(input())
    for i in range(1, T + 1):
        N = int(input())
        print("Case #{}: {}".format(i, solve(N)))


@lru_cache()
def solve(N):
    weight = leftmost_odd(N)
    if weight == -1:
        return 0
    remainder = N % (10 ** weight)
    decreasing = remainder + 1 + solve(N - remainder - 1)
    if (N // 10 ** weight) % 10 == 9:
        return decreasing
    increasing = 10 ** weight - remainder
    return min(increasing, decreasing)


def leftmost_odd(num):
    weight = -1
    i = 0
    while num > 0:
        if (num % 10) % 2 == 1:
            weight = i
        num = num // 10
        i += 1
    return weight


if __name__ == '__main__':
    main()
