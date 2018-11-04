"""https://code.google.com/codejam/contest/4344486/dashboard#s=p2&a=2"""
import sys
import copy
import math


# INPUT = "C-large-practice.in"
INPUT = "C-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, Q = (int(s) for s in input().split())
        friends = []
        for _ in range(N):
            friends.append([s == 'T' for s in input()])
        mine = [s == 'T' for s in input()]
        scores = [int(s) for s in input().split()]
        print("Case #{}: {}".format(i, solve(friends, mine, scores)))


def solve(friends, mine, scores):
    if len(friends) == 1:
        num_agreed = sum(m == f for m, f in zip(mine, friends[0]))
        return len(mine) - abs(scores[0] - num_agreed)
    cache = {}
    return max_score(friends, mine, scores, 0, cache)


def max_score(friends, mine, scores, start, cache):
    assert len(scores) == 2
    if (scores[0], scores[1], start) in cache.keys():
        return cache[scores[0], scores[1], start]
    if start == len(mine):
        res = 0 if all(s == 0 for s in scores) else -math.inf
        cache[scores[0], scores[1], start] = res
        return res
    if any(s < 0 for s in scores):
        cache[scores[0], scores[1], start] = -math.inf
        return -math.inf

    scores1 = copy.deepcopy(scores)
    for i, f in enumerate(friends):
        scores1[i] -= (f[start] is True)
    res1 = (mine[start] is True) + max_score(friends, mine, scores1, start + 1, cache)

    scores2 = copy.deepcopy(scores)
    for i, f in enumerate(friends):
        scores2[i] -= (f[start] is False)
    res2 = (mine[start] is False) + max_score(friends, mine, scores2, start + 1, cache)
    res = max(res1, res2)
    cache[scores[0], scores[1], start] = res
    return res


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
