"""https://code.google.com/codejam/contest/3254486/dashboard#s=p1&a=1"""


def main():
    T = int(input())
    for i in range(1, T + 1):
        N = int(input())
        R = [int(s) for s in input().split(" ")]
        B = [int(s) for s in input().split(" ")]
        assert len(R) == len(B) == N
        print("Case #{}: {}".format(i, solve(R, B)))


def solve(R: list, B: list):
    assert len(R) == len(B)
    length = len(R)
    if length == 1:
        return 0
    min_total = None
    for i in range(length - 1):
        for j in range(i + 1, length):
            score = min(R[i] ^ B[j], R[j] ^ B[i])
            sub_total = min(solve(remove(R, i), remove(B, i)),
                            solve(remove(R, j), remove(B, j)))
            total = score + sub_total
            if min_total is None:
                min_total = total
            else:
                min_total = min(min_total, total)
    return min_total


def remove(arr: list, index: int):
    clone = arr[:]
    del clone[index]
    return clone


if __name__ == '__main__':
    main()
