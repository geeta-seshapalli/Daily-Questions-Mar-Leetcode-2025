class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;

        long N = (long) n * n;

        long gridSum = 0;
        long gridSqSum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gridSum += grid[i][j];
                gridSqSum += (long) grid[i][j] * grid[i][j];
            }
        }

        long sum = (N * (N + 1)) / 2; // Expected sum of N natural numbers
        long sqSum = (N * (N + 1) * (2 * N + 1)) / 6; // Expected sq sum of natural numbers

        long sqDiff = gridSqSum - sqSum;
        long sumDiff = gridSum - sum;

        int a = (int) ((sqDiff / sumDiff + sumDiff) / 2);
        int b = (int) ((sqDiff / sumDiff - sumDiff) / 2);

        return new int[]{a, b};
    }
}