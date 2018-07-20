//GCJ Qualification Round 2009:A
import java.util.*;
import java.io.*;
public class AlienLanguage{
	@SuppressWarnings("resource")
	public static void main(String[] args){
	    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        @SuppressWarnings("unused")
		final int L = in.nextInt();
        final int D = in.nextInt();
        final int N = in.nextInt();
        final String[] words=new String[D];
        for(int i=0; i<D; ++i){
        	words[i]=in.next();
        }
        for (int i = 1; i <= N; ++i) {
        	String pattern=in.next().replace('(', '[').replace(')',']');
        	int sum=0;
        	for(int j=0; j<D; ++j){
        		if(words[j].matches(pattern)) ++sum;
        	}
            System.out.println("Case #" + i + ": " + sum);
        }
	}
}