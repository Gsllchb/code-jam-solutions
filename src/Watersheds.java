//GCJ Qualification Round 2009:B
import java.util.*;
import java.io.*;
public class Watersheds {
	static char label;
	
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
        	label='a';
            final int H = in.nextInt();
            final int W = in.nextInt();
            int[][] map=new int[H][W];
            for(int j=0; j<H; ++j){
            	for(int k=0; k<W; ++k){
            		map[j][k]=in.nextInt();
            	}
            }
            char[][] basins=new char[H][W];
            for(int j=0; j<H; ++j){
            	for(int k=0; k<W; ++k){
            		explore(map,basins,k,j);
            	}
            }
            System.out.println("Case #" + i + ":");
            for(int j=0; j<H; ++j){
            	for(int k=0; k<W; ++k){
            		System.out.print(basins[j][k]+" ");
            	}
            	System.out.println();
            }
        }
    }
	static void explore(final int[][] map, char[][] basins, final int x, final int y){
		char temp=basins[y][x];
		if(temp==0){
			basins[y][x]=label;
		}else{
			for(int i=0; i<basins.length; ++i){
				for(int j=0; j<basins[0].length; ++j){
					if(basins[i][j]==label)
						basins[i][j]=temp;
				}
			}
			return;
		}
		
		int min=Integer.MAX_VALUE;
		if(y>=1 && min>map[y-1][x]) min=map[y-1][x];
		if(x>=1 && min>map[y][x-1]) min=map[y][x-1];
		if(x<=map[0].length-2 && min>map[y][x+1]) min=map[y][x+1];
		if(y<=map.length-2 && min>map[y+1][x]) min=map[y+1][x];
		
		if(min>=map[y][x]){
			++label;
			return;
		}
		
		int nextX = -1;
		int nextY = -1;
		if(y<=map.length-2 && map[y+1][x]==min){
			nextX=x;
			nextY=y+1;
		}
		if(x<=map[0].length-2 && map[y][x+1]==min){
			nextX=x+1;
			nextY=y;
		}
		if(x>=1 && map[y][x-1]==min){
			nextX=x-1;
			nextY=y;
		}
		if(y>=1 && map[y-1][x]==min){
			nextX=x;
			nextY=y-1;
		}
		
		explore(map,basins,nextX,nextY);
	}
}