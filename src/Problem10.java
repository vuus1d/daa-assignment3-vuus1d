import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Problem10 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
            inDegree[p[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int[] order = new int[numCourses];
        int count = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            order[count++] = v;
            for (int to : graph.get(v)) {
                inDegree[to]--;
                if (inDegree[to] == 0) {
                    queue.add(to);
                }
            }
        }
        if (count < numCourses) {
            return new int[0];
        }
        return order;
    }

    public static void main(String[] args) {
        Problem10 solution = new Problem10();
        // any valid topological order is accepted, not only the one in the example
        System.out.println("Example 1: " + java.util.Arrays.toString(solution.findOrder(2, new int[][]{{1, 0}})) + " (expected [0, 1])");
        System.out.println("Example 2: " + java.util.Arrays.toString(solution.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})) + " (expected [0, 2, 1, 3] or [0, 1, 2, 3])");
        System.out.println("Example 3: " + java.util.Arrays.toString(solution.findOrder(1, new int[][]{})) + " (expected [0])");
    }
}
