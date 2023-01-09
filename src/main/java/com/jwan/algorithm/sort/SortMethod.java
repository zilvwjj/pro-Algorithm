package com.jwan.algorithm.sort;

import java.util.Stack;

public class SortMethod {

    /**
     * 快速排序
     *
     * 取第一位数为key(把key放到正确的位置)
     * 比key小的排去左边，比key大的排去右边，此时的位置就是key要待的位置，
     * 按key值位置分开进行递归
     * 递归剩下一位数直接返回
     *
     * @param nums
     * @param l
     * @param r
     */
    public void quictSort (int[] nums , int l , int r) {
        if (l + 1 >= r) { //只有一位数直接返回
            return; }
        int first = l, last = r - 1, key = nums[first];
        while (first < last){  //位置重制时，退出循环
            while(first < last && nums[last] >= key) { //寻找区间比key的数还小的数
                --last;
            }
            nums[first] = nums[last]; //将数赋给first的位置
            while (first < last && nums[first] <= key) { //寻找区间比key的数还大的数
                ++first;
            }
            nums[last] = nums[first]; //将数赋给当前last的位置
        }
        nums[first] = key; //key值归位
        quictSort(nums, l, first);
        quictSort(nums, first + 1, r);
    }

    /**数组中的第K大的数
     * 快速选择？
     *
     * 题目描述
     * 在一个未排序的数组中，找到第 k 大的数字。
     * 输入输出样例
     * 输入一个数组和一个目标值 k，输出第 k 大的数字。题目默认一定有解。
     * Input: [3,2,1,5,6,4] and k = 2
     * Output: 5
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums.length < k || nums==null||k<1) return -1;
        int l = 0 ,r = nums.length,index = -1 , target = nums.length - k;
        while (l < r) {
            index = getQuictSortOnceIndex(nums, l, r); //快速排序一次
            if (index == target) return nums[index]; //如果位置对应，则返回
            if (index < target) {
                l = index + 1;
            }else {
                r = index - 1;
            }
        }
        return -1;
    }

    public int getQuictSortOnceIndex (int[] nums ,int l,int r){
        if (l + 1 >= r) { //只有一位数直接返回
            return l; }
        int first = l, last = r - 1, key = nums[first];
        while (first < last){  //位置重制时，退出循环
            while(first < last && nums[last] >= key) { //寻找区间比key的数还小的数
                --last;
            }
            nums[first] = nums[last]; //将数赋给first的位置
            while (first < last && nums[first] <= key) { //寻找区间比key的数还大的数
                ++first;
            }
            nums[last] = nums[first]; //将数赋给当前last的位置
        }
        nums[first] = key; //key值归位

        return first;
    }
    /**
     * 冒泡排序
     *
     * 遍历一次最后一位数值之前的数值，如果当前数值比后一个数值大则交换两者的数值；
     * 之后遍历倒数第二位之前的数值，倒数第三位之前的数值······
     * 如此循环，直到第一位。
     * @param nums
     */
    public void bubbleSort (int[] nums) {
        if (nums==null) return;

        System.out.println("=========bubbleSort=========");

        int len = nums.length;

        for (int j = 0; j < len-1; j++) {
            for (int i = 0; i < len-1-j; i++) {
                if (nums[i] > nums[i+1]){
                    int temp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = temp;
                }
            }

        }

    }


}
