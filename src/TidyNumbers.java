//GCJ Qualification Round 2017: B
import java.util.*;
import java.io.*;
public class TidyNumbers {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final long N = in.nextLong();
            
            long maxTidyNum = 0;
            int index;
            int length;
            for (long j = N; j > 0; ) {
            	char[] arr = String.valueOf(j).toCharArray();
            	length = arr.length;
            	boolean isTidyNum = true;
            	for (index = 0; index < length - 1; ++index) {
            		if (arr[index] > arr[index + 1]) {
            			isTidyNum = false;
            			break;
            		}
            	}
            	if (isTidyNum) {
            		maxTidyNum = j;
            		break;
            	}
            	long temp = j % (long)Math.pow(10, length - index - 1); 
            	j -= (temp == 0 ? 1 : temp);
            }
            System.out.println("Case #" + i + ": " + maxTidyNum);
        }
    }
}