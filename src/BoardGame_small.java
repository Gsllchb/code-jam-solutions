// https://code.google.com/codejam/contest/4394486/dashboard#s=p2&a=2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class BoardGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            int N = in.nextInt();
            int[] A = new int[3 * N];
            int[] B = new int[3 * N];
            for (int j = 0; j < A.length; ++j) {
                A[j] = in.nextInt();
            }
            for (int j = 0; j < B.length; ++j) {
                B[j] = in.nextInt();
            }
            ArrayList<int[]> allGroupA = findAllGroup(A);
            ArrayList<int[]> allGroupB = findAllGroup(B);
            int maxNumWin = Integer.MIN_VALUE;
            for (int[] groupA: allGroupA) {
                int numWin = 0;
                for (int[] groupB: allGroupB) {
                    if (win(groupA, groupB)) {
                        ++numWin;
                    }
                }
                if (numWin > maxNumWin) {
                    maxNumWin = numWin;
                }
            }
            System.out.println("Case #" + i + ": " + (1.0 * maxNumWin / allGroupB.size()));
        }
    }

    private static ArrayList<int[]> findAllGroup(int[] cards) {
        ArrayList<int[]> res = new ArrayList<>();
        int sum = sum(cards);
        for (int b11 = 0; b11 < cards.length - 2; ++b11) {
            for (int b12 = b11 + 1; b12 < cards.length - 1; ++b12) {
                for (int b13 = b12 + 1; b13 < cards.length; ++b13) {
                    int sum1 = cards[b11] + cards[b12] + cards[b13];

                    for (int b21 = 0; b21 < cards.length - 2; ++b21) {
                        if (b21 == b11 || b21 == b12 || b21 == b13) {
                            continue;
                        }
                        for (int b22 = b21 + 1; b22 < cards.length - 1; ++b22) {
                            if (b22 == b11 || b22 == b12 || b22 == b13) {
                                continue;
                            }
                            for (int b23 = b22 + 1; b23 < cards.length; ++b23) {
                                if (b23 == b11 || b23 == b12 || b23 == b13) {
                                    continue;
                                }
                                int sum2 = cards[b21] + cards[b22] + cards[b23];
                                int[] group = {sum1, sum2, sum - sum1 - sum2};
                                res.add(group);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }


    private static int sum(int[] arr) {
        int res = 0;
        for (int i: arr) {
            res += i;
        }
        return res;
    }

    private static void select(ArrayList<int[]> res, int[] cards, int num) {
        for (int i = 0; i < cards.length; ++i) {
            if (cards[i] == -1) {
                continue;
            }

        }
    }

    private static boolean win(int[] groupA, int[] groupB) {
        assert groupA.length == 3;
        assert groupB.length == 3;
        int count = 0;
        for (int i = 0; i < 3; ++i) {
            if (groupA[i] > groupB[i]) {
                ++count;
            }
        }
        return count >= 2;
    }
}