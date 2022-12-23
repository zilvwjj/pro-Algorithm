package com.jwan.algorithm.character;

public class Shift {
    MyStringMove myStringMove = new MyStringMove();

    public String demo01(String str , int num) {
        int strLength = str.length();
        int length = num % strLength;

        String next = str.substring(0, strLength - length);
        String pre = str.substring(strLength - length, strLength);
        return pre + next;
    }

    /**
     * 通过次数num移动
     * @param str
     * @param num 次数
     * @return
     */
    public String shiftByNum (String str , int num) {
        int strLength = str.length();
        int length = num % strLength;

        // 如果不需要移动，则直接返回
        if (length == 0 ) {
            return str;
        }

        str = myStringMove.reversal(str , 0 , strLength - length -1);
        str = myStringMove.reversal(str , strLength - length , strLength-1);
        str = myStringMove.reversal(str);

        return str;
    }
}
