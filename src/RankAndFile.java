//GCJ Round 1A 2016: B
import java.util.*;
import java.io.*;
public class RankAndFile {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final int N = in.nextInt();
            int[] count = new int[2501];
            for (int j = 0; j < 2 * N - 1; ++j) {
            	for (int k = 0; k < N; ++k) {
            		++count[in.nextInt()];
            	}
            }
            int[] arr = new int[N];
            for (int j = 1, k = 0; j < count.length; ++j) {
            	if (count[j] % 2 == 1) {
            		arr[k++] = j;
            		if (k == N) {
            			break;
            		}
            	}
            }
            Arrays.sort(arr);
            System.out.print("Case #" + i + ":");
            for (int j = 0; j < arr.length; ++j) {
            	System.out.print(" " + arr[j]);
            }
            System.out.println();
        }
    }
}