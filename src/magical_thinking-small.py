"""https://code.google.com/codejam/contest/4344486/dashboard#s=p2"""
import sys
import copy

INPUT = "C-large-practice.in"
# INPUT = "C-small-practice.in"
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
    score = 0
    for ans in find_answers(friends, scores):
        score = max(score, compute_score(ans, mine))
        if score == len(mine):
            break
    return score


def find_answers(friends, scores):
    yield from enum_answers([], [0] * len(friends), friends, scores)


def enum_answers(ans, current_scores, friends, scores):
    length = len(friends[0])
    if len(ans) == length:
        yield ans
        return

    current_scores1 = copy.deepcopy(current_scores)
    for i, f in enumerate(friends):
        if f[len(ans)] is True:
            current_scores1[i] += 1

    flag = True
    for s1, s2 in zip(current_scores1, scores):
        if s1 > s2 or length - len(ans) - 1 < s2 - s1:
            flag = False
            break
    if flag:
        yield from enum_answers(ans + [True], current_scores1, friends, scores)

    current_scores2 = copy.deepcopy(current_scores)
    for i, f in enumerate(friends):
        if f[len(ans)] is False:
            current_scores2[i] += 1

    flag = True
    for s1, s2 in zip(current_scores2, scores):
        if s1 > s2 or length - len(ans) - 1 < s2 - s1:
            flag = False
            break
    if flag:
        yield from enum_answers(ans + [False], current_scores2, friends, scores)


def compute_score(ans, mine):
    return sum(s1 == s2 for s1, s2 in zip(ans, mine))


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
