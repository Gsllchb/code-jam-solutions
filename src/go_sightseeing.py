"""https://code.google.com/codejam/contest/8284487/dashboard#s=p0"""
import sys
from math import ceil, inf, isinf

INPUT = "A-large-practice.in"
# INPUT = "A-small-practice.in"
# INPUT = None


def main():
    T = int(input())
    for i in range(1, T + 1):
        N, Ts, Tf = (int(s) for s in input().split())
        buses = []
        for _ in range(N - 1):
            buses.append(tuple(int(s) for s in input().split()))
        print("Case #{}: {}".format(i, solve(N, Ts, Tf, buses)))


def solve(N, Ts, Tf, buses):
    res = max_sightseeing(N, Ts, Tf, buses, 0, 0)
    global cache
    cache.clear()
    return "IMPOSSIBLE" if isinf(res) else res


cache = {}


def max_sightseeing(N, Ts, Tf, buses, t, city):
    global cache
    if (t, city) in cache.keys():
        return cache[t, city]
    if t > Tf:
        cache[t, city] = -inf
        return -inf
    if city == N - 1:
        cache[t, city] = 0
        return 0

    res = -inf
    next_bus_time = next_bus_t(buses[city], t)
    if next_bus_time >= t + Ts:
        next_t = next_bus_time + buses[city][2]
        res = max(res, 1 + max_sightseeing(N, Ts, Tf, buses, next_t, city + 1))
    else:
        next_t = next_bus_time + buses[city][2]
        res = max(res, max_sightseeing(N, Ts, Tf, buses, next_t, city + 1))
        next_t = next_bus_t(buses[city], t + Ts) + buses[city][2]
        res = max(res, 1 + max_sightseeing(N, Ts, Tf, buses, next_t, city + 1))

    cache[t, city] = res
    return res


def next_bus_t(bus, t):
    s, f, d = bus
    if s >= t:
        return s
    x = ceil((t - s) / f)
    return s + x * f


if __name__ == '__main__':
    if INPUT is None:
        main()
    else:
        with open(INPUT) as file:
            sys.stdin = file
            main()
