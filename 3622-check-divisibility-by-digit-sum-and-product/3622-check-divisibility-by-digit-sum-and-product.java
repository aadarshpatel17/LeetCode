class Solution {
    public boolean checkDivisibility(int n) {
        return n % digitCal(n) == 0;
    } 

    // digit sum
    public int digitCal(int n) {
        int sum = 0;
        int product = 1;
        while (n > 0) {
            int ld = n % 10;
            sum += ld;
            product *= ld;
            n /= 10;
        }
        return sum + product;
    }

    // digit product
}