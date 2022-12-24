package com.jwan.algorithm.character;

/**
 * 两个字符串包含的字符是否完全相同?
 *
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * 可以用 HashMap 来映射字符与出现次数，然后比较两个字符串出现的字符数量是否相同。
 *
 * 由于本题的字符串只包含 26 个小写字符，因此可以使用长度为 26 的整型数组对字符串出现的字符进行统计，不再使用 HashMap。
 */
public class ValidAnagram {

    public Boolean isAnagram (String s, String t) {
        //使用长度为 26 的整型数组对字符串出现的字符进行统计
        int[] cnts = new int[26];

        //遍历s字符串中的字符，在对应的数组位置上+1；
        for (char c : s.toCharArray()) {
            cnts[c - 'a']++;
        }
        //遍历t字符串中的字符，在对应的数组位置上-1；
        for (char c : t.toCharArray()) {
            cnts[c - 'a']--;
        }

        //遍历数组cnt，如果有不同的字符，对应的数组位置不等于0；
        for (int cnt : cnts) {
            if (cnt != 0) {
                return false;
            }
        }
        return true;
    }
}
