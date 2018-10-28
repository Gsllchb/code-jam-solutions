"""https://code.google.com/codejam/contest/7254486/dashboard#s=p1"""
import sys


# INPUT = "B-large-practice.in"
INPUT = "B-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        E, N = (int(s) for s in input().split())
        S = [int(s) for s in input().split()]
        print("Case #{}: {}".format(i, solve(E, S)))


def solve(E, S):
    S.sort()
    left = 0
    right = len(S)
    honor = 0
    while right > left:
        while left < right and E > S[left]:
            E -= S[left]
            left += 1
            honor += 1
        if honor == 0:
            return 0
        if right - left <= 1:
            return honor
        right -= 1
        E += S[right]
        honor -= 1


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
