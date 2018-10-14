// https://code.google.com/codejam/contest/3254486/dashboard#s=p0
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class HugeNumbers {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            int A = in.nextInt();
            int N = in.nextInt();
            int P = in.nextInt();

            boolean useNaiveSolve = true;
            long product = 1;
            for (long j = N; j >= 1; --j) {
                product *= j;
                if (product > P) {
                    useNaiveSolve = false;
                    break;
                }
            }
            long res;
            if (useNaiveSolve) {
                res = naiveSolve(A, P, product);
            } else {
                res = advancedSolve(A, N, P);
            }
            System.out.println("Case #" + i + ": " + res);
        }
    }


    private static int naiveSolve(int A, int P, long factorialN) {
        int remainder = 1;
        for (long j = 0 ; j < factorialN; ++j) {
            remainder = (remainder * A) % P;
        }
        return remainder;
    }


    // N! must be greater than P.
    private static long advancedSolve(int A, int N, int P) {
        long remainder = 1;
        ArrayList<Long> remainders = new ArrayList<>(P);
        while (true){
            remainder = (remainder * A) % P;
            if (remainders.contains(remainder)) {
                break;
            }
            remainders.add(remainder);
        }
        int index = remainders.indexOf(remainder);
        int circleLength = remainders.size() - index;
        int offset;
        if (N >= circleLength) {
            offset = (circleLength - (remainders.size() + 1) % circleLength) % circleLength;
        } else {
            offset = (int) ((factorial(N) - remainders.size() - 1) % circleLength);
        }
        return remainders.get(index + offset);
    }


    private static long factorial(int num) {
        long product = 1;
        for (int i = 1; i <= num; ++i) {
            product *= i;
        }
        return product;
    }
}