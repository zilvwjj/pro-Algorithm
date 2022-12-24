package com.jwan.algorithm.character;

/**
 * 统计二进制字符串中连续 1 和连续 0 数量相同的子字符串个数？
 *
 * Input: "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have
 * equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
 *
 */
public class CountBinarySubstrings {
    public int countBinarySubstrings(String s) {
        int preLen = 0, curLen = 1, count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) { //寻找相同字符
                curLen++;
            } else {
                preLen = curLen;    //如果不同则记录上一个字符的个数
                curLen = 1;     //记录下一个字符
            }

            if (preLen >= curLen) {  //记录当前连续相同字符串个数
                count++;
            }
        }
        return count;
    }
}
