"""https://code.google.com/codejam/contest/5374486/dashboard#s=p2&a=2"""
import sys
from heapq import heappush, heappop
from math import inf


# INPUT = "C-large-practice.in"
# INPUT = "C-small-practice.in"
INPUT = None


OBSTACLE = -100000


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, M, E, Sr, Sc, Tr, Tc = [int(s) for s in input().split()]
        matrix = []
        for _ in range(N):
            matrix.append([int(s) for s in input().split()])
        print("Case #{}: {}".format(i, solve(matrix, E, Sr, Sc, Tr, Tc)))


def solve(matrix, E, Sr, Sc, Tr, Tc) -> int:
    Sr -= 1
    Sc -= 1
    Tr -= 1
    Tc -= 1
    N = len(matrix)
    M = len(matrix[0])
    distances = [[inf for j in range(M)] for i in range(N)]
    distances[Sr][Sc] = 0
    start = Sr, Sc
    stop = Tr, Tc
    heap = [(0, start, {start, })]
    while heap:
        distance, current, visited = heappop(heap)
        if E - distance < 0:
            continue
        if matrix[current[0]][current[1]] == OBSTACLE:
            continue
        if current == stop:
            return max(E - distance, -1)
        left = (current[0], current[1] - 1)
        right = (current[0], current[1] + 1)
        top = (current[0] - 1, current[1])
        bottom = (current[0] + 1, current[1])
        if left[1] >= 0 and left not in visited:
            dist = distance + (-matrix[left[0]][left[1]])
            if dist < distances[left[0]][left[1]]:
                distances[left[0]][left[1]] = dist
                heappush(heap, (dist, left, visited | {left, }))
        if right[1] < M and right not in visited:
            dist = distance + (-matrix[right[0]][right[1]])
            if dist < distances[right[0]][right[1]]:
                distances[right[0]][right[1]] = dist
                heappush(heap, (dist, right, visited | {right, }))
        if top[0] >= 0 and top not in visited:
            dist = distance + (-matrix[top[0]][top[1]])
            if dist < distances[top[0]][top[1]]:
                distances[top[0]][top[1]] = dist
                heappush(heap, (dist, top, visited | {top, }))
        if bottom[0] < N and bottom not in visited:
            dist = distance + (-matrix[bottom[0]][bottom[1]])
            if dist < distances[bottom[0]][bottom[1]]:
                distances[bottom[0]][bottom[1]] = dist
                heappush(heap, (dist, bottom, visited | {bottom, }))
    return -1


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
