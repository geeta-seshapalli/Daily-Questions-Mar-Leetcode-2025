class Solution {
    private int find(int[] dsuf, int v) {
        if (dsuf[v] == -1) {
            return v;
        }
        return dsuf[v] = find(dsuf, dsuf[v]); 
    }

    private void traverseComponent(int[] bitwiseAnd, int curr, boolean[] visited, List<List<int[]>> adj) {
        visited[curr] = true;
        for (int[] edge : adj.get(curr)) {
            int nbr = edge[0], wt = edge[1];
            bitwiseAnd[0] &= wt; 
            if (!visited[nbr]) {
                traverseComponent(bitwiseAnd, nbr, visited, adj);
            }
        }
    }

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        int[] dsuf = new int[n];
        Arrays.fill(dsuf, -1);
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt});

            int px = find(dsuf, u);
            int py = find(dsuf, v);
            if (px != py) {
                dsuf[px] = py; // Union
            }
        }

        Map<Integer, Integer> parentCost = new HashMap<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] bitwiseAnd = new int[]{adj.get(i).isEmpty() ? 0 : adj.get(i).get(0)[1]};
                traverseComponent(bitwiseAnd, i, visited, adj);
                int parent = find(dsuf, i);
                parentCost.put(parent, bitwiseAnd[0]);
            }
        }

        int[] ans = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int px = find(dsuf, query[i][0]);
            int py = find(dsuf, query[i][1]);
            if (px == py) {
                ans[i] = parentCost.get(px);
            } else {
                ans[i] = -1;
            }
        }

        return ans;
    }
}