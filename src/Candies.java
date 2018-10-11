// https://code.google.com/codejam/contest/6364486/dashboard#s=p0&a=3
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;


public class Candies {


    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int o = in.nextInt();
            long d = in.nextLong();

            int x1 = in.nextInt();
            int x2 = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int m = in.nextInt();
            int l = in.nextInt();

            long[] s = generateS(n, x1, x2, a, b, c, m, l);
            long[] prefixSum = getPrefixSum(s);

            HashMap<Long, Integer> counter = new HashMap<>();
            TreeSet<Long> sums = new TreeSet<>();
            long maxSweetness = Long.MIN_VALUE;
            int left = 0;
            int right = 0;
            int numOdd = 0;

            while (left < n) {
                while (right < n) {
                    if (!isOdd(s[right])) {
                        sums.add(prefixSum[right]);
                        counter.put(prefixSum[right], counter.getOrDefault(prefixSum[right], 0) + 1);
                        ++right;
                    } else {
                        if (numOdd + 1 <= o) {
                            sums.add(prefixSum[right]);
                            counter.put(prefixSum[right], counter.getOrDefault(prefixSum[right], 0) + 1);
                            ++right;
                            ++numOdd;
                        } else {
                            break;
                        }
                    }
                }
                if (left == right) {
                    break;
                }
                Long temp = sums.floor(d + (left - 1 < 0 ? 0 : prefixSum[left - 1]));
                if (temp != null) {
                    long sum = temp - (left - 1 < 0 ? 0 : prefixSum[left - 1]);
                    if (sum > maxSweetness) {
                        maxSweetness = sum;
                    }
                }
                if (isOdd(s[left])) {
                    --numOdd;
                }
                int count = counter.get(prefixSum[left]) - 1;
                assert count >= 0;
                counter.put(prefixSum[left], count);
                if (count == 0) {
                    sums.remove(prefixSum[left]);
                }
                ++left;
            }

            System.out.println("Case #" + i + ": " + (maxSweetness == Long.MIN_VALUE ? "IMPOSSIBLE" : maxSweetness));
        }
    }


    private static boolean isOdd(long num) {
        return Math.abs(num) % 2 == 1;
    }


    private static long[] generateS(int n, int x1, int x2, int a, int b, int c, int m, int l) {
        long[] x = new long[n];
        x[0] = x1;
        x[1] = x2;

        for (int i = 2; i < n; ++i) {
            x[i] = ((a * x[i - 1]) % m + (b * x[i - 2]) % m + c) % m;
        }
        long[] s = x;
        for (int i = 0; i < n; ++i) {
            s[i] += l;
        }
        return s;
    }


    private static long[] getPrefixSum(long[] s) {
        long[] prefixSum = new long[s.length];
        prefixSum[0] = s[0];
        for (int j = 1; j < s.length; ++j) {
            prefixSum[j] = prefixSum[j - 1] + s[j];
        }
        return prefixSum;
    }

}
