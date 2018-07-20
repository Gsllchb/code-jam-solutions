//GCJ Round 1B 2016:A
import java.util.*;
import java.io.*;
public class GettingTheDigits {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final String S = in.next();
            
            int Z = 0;
            int N = 0;
            int W = 0;
            int H = 0;
            int F = 0;
            int U = 0;
            int I = 0;
            int V = 0;
            int X = 0;
            int G = 0;
            //The number of others letters actually don't need.
            
            for (int j = 0; j < S.length(); ++j) {
            	switch (S.charAt(j)) {
            	case 'Z':
            		++Z;
            		break;
            	case 'N':
            		++N;
            		break;
            	case 'W':
            		++W;
            		break;
            	case 'H':
            		++H;
            		break;
            	case 'F':
            		++F;
            		break;
            	case 'U':
            		++U;
            		break;
            	case 'I':
            		++I;
            		break;
            	case 'V':
            		++V;
            		break;
            	case 'X':
            		++X;
            		break;
            	case 'G':
            		++G;
            		break;   	
            	}
            }
            
            int[] x = new int[10];
            x[0] = Z;
            x[2] = W;
            x[4] = U;
            x[5] = F - x[4];
            x[7] = V - x[5];
            x[6] = X;
            x[8] = G;
            x[3] = H - x[8];
            x[9] = I - x[5] - x[6] - x[8];
            x[1] = N - x[7] - 2 * x[9];
            
            System.out.print("Case #" + i + ": " );
            for (int j = 0; j<10; ++j) {
            	for (int k = 0; k < x[j]; ++k) {
            		System.out.print(j);
            	}
            }
            System.out.println();
        }
    }
}