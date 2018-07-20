// https://code.google.com/codejam/contest/4374486/dashboard#s=p2
import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt();
    for (int i = 1; i <= t; ++i) {
      int n = in.nextInt();
      String[] starts = new String[n];
      String[] ends = new String[n];
      for (int j = 0; j < n; ++j) {
        starts[j] = in.next();
        ends[j] = in.next();
      }
      String from = null;
      for (String start: starts) {
        boolean isUnique = true;
        for (String end : ends) {
          if (start.equals(end)) {
            isUnique = false;
            break;
          }
        }
        if (isUnique) {
          from = start;
          break;
        }
      }
      System.out.print("Case #" + i + ":");
      while (true) {
        int index = find(starts, from);
        if (index == -1) {
          break;
        }
        System.out.print(" " + from);
        String to = ends[index];
        System.out.print("-" + to);
        from = to;
      }
      System.out.println();
    }
  }

  static int find(String[] arr, String value) {
    for (int i = 0; i < arr.length; ++i) {
      if (arr[i].equals(value)) {
        return i;
      }
    }
    return -1;
  }
}