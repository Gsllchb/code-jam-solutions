"""https://code.google.com/codejam/contest/4384486/dashboard#s=p0"""
from collections import deque


def main():
    T = int(input())
    for i in range(1, T + 1):
        N = int(input())
        matrix = [[False for j in range(N)] for k in range(N)]
        for j in range(N):
            x, y = (int(s) for s in input().split())
            matrix[x - 1][y - 1] = True
            matrix[y - 1][x - 1] = True
        res = solve(matrix)
        print("Case #{}: {}".format(i, " ".join(map(str, res))))


def solve(matrix) -> list:
    N = len(matrix)
    assert len(matrix[0]) == N
    circle = circle_nodes(matrix)
    res = [-1 for _ in range(N)]
    for start in range(N):
        if res[start] != -1:
            continue
        has_visited = set()
        has_visited.add(start)
        queue = deque()
        queue.append((start, [start, ]))
        while queue:
            planet, path = queue.popleft()
            if planet in circle:
                for distance, p in enumerate(reversed(path)):
                    res[p] = distance
                break
            if res[planet] != -1:
                for distance, p in enumerate(reversed(path)):
                    res[p] = distance + res[planet]
                break
            for next_planet in range(N):
                if matrix[planet][next_planet] and next_planet not in has_visited:
                    queue.append((next_planet, path + [next_planet, ]))
                    has_visited.add(next_planet)
    return res


def circle_nodes(matrix) -> set:
    for start in range(len(matrix)):
        queue = deque()
        queue.append((start, {start, }))
        while queue:
            planet, has_visited = queue.popleft()
            for next_planet in range(len(matrix)):
                if not matrix[planet][next_planet]:
                    continue
                if len(has_visited) > 2 and next_planet == start:
                    return has_visited
                if next_planet not in has_visited:
                    queue.append((next_planet, has_visited | {next_planet, }))


if __name__ == '__main__':
    main()
