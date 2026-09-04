class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        // create a preMax array
        int[] preMax = new int[n];
        preMax[0] = nums[0]; // 1, 1
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }

        int[] postMin = new int[n + 1];
        postMin[n - 1] = nums[n - 1]; // 1, 1
        for (int i = n - 2; i >= 0; i--) {
            postMin[i] = Math.min(postMin[i + 1], nums[i]);
        }

        // 
        int result = -1;
        for (int i = 0; i < n; i++) {
            int diff = preMax[i] - postMin[i];
            if (diff <= k) {
                result = i;
                break;
            }
        }

        return result;
    }
}