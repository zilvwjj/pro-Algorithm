package com.jwan.algorithm.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CharacterMethod {

    /**
     * 最长回文串 https://leetcode.com/problems/longest-palindrome/
     * Longest Palindrome
     * Given a string s which consists of lowercase or uppercase letters, return the length of
     * the longest palindrome that can be built with those letters.
     *
     * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "abccccdd"
     * Output: 7
     * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
     * Example 2:
     *
     * Input: s = "a"
     * Output: 1
     * Explanation: The longest palindrome that can be built is "a", whose length is 1.
     *
     * 题解：哈希表
     * @param s
     * @return
     */

    public int longestPalindrome(String s) {
        if (s.length() <= 1) return s.length();
        int ans = 0;
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a'] ++;
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 1) {
                if ((chars[i] - 1) % 2 == 0){
                    ans += chars[i] -1;
                }
                if (chars[i] % 2 == 0) {
                    ans += chars[i];
                }
            }
        }

        return ans < s.length() ? ans+1 : ans;
    }

    /**
     * 无重复字符的最长子串
     * 3. Longest Substring Without Repeating Characters
     *
     * Given a string s, find the length of the longest
     * substring
     *  without repeating characters.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     *
     * Input: s = "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     *
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     *
     * 题解：a^a = 0 范围组合l&r变量(滑动窗口)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        int ans = 0;
        char[] chars = s.toCharArray();
        int l = 0 , r = 0;
        while (r < s.length()) {
            for (int i = l; i < r; i++) {
                if ((chars[i]^chars[r]) == 0) {
                    ans = Math.max(ans,r-l);
                    l = i+1;
                    r = l-1;
                }
            }
            r++;
        }
        return ans;
    }

    /**
     * 同宇符词语分组
     * 49 Group Anagrams
     *
     * Given an array of strings strs, group the anagrams together.
     * You can return the answer in any order.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
     * typically using all the original letters exactly once.
     *
     *
     *
     * Example 1:
     *
     * Input: strs = ["eat","tea","tan","ate","nat","bat"]
     * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * Example 2:
     *
     * Input: strs = [""]
     * Output: [[""]]
     * Example 3:
     *
     * Input: strs = ["a"]
     * Output: [["a"]]
     *
     * 题解：b^b = 0;
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        if (strs.length == 0) return null;
        if (strs.length == 1) {
            List<String > l = new ArrayList<>();
            l.add(strs[0]);
            ans.add(l);
            return ans;
        }

        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == null) continue;
            List<String > l = new ArrayList<>();
            l.add(strs[i]);
            for (int j = i+1; j < strs.length ; j++) {
                if (strs[j] == null) continue;
                char temp = 0;
                for (char c: (strs[i]+strs[j]).toCharArray()
                     ) {
                    temp ^= c;
                }
                if (temp == 0) {
                    l.add(strs[j]);
                    strs[j] = null;
                }
            }
            ans.add(l);
        }
        return ans;
    }

    /**
     * 重复的DNA序列
     * 187. Repeated DNA Sequences
     *
     * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
     *
     * For example, "ACGAATTCCG" is a DNA sequence.
     * When studying DNA, it is useful to identify repeated sequences within the DNA.
     *
     * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * Output: ["AAAAACCCCC","CCCCCAAAAA"]
     * Example 2:
     *
     * Input: s = "AAAAAAAAAAAAA"
     * Output: ["AAAAAAAAAA"]
     *
     * 题解：哈希表
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) return null;
        List<String> ans = new ArrayList<>();

        HashMap<String, Integer> hashBuff = new HashMap<>();
        int l = 0 ,r = 10;
        while (r <= s.length()){ //substring(l++,r++) 左闭右开
            String cur = s.substring(l++,r++);
            if (hashBuff.get(cur) != null ){
                hashBuff.put(cur,hashBuff.get(cur)+1);
                if (hashBuff.get(cur) == 2) ans.add(cur); //重复记录
            } else {
                hashBuff.put(cur,1);  //哈希表记录
            }
        }
        return ans;
    }

    /**
     * 76. Minimum Window Substring
     *
     * Given two strings s and t of lengths m and n respectively, return the minimum window
     * substring
     *  of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
     *
     * The testcases will be generated such that the answer is unique.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
     * Example 2:
     *
     * Input: s = "a", t = "a"
     * Output: "a"
     * Explanation: The entire string s is the minimum window.
     * Example 3:
     *
     * Input: s = "a", t = "aa"
     * Output: ""
     * Explanation: Both 'a's from t must be included in the window.
     * Since the largest window of s only has one 'a', return empty string.
     *
     * 题解：（频率表）哈希表 ； 范围组合l&r变量(滑动窗口)
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (t.length() > s.length() || t.length() == 0) return "";
        if (t.equals(s)) return s;
        String ans = s+s;
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int[] nums = new int[256];
        int l = 0, r = 0;
        int times = 0 , len = t.length();
        while (r < s.length()){
            for (char b: tc
                 ) {
                if ((sc[r]^b) == 0 && nums[b] == times){
                    nums[b] ++;
                    if (--len == 0){
                        ans = ans.length() > s.substring(l,r+1).length() ? s.substring(l,r+1) : ans;
                        len = t.length();
                        times++;
                        l++;
                        r = l-1;
                    }
                }
            }
            r++;
        }


        if (ans.equals(s+s)) return "";
        return ans;
    }
}
