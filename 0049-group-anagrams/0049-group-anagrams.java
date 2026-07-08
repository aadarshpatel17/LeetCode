class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        int len = strs.length;

        List<List<String>> result = new ArrayList<>();
        boolean[] used = new boolean[len];

        for (int i = 0; i < len; i++) {
            List<String> anagrams = new ArrayList<>();
            if (!used[i]) {
                anagrams.add(strs[i]);
            }
            used[i] = true;
            for (int j = i + 1; j < len; j++) {
                if (!used[j] && isAnagram(strs[i], strs[j])) {
                    anagrams.add(strs[j]);
                    used[j] = true;
                }
            }
            if (!anagrams.isEmpty())
                result.add(anagrams);
        }

        return result;
    }

    public boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        boolean[] used = new boolean[b.length()];
        for (int i = 0; i < a.length(); i++) {
            boolean found = false;
            for (int j = 0; j < b.length(); j++) {
                if (!used[j] && a.charAt(i) == b.charAt(j)) {
                    used[j] = true;
                    found = true;
                    break;
                }
            }
            if (!found)
                return false;
        }

        return true;
    }
}