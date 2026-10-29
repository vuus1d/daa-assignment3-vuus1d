import java.util.Arrays;

public class Problem04 {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] inTree = new boolean[n];
        // minDist[i] is the cheapest edge from point i to the current tree
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;

        int cost = 0;
        for (int step = 0; step < n; step++) {
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!inTree[i] && (u == -1 || minDist[i] < minDist[u])) {
                    u = i;
                }
            }
            inTree[u] = true;
            cost += minDist[u];

            for (int v = 0; v < n; v++) {
                if (!inTree[v]) {
                    int dist = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    if (dist < minDist[v]) {
                        minDist[v] = dist;
                    }
                }
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        Problem04 solution = new Problem04();
        System.out.println("Example 1: " + solution.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}) + " (expected 20)");
        System.out.println("Example 2: " + solution.minCostConnectPoints(new int[][]{{3, 12}, {-2, 5}, {-4, 1}}) + " (expected 18)");
    }
}
