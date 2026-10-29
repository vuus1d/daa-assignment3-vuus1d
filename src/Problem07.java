import java.util.ArrayList;
import java.util.List;

public class Problem07 {
    private List<List<Integer>> graph;
    // 0 = not visited, 1 = in current path, 2 = done
    private int[] state;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
        }
        state = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && hasCycle(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(int v) {
        state[v] = 1;
        for (int to : graph.get(v)) {
            if (state[to] == 1) {
                return true;
            }
            if (state[to] == 0 && hasCycle(to)) {
                return true;
            }
        }
        state[v] = 2;
        return false;
    }

    public static void main(String[] args) {
        Problem07 solution = new Problem07();
        System.out.println("Example 1: " + solution.canFinish(2, new int[][]{{1, 0}}) + " (expected true)");
        System.out.println("Example 2: " + solution.canFinish(2, new int[][]{{1, 0}, {0, 1}}) + " (expected false)");
    }
}
