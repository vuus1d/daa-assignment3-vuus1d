import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Problem08 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        int taken = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            taken++;
            for (int to : graph.get(v)) {
                inDegree[to]--;
                if (inDegree[to] == 0) {
                    queue.add(to);
                }
            }
        }
        return taken == numCourses;
    }

    public static void main(String[] args) {
        Problem08 solution = new Problem08();
        System.out.println("Example 1: " + solution.canFinish(2, new int[][]{{1, 0}}) + " (expected true)");
        System.out.println("Example 2: " + solution.canFinish(2, new int[][]{{1, 0}, {0, 1}}) + " (expected false)");
    }
}
