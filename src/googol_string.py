# https://code.google.com/codejam/contest/4374486/dashboard#s=p1
import math

MAX_LENGTH = 2 ** 60 - 1  # just greater than 10 ** 18


def main():
    t = int(input())
    for i in range(1, t + 1):
        k = int(input()) - 1  # change to starting from 0
        print("Case #{}: {}".format(i, 1 if find(MAX_LENGTH, k) else 0))


def find(length, k) -> bool:
    if k > length // 2:
        return not find(length // 2, length - k - 1)
    elif k < length // 2:
        return find(length // 2, k)
    else:
        return False


if __name__ == '__main__':
    main()
