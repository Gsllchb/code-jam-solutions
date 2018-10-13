// https://code.google.com/codejam/contest/3314486/dashboard#s=p0
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;


public class CommonAnagrams {
    static class Hist {
        private int[] arr;
        Hist(int[] arr) {
            assert arr.length == 26;
            this.arr = arr;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(arr);
        }

        @Override
        public boolean equals(Object obj) {
            if (! (obj instanceof Hist)) {
                return false;
            }
            return Arrays.equals(arr, ((Hist) obj).arr);
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            int L = in.nextInt();
            String A = in.next();
            String B = in.next();

            int count = 0;
            HashSet<Hist> set = preCompute(B);
            for (int j = 0; j < A.length(); ++j) {
                int[] hist = new int[26];
                for (int k = j + 1; k <= A.length(); ++k) {
                    ++hist[A.charAt(k - 1) - 'A'];
                    if (set.contains(new Hist(hist))) {
                        ++count;
                    }
                }
            }
            System.out.println("Case #" + i + ": " + count);
        }
    }

    private static HashSet<Hist> preCompute(String str) {
        HashSet<Hist> set = new HashSet<>();
        for (int i = 0; i < str.length(); ++i) {
            int[] hist = new int[26];
            for (int j = i + 1; j <= str.length(); ++j) {
                ++hist[str.charAt(j - 1) - 'A'];
                set.add(new Hist(hist.clone()));
            }
        }
        return set;
    }
}