// https://code.google.com/codejam/contest/4394486/dashboard#s=p1&a=1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class MilkTea {
    private static final int MAX_N = 100;
    private static final int MAX_P = 100;

    static class Record implements Comparable<Record>{
        int numComplaint;
        String milkTea;

        Record(int numComplaint, String milkTea) {
            this.numComplaint = numComplaint;
            this.milkTea = milkTea;
        }

        @Override
        public int compareTo(Record o) {
            return numComplaint - o.numComplaint;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            int N = in.nextInt();
            int M = in.nextInt();
            int P = in.nextInt();

            String[] preference = new String[N];
            for (int j = 0; j < N; ++j) {
                preference[j] = in.next();
            }
            String[] forbidden = new String[M];
            for (int j = 0; j < M; ++j) {
                forbidden[j] = in.next();
            }

            int[] countOne = new int[P];
            int[] countZero = new int[P];

            for (int j = 0; j < P; ++j) {
                for (int k = 0; k < N; ++k) {
                    if (preference[k].charAt(j) == '1') {
                        ++countOne[j];
                    } else {
                        ++countZero[j];
                    }
                }
            }

            Record[] candidates = new Record[2 * (M + 1)];
            candidates[0] = new Record(countOne[0], "0");
            candidates[1] = new Record(countZero[0], "1");
            for (int j = 2; j < candidates.length; ++j) {
                candidates[j] = new Record(MAX_N * MAX_P + 1, "");
            }
            Arrays.sort(candidates, Record::compareTo);

            for (int j = 1; j < P; ++j) {
                for (int k = M; k >= 0; --k) {
                    if (candidates[k] == null) {
                        continue;
                    }
                    int complaint = candidates[k].numComplaint;
                    String milkTea = candidates[k].milkTea;
                    candidates[2 * k] = new Record(complaint + countOne[j], milkTea + "0");
                    candidates[2 * k + 1] = new Record(complaint + countZero[j], milkTea + "1");
                }
                Arrays.sort(candidates, Record::compareTo);
            }

            int res = -1;
            for (Record record: candidates) {
                if (!contain(forbidden, record.milkTea)) {
                    res = record.numComplaint;
                    break;
                }
            }
            System.out.println("Case #" + i + ": " + res);
        }
    }

    private static boolean contain(String[] strs, String str) {
        for (String s: strs) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }
}