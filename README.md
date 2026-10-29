# DAA-Assignment3 - Graph Algorithms

You are to solve the following graph problems. Each problem is 5 points.

1. [Number of Provinces using DFS](Problem01.md) **(5 points)**.
2. [Number of Provinces using DSU](Problem02.md) **(5 points)**.
3. [Min Cost to Connect All Points using Kruskal](Problem03.md) **(5 points)**.
4. [Min Cost to Connect All Points using Prim](Problem04.md) **(5 points)**.
5. [Software Dependency Cycles using Kosaraju](Problem05.md) **(5 points)**.
6. [Software Dependency Cycles using Tarjan](Problem06.md) **(5 points)**
7. [Course Schedule using DFS with cycle detection](Problem07.md) **(5 points)**.
8. [Course Schedule using Kahn](Problem08.md) **(5 points)**.
9. [Course Schedule II using DFS with reverse finishing order](Problem09.md) **(5 points)**.
10. [Course Schedule II using Kahn](Problem10.md) **(5 points)**.

**Write and defend** a report describing your solutions for each problem in [REPORT.md](REPORT.md) **(50 points)**.

## How to build and run

Only the standard JDK is needed (Java 8+). From the repository root:

```
javac -d out src/*.java
```

The classes have no `main` method. Create an object and call its method, for example `new Problem05().countDependencyGroups(5, new int[][]{{0,1},{1,2},{2,0},{2,3},{3,4}})` returns `3`.

## Summary

V = number of vertices, E = number of edges, n = number of cities/points.

| Problem | Algorithm | Time | Space |
|---|---|---|---|
| 01 Number of Provinces | DFS | O(n^2) | O(n) |
| 02 Number of Provinces | DSU (path compression + union by rank) | O(n^2 · α(n)) | O(n) |
| 03 Min Cost to Connect All Points | Kruskal | O(n^2 log n) | O(n^2) |
| 04 Min Cost to Connect All Points | Prim (array version) | O(n^2) | O(n) |
| 05 Software Dependency Cycles | Kosaraju (iterative DFS) | O(V + E) | O(V + E) |
| 06 Software Dependency Cycles | Tarjan (iterative DFS) | O(V + E) | O(V + E) |
| 07 Course Schedule | DFS with cycle detection | O(V + E) | O(V + E) |
| 08 Course Schedule | Kahn | O(V + E) | O(V + E) |
| 09 Course Schedule II | DFS, reverse finishing order | O(V + E) | O(V + E) |
| 10 Course Schedule II | Kahn | O(V + E) | O(V + E) |
