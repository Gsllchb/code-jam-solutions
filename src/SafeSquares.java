// https://code.google.com/codejam/contest/12254486/dashboard#s=p1
import java.io.*;
import java.util.Scanner;


public class Solution {

    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader("./B-large-practice.in");
//        Reader reader = new FileReader("./B-small-practice.in");
//        Reader reader = new InputStreamReader(System.in);
        Scanner in = new Scanner(new BufferedReader(reader));

        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            int R = in.nextInt();
            int C = in.nextInt();
            int K = in.nextInt();
            int[][] mat = new int[R][C];
            for (int j = 0; j < K; ++j) {
                int Ri = in.nextInt();
                int Ci = in.nextInt();
                mat[Ri][Ci]= 1;
            }
            System.out.println("Case #" + i +": " + solve(mat));
        }

    }

    private static long solve(int[][] mat) {
        int R = mat.length;
        int C = mat[0].length;
        preSum(mat);
        long res = R * C - mat[R - 1][C - 1];
        for (int size = 2; size <= (R > C ? C : R); ++size) {
            long total = 0;
            for (int y = 0; y <= R - size; ++y) {
                for (int x = 0; x <= C - size; ++x) {
                    if (isSafe(mat, y, x, size)) {
                        ++total;
                    }
                }
            }
            if (total == 0) {
                break;
            }
            res += total;
        }
        return res;
    }

    private static void preSum(int[][] mat) {
        for (int y = 0; y < mat.length; ++y) {
            for (int x = 0; x < mat[0].length; ++x) {
                mat[y][x] += (y - 1 >= 0 ? mat[y - 1][x] : 0)
                        + (x - 1 >= 0 ? mat[y][x - 1] : 0)
                        - (y - 1>= 0 && x - 1 >= 0 ? mat[y - 1][x - 1] : 0);
            }
        }
    }


    private static boolean isSafe(int[][] mat, int y, int x, int size) {
        --x;
        --y;
        int xx = x + size;
        int yy = y + size;
        return mat[yy][xx]
                - (x >= 0 ? mat[yy][x] : 0)
                - (y >= 0 ? mat[y][xx] : 0)
                + (x >= 0 && y >= 0 ? mat[y][x] : 0)
                == 0;
    }
}