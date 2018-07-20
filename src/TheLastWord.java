//GCJ Round 1A 2016: A
import java.util.*;
import java.io.*;
public class TheLastWord {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final String S = in.next();
            char[] words = S.toCharArray();
            LinkedList<Character> lastWord = new LinkedList<Character>();
            lastWord.add(words[0]);
            for (int j = 1; j < words.length; ++j) {
            	if (words[j] >= lastWord.getFirst()) {
            		lastWord.addFirst(words[j]);
            	} else {
            		lastWord.addLast(words[j]);
            	}
            }
            System.out.print("Case #" + i + ": ");
            lastWord.stream().forEach(System.out::print);
            System.out.println();
        }
    }
}