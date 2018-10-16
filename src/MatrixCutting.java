// https://code.google.com/codejam/contest/3254486/dashboard#s=p2&a=2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class MatrixCutting {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            int N = in.nextInt();
            int M = in.nextInt();
            int[][] matrix = new int[N][M];
            for (int j = 0; j < N; ++j) {
                for (int k = 0; k < M; ++k) {
                    matrix[j][k] = in.nextInt();
                }
            }
            System.out.println("Case #" + i + ": " + solve(matrix));
        }

    }

    private static int solve(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[][][][][] cache = new int[M][M + 1][N][N + 1][2];
        return cut(matrix, 0, matrix[0].length, 0, matrix.length, cache)[0];
    }

    private static int[] cut(int[][] matrix, int left, int right, int top, int bottom, int[][][][][] cache) {
        if (cache[left][right][top][bottom][1] > 0) {
            return cache[left][right][top][bottom];
        }

        if (left + 1 == right && top + 1 == bottom) {
            int[] res = {0, matrix[top][left]};
            cache[left][right][top][bottom] = res;
            return res;
        }
        int maxTotal = 0;
        int overallMin = -1;
        for (int i = left + 1; i < right; ++i) {
            int[] leftRes = cut(matrix, left, i, top, bottom, cache);
            int[] rightRes = cut(matrix, i, right, top, bottom, cache);
            overallMin = leftRes[1] < rightRes[1] ? leftRes[1] : rightRes[1];
            int total = overallMin + leftRes[0] + rightRes[0];
            maxTotal = maxTotal > total ? maxTotal : total;
        }
        for (int i = top + 1; i < bottom; ++i) {
            int[] topRes = cut(matrix, left, right, top, i, cache);
            int[] bottomRes = cut(matrix, left, right, i, bottom, cache);
            overallMin = topRes[1] < bottomRes[1] ? topRes[1] : bottomRes[1];
            int total = overallMin + topRes[0] + bottomRes[0];
            maxTotal = maxTotal > total ? maxTotal : total;
        }
        int[] res = {maxTotal, overallMin};
        cache[left][right][top][bottom] = res;
        return res;
    }

}