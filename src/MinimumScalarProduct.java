//GCJ Round 1A 2008:A
import java.util.*;
import java.io.*;
public class MinimumScalarProduct {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();    
        for (int i = 1; i <= T; ++i) {
        	final int n=in.nextInt();
        	int[] x=new int[n];
        	int[] y=new int[n];    	
        	for(int j=0; j<n; ++j){
        		x[j]=in.nextInt();
        	}
	    	for(int j=0; j<n; ++j){
	    		y[j]=in.nextInt();
	    	}    	
	    	Arrays.sort(x);
	    	Arrays.sort(y);
	    	long sum=0;
	    	for(int j=0; j<n; ++j){
	    		sum+=(1L*x[j]*y[n-1-j]);
	    	}    	
	    	System.out.println("Case #" + i + ": " + sum);
        }    
	}
}