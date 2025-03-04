class Solution {
    public boolean checkPowersOfThree(int n) {
        
        int p = 0;
        while (Math.pow(3,p) <= n){
            p++;
        }
        while (n>0){
            if(n >= Math.pow(3,p)){
                n = n - (int)Math.pow(3,p);
            }
        if (n >= Math.pow(3,p)){
            return false;
        }
        p--;
        }
        return true;
    }
}