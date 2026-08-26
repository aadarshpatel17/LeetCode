class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();

        int left = 0;
        int oneCount = 0;

        String result = "";
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {

            // Expand window
            if (s.charAt(right) == '1') {
                oneCount++;
            }

            // Too many 1s -> shrink from left
            while (oneCount > k) {
                if (s.charAt(left) == '1') {
                    oneCount--;
                }
                left++;
            }

            // Exactly k ones -> remove unnecessary leading zeroes
            while (oneCount == k && s.charAt(left) == '0') {
                left++;
            }

            // Update answer
            if (oneCount == k) {
                String current = s.substring(left, right + 1);

                if (current.length() < minLen ||
                        (current.length() == minLen &&
                                current.compareTo(result) < 0)) {

                    result = current;
                    minLen = current.length();
                }
            }
        }

        return result;
    }
}