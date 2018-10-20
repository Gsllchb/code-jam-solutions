"""https://code.google.com/codejam/contest/10284486/dashboard#s=p2&a=2"""
MAX_M = 1000


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, V1, H1, A, B, C, D, E, F, M = (int(s) for s in input().split())
        V = [V1, ]
        H = [H1, ]
        for _ in range(N - 1):
            V.append((A * V[-1] + B * H[-1] + C) % M)
            H.append((D * V[-2] + E * H[-1] + F) % M)
        print("Case #{}: {}".format(i, solve(V, H, pre_comp(V, H))))


def pre_comp(V, H):
    assert len(V) == len(H)
    matrix = [[False for j in range(MAX_M)] for i in range(MAX_M)]
    for v, h in zip(V, H):
        matrix[v][h] = True
    pre_sum = [[0 for j in range(MAX_M)] for i in range(MAX_M)]
    for i in range(MAX_M):
        for j in range(MAX_M):
            pre_sum[i][j] = (
                    matrix[i][j]
                    + (pre_sum[i - 1][j] if i - 1 >= 0 else 0)
                    + (pre_sum[i][j - 1] if j - 1 >= 0 else 0)
                    - (pre_sum[i - 1][j - 1] if i - 1 >= 0 and j - 1 >= 0 else 0)
            )
    return pre_sum


def solve(V: list, H: list, pre_sum) -> int:
    assert len(V) == len(H)
    count = 0
    for v, h in zip(V, H):
        top_left = pre_sum[v - 1][h - 1] if v - 1 >= 0 and h - 1 >= 0 else 0
        top_right = pre_sum[v - 1][-1] - pre_sum[v - 1][h] if v - 1 >= 0 else 0
        bottom_left = pre_sum[-1][h - 1] - pre_sum[v][h - 1] if h - 1 >= 0 else 0
        bottom_right = pre_sum[-1][-1] - pre_sum[v][-1] - pre_sum[-1][h] + pre_sum[v][h]
        count += top_left * bottom_right + top_right * bottom_left
    return count_all_possible(len(V)) - count


def count_all_possible(N) -> int:
    return N * (N - 1) * (N - 2) // 6


if __name__ == '__main__':
    main()
