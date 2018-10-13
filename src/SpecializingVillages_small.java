// https://code.google.com/codejam/contest/3314486/dashboard#s=p1&a=1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class SpecializingVillages {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            int V = in.nextInt();
            int E = in.nextInt();
            int[][] matrix = initialMatrix(V);
            for (int j = 0; j < E; ++j) {
                int A = in.nextInt();
                int B = in.nextInt();
                int L = in.nextInt();
                matrix[A - 1][B - 1] = L;
                matrix[B - 1][A - 1] = L;
            }

            boolean[][] allPlans = findAllPlans(V);
            long minDistance = Long.MAX_VALUE;
            long numMin = 0;
            for (boolean[] plan: allPlans) {
                long distance = computeSumDistance(plan, matrix);
                if (distance == -1) {
                    continue;
                } else if (distance == minDistance) {
                    ++numMin;
                } else if (distance < minDistance) {
                    minDistance = distance;
                    numMin = 1;
                }
            }

            System.out.println("Case #" + i + ": " + numMin);
        }
    }

    static int[][] initialMatrix(int V) {
        int[][] matrix = new int[V][V];
        for (int i = 0; i < V; ++i) {
            for (int j= 0; j < V; ++j) {
                matrix[i][j] = -1;
            }
        }
        return matrix;
    }

    static boolean[][] findAllPlans(int V) {
        boolean[][] allPlans = new boolean[1 << V][V];
        int numPlan = 1;
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < numPlan; ++j) {
                System.arraycopy(allPlans[j], 0, allPlans[numPlan + j], 0, i);
            }
            for (int j = 0; j < numPlan; ++j) {
                allPlans[j][i] = true;
            }
            numPlan <<= 1;
        }
        return allPlans;
    }

    static long computeSumDistance(boolean[] plan, int[][] matrix) {
        long sumDistance = 0;
        for (int village = 0; village < matrix.length; ++village) {
            long distance = computeDistance(village, plan, matrix);
            if (distance == -1) {
                return -1;
            }
            sumDistance += distance;
        }
        return sumDistance;
    }

    static long computeDistance(int village, boolean[] plan, int[][] matrix) {
        PriorityQueue<Path> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Path(village));
        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();
            int endVillage = path.getLast();
            if (plan[village] != plan[endVillage]) {
                return path.length();
            }
            for (int nextVillage = 0; nextVillage < matrix.length; ++nextVillage) {
                int distance = matrix[endVillage][nextVillage];
                if (distance == -1 || path.contains(nextVillage)) {
                    continue;
                }
                Path clone = new Path(path);
                clone.append(nextVillage, distance);
                priorityQueue.add(clone);
            }
        }
        return -1;
    }


    static class Path implements Comparable<Path> {
        private ArrayList<Integer> villages;
        private long length;

        Path(Path path) {
            villages = new ArrayList<>(path.villages);
            length = path.length;
        }

        Path(int start) {
            villages = new ArrayList<>();
            length = 0;
            villages.add(start);
        }

        @Override
        public int compareTo(Path o) {
            return (int) (length - o.length);
        }

        boolean contains(int village) {
            return villages.contains(village);
        }

        int getLast() {
            return villages.get(villages.size() - 1);
        }

        void append(int village, int distance) {
            villages.add(village);
            length += distance;
        }

        long length() {
            return length;
        }
    }
}