# coding: utf-8
"""https://code.google.com/codejam/contest/4394486/dashboard#s=p0"""
def main():
    t = int(input())
    for i in range(1, t + 1):
        n, k = [int(s) for s in input().split(" ")]
        a = [int(s) for s in input().split(" ")]
        a.sort(reverse=True)
        y = 0
        while a:
            while a and a[-1] == 0:
                a.pop()
            y += min(k, len(a))
            a = a[: -min(k, len(a))]
            for j in range(len(a)):
                a[j] -= 1
        print("Case #{}: {}".format(i, y))


if __name__ == '__main__':
    main()
