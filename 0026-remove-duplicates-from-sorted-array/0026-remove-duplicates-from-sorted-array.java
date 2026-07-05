class Solution {
    public int removeDuplicates(int[] nums) {
        int write = 0;
        for(int i=1; i<nums.length; i++) {
            if(nums[i] != nums[write]) {
                nums[++write] = nums[i];
            }
        }
        return write + 1;
    }
}