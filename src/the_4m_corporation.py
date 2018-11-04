"""https://code.google.com/codejam/contest/4344486/dashboard#s=p3&a=3"""
import sys
import math


INPUT = "D-large-practice.in"
# INPUT = "D-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        minimum, maximum, mean, median = (int(s) for s in input().split())
        print("Case #{}: {}".format(i, solve(minimum, maximum, mean, median)))


def solve(minimum, maximum, mean, median):
    if minimum > maximum:
        return "IMPOSSIBLE"
    if mean > maximum or mean < minimum:
        return "IMPOSSIBLE"
    if median > maximum or median < minimum:
        return "IMPOSSIBLE"
    res = min(
        min_odd_departments(minimum, maximum, mean, median),
        min_even_departments(minimum, maximum, mean, median)
    )
    return res if math.isfinite(res) else "IMPOSSIBLE"


def min_odd_departments(minimum, maximum, mean, median):
    if minimum == maximum == mean == median:
        return 1
    if not (minimum + median < 2 * mean < maximum + median):
        return math.inf
    pre_sum = minimum + median + maximum
    factor = 3
    while True:
        x = factor * mean - pre_sum
        lower = (minimum + median) * (factor - 3) // 2
        upper = (maximum + median) * (factor - 3) // 2
        if lower <= x <= upper:
            return factor
        factor += 2


def min_even_departments(minimum, maximum, mean, median):
    if (minimum + maximum) / 2 == mean == median:
        return 2
    if not (minimum + median < 2 * mean < maximum + median):
        return math.inf
    pre_sum = minimum + 2 * median + maximum
    factor = 4
    while True:
        x = factor * mean - pre_sum
        lower = (minimum + median) * (factor - 4) // 2
        upper = (maximum + median) * (factor - 4) // 2
        if lower <= x <= upper:
            return factor
        factor += 2


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
