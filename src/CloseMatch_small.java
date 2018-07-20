//GCJ Round 1B 2016:B small
import java.util.*;

import Jama.Matrix;

import java.io.*;
public class CloseMatch_small {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final String C = in.next();
            final String J = in.next();
            final char[] coder = C.toCharArray();
            final char[] jammer = J.toCharArray();
            
            List<char[]> coders = new LinkedList<char[]>();
            List<char[]> jammers = new LinkedList<char[]>();
            foo(coders, coder, 0);
            foo(jammers, jammer, 0);
            String cResult = null;
            String jResult = null;
            int cSumMin = Integer.MAX_VALUE;
            int jSumMin = Integer.MAX_VALUE;
            int differenceMin = Integer.MAX_VALUE;
            for (char[] tempC : coders) {
            	int length = tempC.length;
            	for (char[] tempJ : jammers) {
            		int cSum = 0;
            		int jSum = 0;
            		for (int j = 0; j < length; ++j) {
            			cSum += tempC[j] * Math.pow(10, length - j - 1);
            			jSum += tempJ[j] * Math.pow(10, length - j - 1);
            		}
            		int difference = Math.abs(cSum - jSum);
            		if (difference < differenceMin) {
            			cResult = String.valueOf(tempC);
            			jResult = String.valueOf(tempJ);
            			cSumMin = cSum;
            			jSumMin = jSum;
            			differenceMin = difference;
            		} else if (difference == differenceMin && cSum < cSumMin) {
            			cResult = String.valueOf(tempC);
            			jResult = String.valueOf(tempJ);
            			cSumMin = cSum;
            			jSumMin = jSum;
            		} else if (difference == differenceMin && cSum == cSumMin && jSum < jSumMin) {
            			cResult = String.valueOf(tempC);
            			jResult = String.valueOf(tempJ);
            			jSumMin = jSum;
            		}
            			
            	}
            }
            
            System.out.println("Case #" + i + ": " + cResult + " " + jResult);
        }
    }
	
	static void foo(List<char[]> strs, final char[] str, final int rank) {
		final int length = str.length;
		if (rank == length) {
			strs.add(str);
			return;
		}
		if (str[rank] == '?') {
			for (int i = 0; i < 10; ++i) {
				char[] temp = new char[length];
				System.arraycopy(str, 0, temp, 0, length);
				temp[rank] = (char)('0' + i);
				foo(strs, temp, rank + 1);
			}
		} else {
			foo(strs, str, rank + 1);
		}
	}
}