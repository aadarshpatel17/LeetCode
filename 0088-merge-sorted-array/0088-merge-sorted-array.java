class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // insert larger element from the end of nums1 array
        // by comparing nums1 and nums2 from the end using 2 pointers
        int left = m - 1;
        int right = n - 1;
        int pos = m + n - 1;
        while (right >= 0) {
            if (left >= 0 && nums1[left] > nums2[right]) {
                nums1[pos] = nums1[left];
                left--;
                pos--;
            } else {
                nums1[pos] = nums2[right];
                right--;
                pos--;
            }
        }
    }
}