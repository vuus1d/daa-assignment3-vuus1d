public class Problem01 {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                provinces++;
                dfs(isConnected, visited, i);
            }
        }
        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true;
        for (int next = 0; next < isConnected.length; next++) {
            if (isConnected[city][next] == 1 && !visited[next]) {
                dfs(isConnected, visited, next);
            }
        }
    }

    public static void main(String[] args) {
        Problem01 solution = new Problem01();
        System.out.println("Example 1: " + solution.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}) + " (expected 2)");
        System.out.println("Example 2: " + solution.findCircleNum(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}) + " (expected 3)");
    }
}
