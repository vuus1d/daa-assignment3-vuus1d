import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Problem06 {
    public int countDependencyGroups(int n, int[][] dependencies){
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] d : dependencies) {
            graph.get(d[0]).add(d[1]);
        }

        int[] ids = new int[n];
        int[] low = new int[n];
        Arrays.fill(ids, -1);
        boolean[] onStack = new boolean[n];
        int[] nextEdge = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int time = 0;
        int groups = 0;

        // DFS is iterative because recursion can overflow the stack for n = 1e5
        for (int s = 0; s < n; s++) {
            if (ids[s] != -1) {
                continue;
            }
            Deque<Integer> path = new ArrayDeque<>();
            path.push(s);
            ids[s] = low[s] = time++;
            stack.push(s);
            onStack[s] = true;

            while (!path.isEmpty()) {
                int v = path.peek();
                if (nextEdge[v] < graph.get(v).size()) {
                    int to = graph.get(v).get(nextEdge[v]++);
                    if (ids[to] == -1) {
                        ids[to] = low[to] = time++;
                        stack.push(to);
                        onStack[to] = true;
                        path.push(to);
                    } else if (onStack[to]) {
                        low[v] = Math.min(low[v], ids[to]);
                    }
                } else {
                    path.pop();
                    if (!path.isEmpty()) {
                        int parent = path.peek();
                        low[parent] = Math.min(low[parent], low[v]);
                    }
                    if (low[v] == ids[v]) {
                        groups++;
                        int w;
                        do {
                            w = stack.pop();
                            onStack[w] = false;
                        } while (w != v);
                    }
                }
            }
        }
        return groups;
    }

    public static void main(String[] args) {
        Problem06 solution = new Problem06();
        System.out.println("Example 1: " + solution.countDependencyGroups(5, new int[][]{{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}}) + " (expected 3)");
        System.out.println("Example 2: " + solution.countDependencyGroups(4, new int[][]{{0, 1}, {1, 0}, {2, 3}, {3, 2}}) + " (expected 2)");
        System.out.println("Example 3: " + solution.countDependencyGroups(4, new int[][]{{0, 1}, {1, 2}, {2, 3}}) + " (expected 4)");
    }

}
