class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {

            int idx = target.charAt(i) - 'a';

            // We can continue matching target
            if (freq[idx] > 0) {
                freq[idx]--;
                prefix.append(target.charAt(i));
            } else {
                // Can't match target[i].
                // Try making the answer greater starting at i.
                return buildGreater(prefix, freq, target, i);
            }
        }

        // s can form exactly target.
        // Need STRICTLY greater, so backtrack.
        return buildGreater(prefix, freq, target, target.length() - 1);
    }

    private String buildGreater(
            StringBuilder prefix,
            int[] freq,
            String target,
            int pos) {

        // Try current position first, then backtrack.
        for (int i = pos; i >= 0; i--) {

            // If we are backtracking, return prefix[i]
            // to the available characters.
            if (i < prefix.length()) {
                int current = prefix.charAt(i) - 'a';
                freq[current]++;
                prefix.deleteCharAt(i);
            }

            int targetChar = target.charAt(i) - 'a';

            // Find the smallest character greater than target[i]
            for (int c = targetChar + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    prefix.append((char) ('a' + c));
                    freq[c]--;

                    // Append remaining characters in sorted order
                    for (int x = 0; x < 26; x++) {
                        while (freq[x] > 0) {
                            prefix.append((char) ('a' + x));
                            freq[x]--;
                        }
                    }

                    return prefix.toString();
                }
            }
        }

        return "";
    }
}