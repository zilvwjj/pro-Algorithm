package com.jwan.algorithm.character;

/**
 * 字符串同构?
 *
 * Given "egg", "add", return true.
 * Given "foo", "bar", return false.
 * Given "paper", "title", return true.
 * 记录一个字符上次出现的位置，如果两个字符串中的字符上次出现的位置一样，那么就属于同构。
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        int[] preIndexOfS = new int[256];
        int[] preIndexOfT = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i), tc = t.charAt(i); //抽取字符
            if (preIndexOfS[sc] != preIndexOfT[tc]) { //判断字符存放位置是否一样
                return false;
            }
            preIndexOfS[sc] = i + 1; //存放当前字符位置数据
            preIndexOfT[tc] = i + 1;
        }
        return true;
    }
}
