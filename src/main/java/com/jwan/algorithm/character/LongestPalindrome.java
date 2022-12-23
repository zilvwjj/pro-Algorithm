package com.jwan.algorithm.character;

/**
 * 计算一组字符集合可以组成的回文字符串的最大长度 ?
 *
 * 使用长度为 256 的整型数组来统计每个字符出现的个数，每个字符有偶数个可以用来构成回文字符串。
 *
 * 因为回文字符串最中间的那个字符可以单独出现，所以如果有单独的字符就把它放到最中间。
 */
public class LongestPalindrome {
    //字符映射数组
    private int [] cn = new int[256];
    //原字符串
    private String str ;
    //最长回文字符串
    private String longestPalindrome ;
    //回文字符串长度
    private int length ;
    //中间字符
    private int oddChar ;

    public LongestPalindrome(String str) {
        this.str = str;
        countChar();
        setLongestPalindrome();
    }

    /**
     * 统计字符串中字符的个数
     */
    private void countChar () {
        for ( char c : str.toCharArray()
             ) {
            cn[c] ++ ;
        }
    }

    private void setLongestPalindrome () {
        boolean oddFlag = false ; //中间值标志位
        StringBuilder stringBuilder = new StringBuilder(); //字符串

        for (int i = 0 ; i < 256 ; i ++
             ) {
            // 获取中间字符
            if(!oddFlag && cn[i] % 2 != 0){
                oddChar = i;
                oddFlag = true;
            }

            // 组成回文字符串
            if (cn[i] > 1) { //相同字符数量大于1的字符
                    int num = (cn[i]/2)*2; //获取可以组成回文字符串的字符数量
                    int lengthBuilder = stringBuilder.length();
                    if (lengthBuilder == 0) { //初始字符串长度为空直接添加
                        while (num>0){
                            stringBuilder.append((char) i);
                            num --;
                        }
                    } else { //字符串不为空插入中间位置
                        int offset = lengthBuilder / 2 ; //获取插入位置
                        while (num>0){
                            stringBuilder.insert(offset++,(char) i );
                            num --;
                        }
                    }

                }



        }
            if (oddFlag) { //有中间值就进行插入
                int lengthBuilder = stringBuilder.length();
                int offset = lengthBuilder / 2 ;
                stringBuilder.insert(offset,(char) oddChar );
            }

        longestPalindrome = stringBuilder.toString();
    }

    public int[] getCn() {
        return cn;
    }

    public String getStr() {
        return str;
    }

    public String getLongestPalindrome() {
        return longestPalindrome;
    }

    public int getLength() {
        for (int i : cn //遍历数组获取能组成回文字符串的数量
             ) {
            length += (i/2) * 2 ;
        }
        if (length < str.length()) //小于原字符串，必有中间值
            length ++;
        return length;
    }

    public int getOddChar() {
        return oddChar;
    }
}
