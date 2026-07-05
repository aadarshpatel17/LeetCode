class Solution {
    public int removeDuplicates(int[] nums) {
        int write = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[write - 2]) {
                nums[write++] = nums[i];
            }
        }
        return write;
    }
}