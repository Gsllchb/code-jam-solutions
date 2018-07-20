//GCJ Round 1A 2016: C
import java.util.*;
import java.io.*;
import static java.lang.System.out;
public class BFFs {
	static int forwardMax = 0;
	static int backwardMax = 0;
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final int N = in.nextInt();
            final int[] BFFs = new int[N + 1];
            for (int j = 1; j <= N; ++j) {
            	BFFs[j] = in.nextInt();
            }
            
            int max = 0;
            List<LinkedList<Integer>> circles = new LinkedList<LinkedList<Integer>>();
            for (int j = 1; j <= N; ++j) {
            	LinkedList<Integer> circle = new LinkedList<Integer>();
            	circle.addLast(j);
            	for ( ; ; ) {
            		int BFF = BFFs[circle.getLast()];
            		if (BFF == circle.getFirst()) {
            			if (circle.size() == 2) {
            				circles.add(circle);
            			} else {
            				max = circle.size() > max ? circle.size() : max;
            			}
            			break;
            		}
            		if (circle.contains(BFF)) {
            			break;
            		}
            		circle.addLast(BFF);
            	}
            }
            
            int tempMax = 0;
            for (LinkedList<Integer> circle : circles) {
            	forwardMax = 0;
            	forward(circle, BFFs);
            	backwardMax = 0;
            	backward(circle, BFFs);
            	tempMax += forwardMax + backwardMax - 2;
            }
            tempMax /= 2;
            max = tempMax > max ? tempMax : max;
            out.println("Case #" + i + ": " + max);
        }
    }
	
	static void forward(LinkedList<Integer> circle, final int[] BFFs) {
		for (int i = 1; i < BFFs.length; ++i) {
			if (BFFs[i] == circle.getLast() && i != circle.getFirst()) {
				LinkedList<Integer> temp = new LinkedList<Integer>(circle);
				temp.addLast(i);
				forward(temp, BFFs);
			}
		}
		forwardMax = circle.size() > forwardMax ? circle.size() : forwardMax;
	}
	
	static void backward(LinkedList<Integer> circle, final int[] BFFs) {
		for (int i = 1; i < BFFs.length; ++i) {
			if (BFFs[i] == circle.getFirst() && i != circle.getLast()) {
				LinkedList<Integer> temp = new LinkedList<Integer>(circle);
				temp.addFirst(i);
				backward(temp, BFFs);
			}
		}
		backwardMax = circle.size() > backwardMax ? circle.size() : backwardMax;
	}
}