class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int write = 0;
        for(int i=0; i<n; i++) {
            if(nums[i] != nums[write]) {
                write++;
                nums[write] = nums[i];
            }
        }
        return write + 1;
    }
}