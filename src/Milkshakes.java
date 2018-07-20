//GCJ Round 1A 2008:B
import java.util.*;
import java.io.*;
public class Milkshakes {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int C = in.nextInt();
        for (int i = 1; i <= C; ++i) {
            final int N=in.nextInt();
            final int M=in.nextInt();
            int[] batchs=new int[N];
            int[][] customerSets=new int[M][N];
            for(int j=0; j<M; ++j){
            	final int T=in.nextInt();
            	for(int k=0; k<T; ++k){
            		customerSets[j][in.nextInt()-1]= in.nextInt()+1;	//0,1 and 2 individually represents null,unmalted and malted.
            	}
            }
            if(makeBatchs(batchs, customerSets)){
            	String str="";
            	for(int j=0; j<N; ++j){
            		str+=" "+batchs[j];
            	}
            	System.out.println("Case #" + i + ": "+str);
            }else{
            	System.out.println("Case #" + i + ": IMPOSSIBLE");
            }
        }
    }
    private static boolean makeBatchs(int[] batchs, final int[][] customerSets){
    	for(int i=0; i<customerSets.length; ++i){
    		boolean flag=false;
    		int maltedIndex=-1;
    		for(int j=0; j<customerSets[i].length; ++j){
    			if(customerSets[i][j]==2)
    				maltedIndex=j;
    			if(batchs[j]+1==customerSets[i][j]){
    				flag=true;
    				break;
    			}			
    		}
    		if(maltedIndex==-1 && flag==false){
    			return false;
    		}else if(maltedIndex!=-1 && flag==false){
    			batchs[maltedIndex]=1;
    			return makeBatchs(batchs, customerSets);
    		}
    	}
    	return true;
    }
}