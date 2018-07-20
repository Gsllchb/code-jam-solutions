//GCJ Qualification Round 2016:A
import java.util.*;
import java.io.*;
public class CountingSheep {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        
        for (int i = 1; i <= T; ++i) {
            final int N = in.nextInt();
            
            if (N == 0) {
            	System.out.println("Case #" + i + ": " + "INSOMNIA");
            	continue;
            }
            
            boolean[] digits=new boolean[10];
            int n = 0;
            boolean hasAllDigits = false;
            do {
            	hasAllDigits = true;
            	n += N;
            	setDigits(n, digits);
            	for (int j = 0; j < 10; ++j){
            		if (digits[j] == false) {
            			hasAllDigits = false;
            			break;
            		}
            	}
            } while (!hasAllDigits);
            System.out.println("Case #" + i + ": " + n);
        }
        
    }
	static void setDigits(int n, boolean[] digits){
		do {
			digits[n % 10] = true;
		} while((n /= 10) > 0);
	}
}