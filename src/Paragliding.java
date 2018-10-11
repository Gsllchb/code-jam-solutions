// https://code.google.com/codejam/contest/6364486/dashboard#s=p1&a=1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Paragliding {
    static class Location implements Comparable<Location> {
        long x;
        long y;
        Location(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Location o) {
            return (int) (x - o.x);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int k = in.nextInt();

            int p1 = in.nextInt();
            int p2 = in.nextInt();
            int a1 = in.nextInt();
            int b1 = in.nextInt();
            int c1 = in.nextInt();
            int m1 = in.nextInt();

            int h1 = in.nextInt();
            int h2 = in.nextInt();
            int a2 = in.nextInt();
            int b2 = in.nextInt();
            int c2 = in.nextInt();
            int m2 = in.nextInt();

            int x1 = in.nextInt();
            int x2 = in.nextInt();
            int a3 = in.nextInt();
            int b3 = in.nextInt();
            int c3 = in.nextInt();
            int m3 = in.nextInt();

            int y1 = in.nextInt();
            int y2 = in.nextInt();
            int a4 = in.nextInt();
            int b4 = in.nextInt();
            int c4 = in.nextInt();
            int m4 = in.nextInt();

            long[] p = generateSeq(n, p1, p2, a1, b1, c1, m1);
            long[] h = generateSeq(n, h1, h2, a2, b2, c2, m2);
            long[] x = generateSeq(k, x1, x2, a3, b3, c3, m3);
            long[] y = generateSeq(k, y1, y2, a4, b4, c4, m4);

            Location[] towers = pruneTowers(p, h);
            int count = countBalloon(x, y, towers);
            System.out.println("Case #" + i + ": " + count);
        }
    }


    private static Location[] pruneTowers(long[] p, long[] h) {
        assert p.length == h.length;
        ArrayList<Location> list = new ArrayList<>(p.length);
        for (int j = 0; j < p.length; ++j) {
            list.add(new Location(p[j], h[j]));
        }
        list.sort(Location::compareTo);
        ArrayList<Location> relevantTowers = new ArrayList<>();
        for (Location tower : list) {
            while (!relevantTowers.isEmpty() && canReach(tower, relevantTowers.get(relevantTowers.size() - 1))) {
                relevantTowers.remove(relevantTowers.size() - 1);
            }
            relevantTowers.add(tower);
        }
        list = relevantTowers;
        relevantTowers = new ArrayList<>();
        for (int j = list.size() - 1; j >= 0; --j) {
            Location tower = list.get(j);
            while (!relevantTowers.isEmpty() && canReach(tower, relevantTowers.get(relevantTowers.size() - 1))) {
                relevantTowers.remove(relevantTowers.size() - 1);
            }
            relevantTowers.add(tower);
        }
        Location[] res = new Location[relevantTowers.size()];
        for (int j = 0; j < res.length; ++j) {
            res[j] = relevantTowers.get(res.length - j - 1);
        }
        return res;
    }


    private static int countBalloon(long[] x, long[] y, Location[] towers) {
        assert x.length == y.length;
        int count = 0;
        for (int j = 0; j < x.length; ++j) {
            Location location = new Location(x[j], y[j]);
            int index = Arrays.binarySearch(towers, location);
            if (index >= 0) {
                if (canReach(towers[index], location)) {
                    ++count;
                }
            } else {
                int insertion = -index - 1;
                if (insertion - 1 >= 0) {
                    Location left = towers[insertion - 1];
                    if (canReach(left, location)) {
                        ++count;
                        continue;
                    }
                }
                if (insertion < towers.length) {
                    Location right = towers[insertion];
                    if (canReach(right, location)) {
                        ++count;
                    }
                }
            }
        }
        return count;
    }

    private static boolean canReach(Location start, Location dest) {
        return start.y - dest.y >= Math.abs(start.x - dest.x);
    }


    private static long[] generateSeq(int len, int first, int second, int a, int b, int c, int m) {
        long[] seq = new long[len];
        seq[0] = first;
        seq[1] = second;
        for (int i = 2; i < len; ++i) {
            seq[i] = ((a * seq[i - 1]) % m + (b * seq[i - 2]) % m + c) % m + 1;
        }
        return seq;
    }

}