package com.jwan.algorithm.character;

/**
 * 判断一个整数是否是回文数?
 *
 * 要求不能使用额外空间，也就不能将整数转换为字符串进行判断。
 *
 * 将整数分成左右两部分，右边那部分需要转置，然后判断这两部分是否相等。
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x == 0) {//整数等于0，直接返回false
            return true;
        }
        if (x < 0 || x % 10 == 0) { //整数小于0或者为个位数，直接返回false
            return false;
        }
        int right = 0;
        while (x > right) { //将整数分为两个部份
            right = right * 10 + x % 10; //抽出x个位数组成right的个位数
            x /= 10; //去除个位数
        }
        return x == right || x == right / 10; //如果原整数位数为偶数x==right，如果原整数位数为奇数x==right/10；
    }
}
