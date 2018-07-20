//GCJ Round 1C 2016:A
import java.util.*;
import java.io.*;
public class SenateEvacuation {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final int N = in.nextInt();
            int[] p = new int[N];
            int sum = 0;
            for (int j = 0; j < N; ++j) {
            	p[j] = in.nextInt();
            	sum += p[j];
            }
            StringBuilder result = new StringBuilder();
            
            foo(p, sum, result);
            
            System.out.println("Case #" + i + ": " + result);
        }
    }
	static void foo(int[] p, final int sum, StringBuilder result) {
		boolean allOne = true;
		for (int i = 0; i < p.length; ++i) {
			if(p[i] > 1) {
				allOne = false;
				break;
			}
		}
		
		if (allOne) {
			if (p.length % 2 == 1) {
				result.append('A').append(' ');
				for (int i = 1; i < p.length; i += 2) {
					result.append((char)('A' + i)).append((char)('A' + i + 1)).append(' ');
				}
			} else {
				for (int i = 0; i < p.length; i += 2) {
					result.append((char)('A' + i)).append((char)('A' + i + 1)).append(' ');
				}
			}
			return;
		} else {
			int index0 = -1;
			int index1 = -1;
			int max = 0;
			for (int i = 0; i < p.length; ++i) {
				if (p[i] > max) {
					max = p[i];
					index0 = i;
				} else if (p[i] == max) {
					index1 = i;
				}
			}
			if (index1 != -1 && p[index1] == max) {
				--p[index0];
				--p[index1];
				result.append((char) ('A' + index0)).append((char) ('A' + index1)).append(' ');
				foo(p,sum - 2, result);
			} else {
				--p[index0];
				result.append((char) ('A' + index0)).append(' ');
				foo(p, sum - 1, result);
			}
		}
	}
}