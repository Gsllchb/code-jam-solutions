"""https://code.google.com/codejam/contest/7254486/dashboard#s=p2"""
import sys
from heapq import heappush, heappop
from math import inf


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
    global expected_cache, dist_cache
    expected_cache.clear()
    dist_cache.clear()
    return expected(0, mat, P)


expected_cache = {}


def expected(start, mat, num):
    global expected_cache
    if (start, num) in expected_cache.keys():
        return expected_cache[start, num]
    if num == 0:
        expected_cache[start, num] = 0
        return 0
    distances = get_distances(start, mat)
    total = 0
    N = len(mat)
    for i in range(N):
        if i == start:
            continue
        total += distances[i] + expected(i, mat, num - 1)
    res = total / (N - 1)
    expected_cache[start, num] = res
    return res


dist_cache = {}


def get_distances(start, mat):
    global dist_cache
    if start in dist_cache.keys():
        return dist_cache[start]
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
    dist_cache[start] = distances
    return distances


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
