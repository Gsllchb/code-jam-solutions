//Round 1C 2016: B
import java.util.*;
import java.io.*;
public class Slides {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final int B = in.nextInt();
            final long M = in.nextLong();
            
            if (1L << (B - 2) < M) {
            	System.out.println("Case #" + i + ": IMPOSSIBLE");
            } else if (1L << (B - 2) == M) {
            	System.out.println("Case #" + i + ": POSSIBLE");
            	for (int j = 0; j < B; ++j) {
                	for (int k = 0; k < B; ++k) {
                		if (k > j) {
                			System.out.print('1');
                		} else {
                			System.out.print('0');
                		}
                	}
                	System.out.println();
                }
            } else {
            	System.out.println("Case #" + i + ": POSSIBLE");
    			System.out.print('0');
            	for (int j = 1; j < B - 1; ++j) {
            		if (((1L << (B - 2 - j)) & M) > 0) {
            			System.out.print('1');
            		} else {
            			System.out.print('0');
            		}
            	}
            	System.out.println('0');
            	for (int j = 1; j < B; ++j) {
                	for (int k = 0; k < B; ++k) {
                		if (k > j) {
                			System.out.print('1');
                		} else {
                			System.out.print('0');
                		}
                	}
                	System.out.println();
                }
            }
        }	
    }
}