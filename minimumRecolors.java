class Solution {
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int i = 0;
        int j = 0;

        int ops = k;
        int W = 0;

        while (j < n) {
            if (blocks.charAt(j) == 'W') {
                W++;
            }

            if (j - i + 1 == k) {
                ops = Math.min(ops, W);

                if (blocks.charAt(i) == 'W') {
                    W--;
                }
                i++;
            }
            j++;
        }

        return ops;
    }
}