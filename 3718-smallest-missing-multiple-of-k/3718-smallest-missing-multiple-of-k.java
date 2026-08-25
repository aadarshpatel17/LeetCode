class Solution {
    public int missingMultiple(int[] nums, int k) {
        int result = -1;
        int n = nums.length;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 1; i <= n + 1; i++) {
            int multiple = k * i;
            if (!set.contains(multiple)) {
                return multiple;
            }
        }
        return 0;
    }
}