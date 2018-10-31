"""https://code.google.com/codejam/contest/8284487/dashboard#s=p1&a=1
This program ran timeout in my computer. But the algorithm is fast and correct for small
inputs theoretically. Maybe just because my laptop is not **modern** enough. :)"""
import sys
from itertools import accumulate
from array import array
from heapq import heappush, heappop


# INPUT = "A-large-practice.in"
INPUT = "B-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, K, A1, B1, C, D, E1 ,E2, F = (int(s) for s in input().split())
        x = array("I")
        y = array("I")
        x.append(A1)
        y.append(B1)
        for _ in range(N - 1):
            x.append((C * x[-1] + D * y[-1] + E1) % F)
            y.append((D * x[-2] + C * y[-1] + E2) % F)
        r = array("B")
        s = array("B")
        r.append(0)
        s.append(0)
        for _ in range(N - 1):
            r.append((C * r[-1] + D * s[-1] + E1) % 2)
            s.append((D * r[-2] + C * s[-1] + E2) % 2)
        A = array("i", ((-1) ** ri * xi for ri, xi in zip(r, x)))
        B = array("i", ((-1) ** si * yi for si, yi in zip(s, y)))
        print("Case #{}: {}".format(i, solve(A, B, K)))


def solve(A, B, K):
    assert len(A) == len(B)
    length = len(A)
    pre_A = array("l", accumulate(A))
    pre_B = array("l", accumulate(B))
    heap = []
    for left in range(-1, length - 1):
        for right in range(left + 1, length):
            for top in range(-1, length - 1):
                for bottom in range(top + 1, length):
                    sum_A = pre_A[right] - (pre_A[left] if left >= 0 else 0)
                    sum_B = pre_B[bottom] - (pre_B[top] if top >= 0 else 0)
                    product = sum_A * sum_B
                    heappush(heap, product)
                    if len(heap) > K:
                        heappop(heap)
    return heappop(heap)


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
