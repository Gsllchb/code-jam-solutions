// https://code.google.com/codejam/contest/7254486/dashboard#s=p3
import java.io.*;
import java.util.Scanner;


public class Solution {
    private final static int MAX_N = 10000;
    private static int[] cache = new int[MAX_N + 1];

    static {
        for (int i = 0; i < cache.length; ++i) {
            cache[i] = -1;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader("./D-large-practice.in");
//        Reader reader = new FileReader("./D-small-practice.in");
//        Reader reader = new InputStreamReader(System.in);
        Scanner in = new Scanner(new BufferedReader(reader));

        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            int N = in.nextInt();
            System.out.println("Case #" + i +": " + solve(N));
        }

    }

    private static int solve(int N) {
        if (cache[N] != -1) {
            return cache[N];
        }
        if (N == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= (int) Math.sqrt(N); ++i) {
            int temp = solve(N - i * i);
            res = temp < res ? temp : res;
        }
        cache[N] = res + 1;
        return res + 1;
    }

}