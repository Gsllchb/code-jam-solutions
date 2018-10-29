"""https://code.google.com/codejam/contest/7254486/dashboard#s=p2&a=2"""
import sys
from heapq import heappush, heappop
from math import inf
import numpy as np


# INPUT = "C-large-practice.in"
INPUT = "C-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, M, P = (int(s) for s in input().split())
        mat = [[-1 for j in range(N)] for k in range(N)]
        for _ in range(M):
            U, V, D = (int(s) for s in input().split())
            mat[U - 1][V - 1] = D
            mat[V - 1][U - 1] = D
        print("Case #{}: {}".format(i, solve(mat, P)))


def solve(mat, P):
    N = len(mat)
    matrix = np.ones(shape=(N + 1, N + 1), dtype=np.float64)
    matrix[N, :] *= 0
    for i in range(N + 1):
        matrix[i, i] = 0
    matrix[N, N] = N - 1
    for i in range(N):
        matrix[i, -1] = sum(get_distances(i, mat))
    matrix *= 1 / (N - 1)
    V = np.zeros(shape=(N + 1, 1), dtype=np.float64)
    V[N, 0] = 1
    return (np.linalg.matrix_power(matrix, P) @ V)[0, 0]


def get_distances(start, mat):
    N = len(mat)
    heap = []
    heappush(heap, (0, start, {start, }))
    distances = [inf for _ in range(N)]
    distances[start] = 0
    while heap:
        dist, current, visited = heappop(heap)
        for loc in range(N):
            if mat[current][loc] == -1:
                continue
            if loc in visited:
                continue
            distance = dist + mat[current][loc]
            if distance < distances[loc]:
                distances[loc] = distance
                heappush(heap, (distance, loc, visited | {loc,}))
    return distances


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()