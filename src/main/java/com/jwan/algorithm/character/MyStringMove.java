package com.jwan.algorithm.character;

import com.sun.tools.javac.util.StringUtils;

public class MyStringMove {

    /**
     * 反转字符串
     * @param str
     * @return
     */
    public String reversal (String str ) {
        char[] chars = str.toCharArray();
        int length = chars.length;
        char temp;

        for (int i = 0; i < length/2; i++) {
            temp = chars[i] ;
            chars[i] = chars[length-i-1];
            chars[length-i-1] = temp;
        }
        return new String(chars) ;
    }

    /**
     * 字符串范围内反转
     * @param str
     * @param left
     * @param right
     * @return
     */
    public String reversal (String str , int left,int right) {
        if (left == right) return str;
        if (left > right || left >= str.length() || right >= str.length() || left < 0 || right < 0) {
            return null;
        }

        char[] chars = str.toCharArray();
        int midNum = (right - left) / 2; //反转次数
        char temp;

        for (int i = 0; i <= midNum; i++) { //反转次数
            temp = chars[i+left] ;
            chars[i+left] = chars[right - i];
            chars[right - i] = temp;
        }
        return new String(chars) ;
    }
}
