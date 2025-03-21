class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        int n = recipes.length;
        Set<String> st = new HashSet<>(Arrays.asList(supplies));
        
        
        Map<String, List<Integer>> adj = new HashMap<>(); 
        int[] indegree = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (String ing : ingredients.get(i)) {
                if (!st.contains(ing)) {
                    adj.putIfAbsent(ing, new ArrayList<>());
                    adj.get(ing).add(i);
                    indegree[i]++;
                }
            }
        }
        
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                que.add(i);
            }
        }
        
        List<String> result = new ArrayList<>();
        while (!que.isEmpty()) {
            int i = que.poll();
            String recipe = recipes[i];
            result.add(recipe);
            
            if (adj.containsKey(recipe)) {
                for (int idx : adj.get(recipe)) {
                    indegree[idx]--;
                    if (indegree[idx] == 0) {
                        que.add(idx);
                    }
                }
            }
        }
        
        return result;
    }
}