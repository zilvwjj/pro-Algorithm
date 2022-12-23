package com.jwan.algorithm.character;

public class CycleShift {
    private Shift shift = new Shift();

    /**
     * 依此循环
     * @param str
     * @return
     */
    public String cycleShift1Way (String str) {
        return shift.shiftByNum(str, 1);
    }

    /**
     * 字符串循环移位包含
     * @param str
     * @param demo
     * @return
     */
    public Boolean shiftContain (String str,String demo) {
        // 长度超过，注定不包含
        if (str.length() < demo.length()) return false;
        // 包含直接返回true；
        if (str.indexOf(demo) != -1) return true;

        int length = str.length(); //循环次数

        while (length > 0) {
            str = cycleShift1Way(str); // 依此循环
            if (str.indexOf(demo) != -1) return true; // 包含直接返回true；
            length --;
        }
        return false;
    }

}
