import java.util.ArrayList;
import java.util.List;

public class Problem09 {
    private List<List<Integer>> graph;
    // 0 = not visited, 1 = in current path, 2 = done
    private int[] state;
    private int[] order;
    private int pos;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
        }
        state = new int[numCourses];
        order = new int[numCourses];
        // fill from the end, so the array is the reverse finishing order
        pos = numCourses - 1;
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && hasCycle(i)) {
                return new int[0];
            }
        }
        return order;
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
        order[pos--] = v;
        return false;
    }

    public static void main(String[] args) {
        Problem09 solution = new Problem09();
        // any valid topological order is accepted, not only the one in the example
        System.out.println("Example 1: " + java.util.Arrays.toString(solution.findOrder(2, new int[][]{{1, 0}})) + " (expected [0, 1])");
        System.out.println("Example 2: " + java.util.Arrays.toString(solution.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})) + " (expected [0, 2, 1, 3] or [0, 1, 2, 3])");
        System.out.println("Example 3: " + java.util.Arrays.toString(solution.findOrder(1, new int[][]{})) + " (expected [0])");
    }
}
