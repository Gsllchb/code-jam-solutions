"""https://code.google.com/codejam/contest/9234486/dashboard#s=p2&a=2"""


def main():
    T = int(input())
    for i in range(1, T + 1):
        L = int(input())
        dictionary = input().split(" ")
        line = input().split(" ")
        S1, S2 = line[: 2]
        N, A, B, C, D = (int(s) for s in line[2:])
        S = generate_s(S1, S2, N, A, B, C, D)
        print("Case #{}: {}".format(i, solve(S, dictionary)))


def generate_s(S1, S2, N, A, B, C, D) -> str:
    x = [ord(S1), ord(S2)]
    for _ in range(N - 2):
        x.append((A * x[-1] + B * x[-2] + C) % D)
    s = [S1, S2]
    for xi in x[2:]:
        s.append(chr(97 + (xi % 26)))
    return ''.join(s)


def solve(S: str, dictionary: list):
    count = 0
    for word in dictionary:
        if exist(S, word):
            count += 1
    return count


def exist(S: str, word: str) -> bool:
    length = len(word)
    first = word[0]
    last = word[-1]
    word_hist = [0 for _ in range(26)]
    for char in word:
        word_hist[ord(char) - ord('a')] += 1

    hist = [0 for _ in range(26)]
    for char in S[:length]:
        hist[ord(char) - ord('a')] += 1

    for i in range(length - 1, len(S)):
        if first == S[i + 1 - length] and last == S[i] and word_hist == hist:
            return True
        if i + 1 == len(S):
            break
        hist[ord(S[i + 1]) - ord('a')] += 1
        hist[ord(S[i + 1 - length]) - ord('a')] -= 1
    return False


if __name__ == '__main__':
    main()
