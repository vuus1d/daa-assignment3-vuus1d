import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Problem05 {
    public int countDependencyGroups(int n, int[][] dependencies){
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> reversed = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            reversed.add(new ArrayList<>());
        }
        for (int[] d : dependencies) {
            graph.get(d[0]).add(d[1]);
            reversed.get(d[1]).add(d[0]);
        }

        // DFS is iterative because recursion can overflow the stack for n = 1e5
        boolean[] visited = new boolean[n];
        int[] nextEdge = new int[n];
        Deque<Integer> order = new ArrayDeque<>();
        for (int s = 0; s < n; s++) {
            if (visited[s]) {
                continue;
            }
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(s);
            visited[s] = true;
            while (!stack.isEmpty()) {
                int v = stack.peek();
                if (nextEdge[v] < graph.get(v).size()) {
                    int to = graph.get(v).get(nextEdge[v]++);
                    if (!visited[to]) {
                        visited[to] = true;
                        stack.push(to);
                    }
                } else {
                    stack.pop();
                    order.push(v);
                }
            }
        }

        boolean[] assigned = new boolean[n];
        int groups = 0;
        for (int s : order) {
            if (assigned[s]) {
                continue;
            }
            groups++;
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(s);
            assigned[s] = true;
            while (!stack.isEmpty()) {
                int v = stack.pop();
                for (int to : reversed.get(v)) {
                    if (!assigned[to]) {
                        assigned[to] = true;
                        stack.push(to);
                    }
                }
            }
        }
        return groups;
    }

    public static void main(String[] args) {
        Problem05 solution = new Problem05();
        System.out.println("Example 1: " + solution.countDependencyGroups(5, new int[][]{{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}}) + " (expected 3)");
        System.out.println("Example 2: " + solution.countDependencyGroups(4, new int[][]{{0, 1}, {1, 0}, {2, 3}, {3, 2}}) + " (expected 2)");
        System.out.println("Example 3: " + solution.countDependencyGroups(4, new int[][]{{0, 1}, {1, 2}, {2, 3}}) + " (expected 4)");
    }

}
