"""https://code.google.com/codejam/contest/9234486/dashboard#s=p1&a=1"""
from bisect import bisect


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, K = (int(s) for s in input().split(" "))
        V = [int(s) for s in input().split(" ")]
        V.sort()
        print("Case #{}: {}".format(i, solve(V, K)))


def solve(V: list, K):
    V.sort()
    sums = pre_sum(V)
    expected_val = sum(V) / len(V)
    for i in range(K):
        partition = bisect(V, expected_val)
        left_sum = expected_val * partition
        right_sum = sums[-1] - (sums[partition - 1] if partition > 0 else 0)
        expected_val = (left_sum + right_sum) / len(V)
    return expected_val


def pre_sum(arr: list) -> list:
    res = []
    for i in arr:
        res.append(i + (res[-1] if len(res) > 0 else 0))
    return res


if __name__ == '__main__':
    main()
