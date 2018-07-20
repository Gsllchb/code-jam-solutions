//GCJ Qualification Round 2017: D small
import java.util.*;
import java.io.*;
public class FashionShow_small {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final int N = in.nextInt();
            final int M = in.nextInt();
            char[] temp = new char[N];
            List<String> steps = new LinkedList<String>();
            int point = N + 1;
            
            int oIndex = -1;
            for (int j = 0; j < M; ++j) {
            	char model = in.next().charAt(0);
            	int r = in.nextInt();
            	int c = in.nextInt();
            	temp[c - 1] = model;
            }
            for (int j = 0; j < N; ++j) {
            	if (temp[j] == 0) {
            		steps.add("+ 1 " + (j + 1));
            	}
            	if (temp[j] == 'x') {
            		oIndex = j;
            		steps.add("o 1 " + (j + 1));
            	}
            	if (temp[j] == 'o') {
            		oIndex = j;
            	}
            }
            if (oIndex == -1) {
            	steps.remove("+ 1 1");
            	steps.add("o 1 1");
            	oIndex = 0;
            }
            
            if (N == 1) {
            	System.out.println("Case #" + i + ": " + point + " " + steps.size());
            	if (steps.size() != 0) {
            		System.out.println("o 1 1");
            	}
            	continue;
            }
            
            if (oIndex >= 2) {
            	steps.add("o " + N + " " + oIndex);
            } else if (oIndex == 0) {
            	steps.add("x " + N + " " + N);
            } else {
            	steps.add("x " + N + " " + 1);
            }
            for (int j = 1; j < N - 1; ++j) {
        		if (j != oIndex - 1) {
        			steps.add("+ " + N + " " + (j + 1));
        		}
            }
            
            int x = 0;
            int y = oIndex;
            while (++x < N - 1 && ++y < N) {
            	steps.add("x " + (x + 1) + " " + (y + 1));
            }
            x = N - 1;
            y = oIndex - 1;
            while (--x >= 0 && --y >= 0) {
            	steps.add("x " + (x + 1) + " " + (y + 1));
            }
            
        	point += 2 * N - 3;
            System.out.println("Case #" + i + ": " + point + " " + steps.size());
            for (String str : steps) {
            	System.out.println(str);
            }
        }
    }
}