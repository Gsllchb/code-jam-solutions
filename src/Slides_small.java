//GCJ Round 1C 2016: B small
import java.util.*;
import java.io.*;
public class Slides_small {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        Random rand = new Random();
        for (int i = 1; i <= T; ++i) {
            final int B = in.nextInt();
            final long M = in.nextLong();
            
            if (Math.pow(2, B - 2) < M) {
            	System.out.println("Case #" + i + ": IMPOSSIBLE");
            	continue;
            }
            
            final double expectation = (Math.log(M) / Math.log(2) + 2) * (Math.log(M) / Math.log(2) + 1) / B / (B - 1);  
            boolean[][] matrix = new boolean[B][B];
            while (countWay(matrix, 0) != M) {
            	for (int j = 0; j < B - 1; ++j) {
            		for (int k = j + 1; k < B; ++k) {
            			matrix[j][k] = false;
            			matrix[j][k] =  rand.nextDouble() < (expectation + rand.nextDouble() * (1 - expectation)) ?
            					true : false;
            		}
            	}
            }
            
            System.out.println("Case #" + i + ": POSSIBLE");
            for (int j = 0; j < B; ++j) {
            	for (int k = 0; k < B; ++k) {
            		System.out.print(matrix[j][k] ? "1" : "0");
            	}
            	System.out.println();
            }
        }
    }
	
	static long countWay(final boolean[][] matrix, final int rank) {
		final int length = matrix[0].length;
		long result = 0;
		for (int i = rank + 1; i < length - 1; ++i) {
			if (matrix[rank][i]) {
				result += countWay(matrix, i);
			}
		}
		if (matrix[rank][length - 1]) {
			++result;
		}
		return result;
	}
}