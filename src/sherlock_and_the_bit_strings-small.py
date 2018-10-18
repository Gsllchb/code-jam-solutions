"""https://code.google.com/codejam/contest/10284486/dashboard#s=p1&a=1"""


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, K, P = (int(s) for s in input().split())
        A = []
        B = []
        C = []
        for _ in range(K):
            a, b, c = (int(s) for s in input().split())
            A.append(a)
            B.append(b)
            C.append(c)
        print("Case #{}: {}".format(i, solve(N, P, A, B, C)))


def solve(N: int, P: int, A: list, B: list, C: list) -> str:
    res = [None for _ in range(N)]
    fill = list(bin(P - 1)[2:])
    fill = ["0", ] * (N - len(fill) - len(A)) + fill
    for a, c in zip(A, C):
        res[a - 1] = str(c)
    for i in range(len(res) - 1, -1, -1):
        if res[i] is not None:
            continue
        if not fill:
            break
        res[i] = fill[-1]
        fill.pop()
    return "".join(res)


if __name__ == '__main__':
    main()
