//GCJ Qualification Round 2017: A
import java.util.*;
import java.io.*;
public class OversizedPancakeFlipper {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final String S = in.next();
            final int K = in.nextInt();
            
            int step = 0;
            char[] pancakes = S.toCharArray();
            boolean possible = true;
            for (int j = 0; j < pancakes.length; ++j) {
            	if (pancakes[j] == '-') {
            		if (j > pancakes.length - K) {
            			possible = false;
            			break;
            		}
            		++step;
            		for (int k = j; k < K + j; ++k) {
            			if (pancakes[k] == '-' ) {
            				pancakes[k] = '+';
            			} else {
            				pancakes[k] = '-';
            			}
            		}
            	}
            }
            
            System.out.println("Case #" + i + ": " + (possible ? step : "IMPOSSIBLE"));
        }
    }
}