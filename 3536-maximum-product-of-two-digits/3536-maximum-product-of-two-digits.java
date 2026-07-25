class Solution {
    public int maxProduct(int n) {
        if (n < 10) {
            return n;
        }

        int l = 0;
        int sl = 0;

        while(n > 0) {
            int ld = n % 10;
            if(ld >= l) {
                sl = l;
                l = ld;
            } 
            if(ld < l && ld > sl) {
                sl = ld;
            }
            n /= 10;
        }

        return l * sl;
    }
}