// https://code.google.com/codejam/contest/4374486/dashboard#s=p0
import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt();
    for (int i = 1; i <= t; ++i) {
      int n = in.nextInt();
      int[] start = new int[n];
      int[] end = new int[n];
      for (int j = 0; j < n; ++j){
        start[j] = in.nextInt();
        end[j] = in.nextInt();
      }
      int p = in.nextInt();
      int[] res = new int[p];
      for (int j = 0; j < p; ++j) {
        int city = in.nextInt();
        res[j] = 0;
        for (int k = 0; k < n; ++k) {
          if (city >= start[k] && city <= end[k]) {
            ++res[j];
          }
        }
      }
      System.out.print("Case #" + i + ":");
      for (int j: res) {
        System.out.print(" " + j);
      }
      System.out.println();
    }
  }
}