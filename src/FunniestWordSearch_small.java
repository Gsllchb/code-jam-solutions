// https://code.google.com/codejam/contest/6364486/dashboard#s=p2&a=1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class FunniestWordSearch {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            int R = in.nextInt();
            int C = in.nextInt();
            int W = in.nextInt();

            String[] grid = new String[R];
            for (int j = 0; j < R; ++j) {
                grid[j] = in.next();
            }

            boolean[] dict = new boolean[26];
            for (int j = 0; j < W; ++j) {
                dict[in.next().charAt(0) - 'A'] = true;
            }

            int[][] preCountMap = preCount(grid, dict);
            int count = 0;
            double maxFunValue = 0;
            int maxNumerator = 0;
            int maxDenominator = 1;
            for (int y1 = -1; y1 < R - 1; ++y1) {
                for (int y2 = y1 + 1; y2 < R; ++y2) {
                    for (int x1 = -1; x1 < C - 1; ++x1) {
                        for (int x2 = x1 + 1; x2 < C; ++x2) {
                            int numerator = 4 * (preCountMap[y2][x2]
                                    - (x1 >= 0 ? preCountMap[y2][x1] : 0)
                                    - (y1 >= 0 ? preCountMap[y1][x2] : 0)
                                    + (x1 >= 0 && y1 >= 0 ? preCountMap[y1][x1] : 0));
                            int denominator = (y2 - y1) + (x2 - x1);
                            double funValue = 1.0 * numerator / denominator;
                            if (funValue == maxFunValue) {
                                ++count;
                            } else if (funValue > maxFunValue) {
                                maxFunValue = funValue;
                                maxNumerator = numerator;
                                maxDenominator = denominator;
                                count = 1;
                            }
                        }
                    }
                }
            }
            int[] frac = {maxNumerator, maxDenominator};
            reduce(frac);
            System.out.println("Case #" + i + ": " + frac[0] + "/" + frac[1] + " " + count);
        }
    }


    private static void reduce(int[] frac) {
        assert frac.length == 2;
        for (int i = 2; i <= frac[1]; ++i) {
            if (frac[0] % i == 0 && frac[1] % i == 0) {
                frac[0] /= i;
                frac[1] /= i;
                reduce(frac);
                return;
            }
        }
    }


    private static int[][] preCount(String[] grid, boolean[] dict) {
        int R = grid.length;
        int C = grid[0].length();
        int[][] map = new int[R][C];
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                boolean inDict = dict[grid[i].charAt(j) - 'A'];
                map[i][j] = (inDict ? 1 : 0)
                        + (i - 1 >= 0 ? map[i - 1][j] : 0)
                        + (j - 1 >= 0 ? map[i][j - 1] : 0)
                        - (i - 1 >= 0 && j - 1 >= 0 ? map[i - 1][j - 1] : 0);
            }
        }
        return map;
    }
}