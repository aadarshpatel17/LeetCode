class Solution {
    public boolean checkDivisibility(int n) {
        return n % (digitSum(n) + digitProduct(n)) == 0;
    } 

    // digit sum
    public int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            int ld = n % 10;
            sum += ld;
            n /= 10;
        }
        return sum;
    }

    // digit product
    public int digitProduct(int n) {
        int product = 1;
        while (n > 0) {
            int ld = n % 10;
            product *= ld;
            n /= 10;
        }
        return product;
    }
}