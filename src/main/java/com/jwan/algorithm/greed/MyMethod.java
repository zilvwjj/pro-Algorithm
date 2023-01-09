package com.jwan.algorithm.greed;

import com.sun.tools.javac.util.StringUtils;

import java.util.Stack;

public class MyMethod {
    /**
     * Can Place Flowers
     *
     * You have a long flowerbed in which some of the plots are planted, and some are not. However,
     * flowers cannot be planted in adjacent plots.
     *
     * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty,
     * and an integer n,
     * return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int range = flowerbed.length;
        int num = 0;

        for (int i = 0; i < range; i++) {
            if (i==0 && i+1 < range) { //头种花
                if (flowerbed[i] == flowerbed[++i]) {
                    flowerbed[i] = 1;
                    num ++;
                }
                continue;
            }
            if (i==range-2) { //尾种花
                if (flowerbed[i] == flowerbed[++i]) {
                    flowerbed[i] = 1;
                    num ++;
                }
                continue;
            }
            if (flowerbed[i]==0 && flowerbed[i-1] == 0 && flowerbed[i+1] == 0) {
                { //中间种花
                    flowerbed[i] = 1;
                    num++;
                }
            }
        }

        return num >= n;

    }

    /**
     * candy
     *
     * There are n children standing in a line.
     * Each child is assigned a rating value given in the integer array ratings.
     *
     * You are giving candies to these children subjected to the following requirements:
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     *
     * Return the minimum number of candies you need to have to distribute the candies to the children.
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        if (ratings == null) return 0;
        int num = 0;
        num = ratings.length; //没人至少一颗

        for (int i = 0; i < ratings.length-1; i++) { //从左向右遍历，右边的人比自己高分就多一颗
            if (ratings[i] > ratings[i+1]) num ++;
        }

        for (int i = ratings.length-1; i > 0; i--) { //从右向左遍历，左边的人比自己高分就多一颗
            if (ratings[i] > ratings[i-1]) num ++;
        }
        return num;
    }

    /**
     * JumpGame
     *
     * Given an array of non-negative integers, you are initially positioned at the first index
     * of the array. Each element in the array represents your maximum jump length at that
     * position. Determine if you are able to reach the last index. For example: A = [2,3,1,1,4],
     * return true. A = [3,2,1,0,4], return false.
     *
     * @param arrays
     * @return
     */
    public Boolean JumpGame (int[] arrays) {

        if (arrays.length <= 1 ) return true;

        int max = arrays[0]+0; //可走的最大步数

        for (int i = 0; i < arrays.length; i++) {
            if (max < i ) return false; //如果可走最大步数到达不了当前区域，就失败

            if (max < i+arrays[i]) max = i+arrays[i];  //更新最大步数

            if (max >= arrays.length-1) return true; //如果最大步数超出或等于当前区域，则成功
        }

        return false;
    }

    /**
     * Remove K Digits
     *
     * Given string num representing a non-negative integer num, and an integer k,
     * return the smallest possible integer after removing k digits from num.
     *
     * @param num
     * @param k
     * @return
     */

    public String removeKdigits(String num, int k) {
        if (k < 1) return num;
        if (num.length() == k) return "0";  //长度和要删除的数量相等直接返回0
        StringBuilder stringBuilder = new StringBuilder();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < num.length(); i++) {

            if (stack.size() != 0 && stack.peek() >= num.charAt(i) && k > 0) { //在栈中一开始有数据时，比较k次；
                stack.pop();                                   //如果栈中的数比要入栈的数大或相等时，直接出栈
                stack.push(num.charAt(i));                      //压入新的数
                k--;
            } else {

                stack.push(num.charAt(i));   //k次过后直接入栈
            }

        }

        while (!stack.isEmpty()) { //组成字符串
            if (k>0) { //特例：1234 k=1；   如果字符串是升序排列，上面的for循环就不能把后面的数字除掉
                stack.pop();
                k--;
            }else {

                stringBuilder.append(stack.pop());
            }
        }
        stringBuilder.reverse(); //反转

        while (stringBuilder.charAt(0) == '0' && stringBuilder.length() > 1) { //去除前面为'0'的字符（除了最后一个）
            stringBuilder.deleteCharAt(0);
        }

        return stringBuilder.toString();
    }

    /**
     * Gas Station
     *
     * https://leetcode.com/problems/gas-station/description/
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int leftGas = 0;

        //如果汽车各个油站之间消耗的总油量大于或等于0，则可以循环一圈
        for (int i = 0; i < gas.length ; i++) {
            leftGas +=gas[i] - cost[i] ;
        }

        if (leftGas < 0) return -1;

        //如果循环一周，汽车经过油站所剩下的油量都为正值时，首先剩下油量为正值的油站为开始点。

        int start = 0;
        int accumulate = 0;
        for (int i = 0; i < gas.length; i++) {
            leftGas = gas[i] - cost[i] ;

            if (accumulate + leftGas > 0) {
                accumulate = accumulate + leftGas;
            } else {
                start = i + 1;
                accumulate = 0;
            }
        }


        return start;
    }

}
