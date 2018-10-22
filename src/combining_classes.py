"""https://code.google.com/codejam/contest/5374486/dashboard#s=p1"""
import sys
from collections import Counter


INPUT = "B-large.in"
# INPUT = "B-small-attempt1.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, Q = [int(s) for s in input().split()]
        X1, X2, A1, B1, C1, M1 = [int(s) for s in input().split()]
        Y1, Y2, A2, B2, C2, M2 = [int(s) for s in input().split()]
        Z1, Z2, A3, B3, C3, M3 = [int(s) for s in input().split()]
        X = [X1, X2]
        Y = [Y1, Y2]
        Z = [Z1, Z2]
        for _ in range(2, N):
            X.append((A1 * X[-1] + B1 * X[-2] + C1) % M1)
            Y.append((A2 * Y[-1] + B2 * Y[-2] + C2) % M2)
        for _ in range(2, Q):
            Z.append((A3 * Z[-1] + B3 * Z[-2] + C3) % M3)
        L = [min(x, y) + 1 for x, y in zip(X, Y)][: N]
        R = [max(x, y) + 1 for x, y in zip(X, Y)][: N]
        K = [z + 1 for z in Z][: Q]
        print("Case #{}: {}".format(i, solve(L, R, K)))


def solve(L: list, R: list, K:list) -> int:
    L_R = list(set(L + R))
    L_R.sort(reverse=True)
    left_counter = Counter(L)
    right_counter = Counter(R)
    res = 0
    questions = [(i, k) for i, k in enumerate(K, start=1)]
    questions.sort(key=lambda a: a[1])
    weight = 0
    total = 0
    index = 0
    prev = L_R[0] + 1
    for i in L_R:
        prev_weight = weight
        total += (prev - i - 1) * weight
        weight += right_counter[i]
        weight -= left_counter[i]
        current = weight + left_counter[i]
        total += current
        while index < len(questions) and total >= questions[index][1]:
            if total - current < questions[index][1]:
                res += questions[index][0] * i
            else:
                res += questions[index][0] * (i + (total - current - questions[index][1]) // prev_weight + 1)
            index += 1
        if index == len(questions):
            break
        prev = i
    return res


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
