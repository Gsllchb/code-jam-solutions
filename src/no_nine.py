"""https://code.google.com/codejam/contest/10284486/dashboard#s=p0&a=0"""


def main():
    T = int(input())
    for i in range(1, T + 1):
        F, L = (int(s) for s in input().split())
        print("Case #{}: {}".format(i, solve(F, L)))


def solve(F: int, L: int):
    return count_legal(L) - count_legal(F) + 1


def count_legal(num: int):
    count = int(str(num)[: -1] + "0", base=9) * 8 // 9
    for i in range(num - num % 10, num + 1):
        if i % 9 != 0:
            count += 1
    return count


if __name__ == '__main__':
    main()
