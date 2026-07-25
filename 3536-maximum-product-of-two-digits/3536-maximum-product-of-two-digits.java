class Solution {
    public int maxProduct(int n) {
        if (n < 10) {
            return n;
        }

        int max = 0;
        List<Integer> digits = new ArrayList<>();
        while(n > 0) {
            int ld = n % 10;
            digits.add(ld);
            n /= 10;
        }

        int prod = 0;
        for(int i=0; i<digits.size(); i++) {
            for(int j=0; j<digits.size(); j++) {
                if(i != j) {
                    prod = Math.max(prod,digits.get(i) * digits.get(j));
                }
            }
        }
        max = Math.max(prod, max);
        return max;
    }
}