//GCJ Round 1A 2017: A
import java.util.*;
import java.io.*;
import static java.lang.System.out;
public class AlphabetCake {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final int R = in.nextInt();
            final int C = in.nextInt();
            char[][] cake = new char[R][C];
            for (int j = 0; j < R; ++j) {
            		cake[j] = in.next().toCharArray();
            }
            
            for (int j = 0; j < R; ++j) {
            	char c = '?';
            	for (int k = 0; k < C; ++k) {
            		if (cake[j][k] == '?') {
            			cake[j][k] = c;
            		} else {
            			c = cake[j][k];
            		}
            	}
            	for (int k = C - 1; k >= 0; --k) {
            		if (cake[j][k] == '?') {
            			cake[j][k] = c;
            		} else {
            			c = cake[j][k];
            		}
            	}
            }
            
            for (int j = 0; j < C; ++j) {
            	char c = '?';
            	for (int k = 0; k < R; ++k) {
            		if (cake[k][j] == '?') {
            			cake[k][j] = c;
            		} else {
            			c = cake[k][j];
            		}
            	}
            	for (int k = R - 1; k >= 0; --k) {
            		if (cake[k][j] == '?') {
            			cake[k][j] = c;
            		} else {
            			c = cake[k][j];
            		}
            	}
            }
            
            out.println("Case #" + i + ": ");
            for (int j = 0; j < R; ++j) {
            	for (int k = 0; k < C; ++k) {
            		out.print(cake[j][k]);
            	}
            	out.println();
            }
        }
    }
}