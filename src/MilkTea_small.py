# coding: utf-8
"""https://code.google.com/codejam/contest/4394486/dashboard#s=p1"""

def main():
    t = int(input())
    for i in range(1, t + 1):
        n, m, p = [int(s) for s in input().split(" ")]
        preference = []
        for j in range(n):
            preference.append(int(input(), 2))
        forbidden = set()
        for j in range(m):
            forbidden.add(int(input(), 2))
        y = n * p
        for j in range(2 ** p):
            if j in forbidden:
                continue
            complain = count_complain(preference, j, p)
            if complain < y:
                y = complain
        print("Case #{}: {}".format(i, y))


def histogram(preference: list, p: int) -> list:
    res = [0 for _ in range(p)]
    for i in preference:
        for j in range(p):
            res[j] += i & (1 << j)
    return res


def count_complain(preference: list, milk_tea: int, p: int) -> int:
    complain = 0
    for i in preference:
        for j in range(p):
            if (i & (1 << j)) ^ (milk_tea & (1 << j)):
                complain += 1
    return complain


if __name__ == '__main__':
    main()
