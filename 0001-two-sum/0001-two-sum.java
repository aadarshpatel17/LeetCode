class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < nums.length; right++) {
            int currentTarget = target - nums[right];
            if (map.containsKey(currentTarget)) {
                return new int[]{right, map.get(currentTarget)};
            }
            map.put(nums[right], right);
        }
        return new int[]{-1};    
    }
}