class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        int len = strs.length;

        List<List<String>> result = new ArrayList<>();
        boolean[] used = new boolean[len];

        for (int i = 0; i < strs.length; i++) {
            if (used[i]) {
                continue;
            }

            List<String> anagrams = new ArrayList<>();
            anagrams.add(strs[i]);
            used[i] = true;
            for (int j = i + 1; j < len; j++) {
                if (!used[j] && isAnagram_optimized(strs[i], strs[j])) {
                    anagrams.add(strs[j]);
                    used[j] = true;
                }
            }
            result.add(anagrams);
        }

        return result;
    }

    public boolean isAnagram_optimized(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        return sortString(a).equals(sortString(b));
    }

    public String sortString(String str) {
        char[] ch = str.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
    }
}