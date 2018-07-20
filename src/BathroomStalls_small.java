//GCJ Qualification Round 2017: C small
import java.util.*;
import java.io.*;
public class BathroomStalls_small {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final long N = in.nextLong();
            final long K = in.nextLong();
            
            SortedMap<Long, Long> map = new TreeMap<Long, Long>();
            map.put(N, 1L);
            for (int j = 0; j < K - 1; ++j) {
            	long key = map.lastKey();
            	long value = map.get(key);
            	if (--value == 0) {
            		map.remove(key);
            	} else {
            		map.put(key, value);
            	}
            	
            	if (key % 2 == 0) {
            		if (map.containsKey(key / 2)) {
            			value = map.get(key / 2);
            			map.put(key / 2, ++value);
            		} else {
            			map.put(key / 2, 1L);
            		}
            		
            		if (map.containsKey(key / 2 - 1)) {
            			value = map.get(key / 2 - 1);
            			map.put(key / 2 - 1, ++value);
            		} else {
            			map.put(key / 2 - 1, 1L);
            		}
            	} else {
            		if (map.containsKey(key / 2)) {
            			value = map.get(key / 2);
            			value += 2;
            			map.put(key / 2, value);
            		} else {
            			map.put(key / 2, 2L);
            		}
            	}
            	
            }
            long temp = map.lastKey();
            if (temp % 2 == 0) {
            	System.out.println("Case #" + i + ": " + (temp / 2) + " " + (temp / 2 - 1));
            } else {
            	System.out.println("Case #" + i + ": " + (temp / 2) + " " + (temp / 2));
            }
            
        }
    }
}