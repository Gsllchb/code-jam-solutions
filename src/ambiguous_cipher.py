"""https://code.google.com/codejam/contest/4344486/dashboard#s=p0"""
import sys


INPUT = "A-large-practice.in"
# INPUT = "A-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        word = input()
        print("Case #{}: {}".format(i, solve(word)))


def solve(word):
    length = len(word)
    if length % 2 == 1:
        return "AMBIGUOUS"
    word = [ord(c) - ord('A') for c in word]
    res = [-1 for _ in range(length)]
    res[1] = word[0]
    res[-2] = word[-1]
    for i in range(3, length, 2):
        s0 = res[i - 2]
        c1 = word[i - 1]
        s2 = c1 - s0 if c1 >= s0 else 26 + c1 - s0
        res[i] = s2

        s0 = res[length - i + 1]
        c1 = word[length - i]
        s2 = c1 - s0 if c1 >= s0 else 26 + c1 - s0
        res[length - i - 1] = s2
    return ''.join(map(lambda x: chr(x + ord('A')), res))


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
