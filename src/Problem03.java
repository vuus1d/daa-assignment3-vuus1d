import java.util.ArrayList;
import java.util.List;

public class Problem03 {
    private int[] parent;

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{dist, i, j});
            }
        }
        edges.sort((a, b) -> Integer.compare(a[0], b[0]));

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int cost = 0;
        int used = 0;
        for (int[] e : edges) {
            if (used == n - 1) {
                break;
            }
            int rootA = find(e[1]);
            int rootB = find(e[2]);
            if (rootA != rootB) {
                parent[rootA] = rootB;
                cost += e[0];
                used++;
            }
        }
        return cost;
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public static void main(String[] args) {
        Problem03 solution = new Problem03();
        System.out.println("Example 1: " + solution.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}) + " (expected 20)");
        System.out.println("Example 2: " + solution.minCostConnectPoints(new int[][]{{3, 12}, {-2, 5}, {-4, 1}}) + " (expected 18)");
    }
}
