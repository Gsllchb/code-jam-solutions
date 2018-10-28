"""https://code.google.com/codejam/contest/7254486/dashboard#s=p0"""
import sys
from array import array


INPUT = "A-large-practice.in"
# INPUT = "A-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N = int(input())
        A = array("I", (int(s) for s in input().split()))
        print("Case #{}: {}".format(i, solve(A)))


def solve(A) -> str:
    arr = array("I", sorted(A))
    left = 0
    right = len(arr)
    while A:
        mi = (len(A) - 1) // 2
        if A[mi] == arr[left]:
            left += 1
            del A[mi]
        elif A[mi] == arr[right - 1]:
            right -= 1
            del A[mi]
        else:
            return "NO"
    return "YES"


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
