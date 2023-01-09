package com.jwan.algorithm.dichotomy;

public class DichotomyMethod {
    /**
     * 求开方?
     *
     * 给定一个非负整数，求它的开方，向下取整。
     *
     * 我们可以把这道题想象成，给定一个非负整数 a，求 f (x) = x2 − a = 0 的解。因为我们只考
     * 虑 x ≥ 0，所以 f (x) 在定义域上是单调递增的。考虑到 f (0) = −a ≤ 0， f (a) = a2 − a ≥ 0，我们
     * 可以对 [0, a] 区间使用二分法找到 f (x) = 0 的解。
     * 注意，在以下的代码里，为了防止除以 0，我们把 a = 0 的情况单独考虑，然后对区间 [1, a]
     * 进行二分查找。我们使用了左闭右闭的写法。
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;

        int l = 1 //二分法：左边界
                , r = x // 二分法：右边界
                , mid, sqrt;

        while (l <= r) { //二分法：终止条件
            mid = (l + r) / 2 ; // 二分法：取区间中间数值
            sqrt = x / mid;    //需求计算
            if (mid == sqrt) { //二分法：需求条件返回
                return sqrt;  //需求
            } else if (mid > sqrt) { //二分法：缩小区间条件判断
                r = mid - 1 ;   //无结果返回r
            } else {
                l = mid + 1 ;
            }
        }

        return r;
    }

    /**
     * 查找区间？
     *
     * 题目描述
     * 给定一个增序的整数数组和一个值，查找该值第一次和最后一次出现的位置。
     *
     * 输入是一个数组和一个值，输出为该值第一次出现的位置和最后一次出现的位置（从 0 开
     * 始）；如果不存在该值，则两个返回值都设为-1。
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     * 数字 8 在第 3 位第一次出现，在第 4 位最后一次出现。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null) return null;

        int[] index = new int[]{-1,-1};
        int l = 0 ,r = nums.length - 1, mid ;

        while (l <= r) {
            mid = (l + r ) / 2;
            if (nums[mid] == target ) {

                for (int i = mid; i >= 0 ; i--) {  //查找上半部份相同的
                    if (nums[i] == target) index[0] = i;
                }


                for (int i = mid; i < nums.length; i++) {  //查找下半部份相同的
                    if (nums[i] == target) index[1] = i;
                }
                break;
            }

            if (nums[mid] > target) {
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return index;
    }

    public int[] searchRangeReversal(int[] nums, int target) {
        if (nums == null) return null;

        int[] index = new int[]{-1,-1};
        int l = 0 ,r = nums.length - 1, mid ;

        while (l <= r) {
            mid = (l + r ) / 2;
            if (nums[mid] == target ) {

                for (int i = mid; i >= 0 ; i--) {  //查找上半部份相同的
                    if (nums[i] == target) index[0] = i;
                }


                for (int i = mid; i < nums.length; i++) {  //查找下半部份相同的
                    if (nums[i] == target) index[1] = i;
                }
                break;
            }

            if (nums[mid] < target) {
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return index;
    }

    /**
     * 旋转数组查找数字？
     *
     * 题目描述：
     * 一个原本增序的数组被首尾相连后按某个位置断开（如 [1,2,2,3,4,5] → [2,3,4,5,1,2]，在第一
     * 位和第二位断开），我们称其为旋转数组。给定一个值，判断这个值是否存在于这个为旋转数组
     * 中。
     * 输入输出样例：
     * 输入是一个数组和一个值，输出是一个布尔值，表示数组中是否存在该值。
     * Input: nums = [2,5,6,0,0,1,2], target = 0
     * Output: true
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if (target < 0 || nums == null) return false;

        int len = nums.length ;
        int l = 0 , r = len-1 ;
        int mid ;

        while (l <= r) {
            mid = (l + r) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (mid > l && nums[mid] == nums[mid-1]) { //二分法：遇到和左边相同数据的时候，左边界右移一位；
                l++;
                continue;
            }

            if (nums[mid] < target) {
                if ((nums[mid]>nums[r]) || (nums[mid]<nums[r]&&target<=nums[r])){ // 右边： target 位于一个一直递增的区间 或者 位于 一个大的递增区间
                    l = mid+1;
                }else {
                    r = mid-1;
                }
            } else {
                if ((nums[l]>nums[mid]) || (nums[mid]>nums[l]&&target>=nums[l])){// 左边： target 位于一个一直递增的区间 或者 位于 一个小的递增区间
                    r = mid - 1;
                }else {
                    l = mid + 1;
                }
            }

        }
        return false;
    }

    /**
     *  Search Insert Position？ https://leetcode.com/problems/search-insert-position/
     *
     *  Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     *
     * You must write an algorithm with O(log n) runtime complexity.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,3,5,6], target = 5
     * Output: 2
     *
     * Example 2:
     *
     * Input: nums = [1,3,5,6], target = 2
     * Output: 1
     *
     * Example 3:
     *
     * Input: nums = [1,3,5,6], target = 7
     * Output: 4
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || target < 0 ) return -1;

        int len = nums.length;
        if (target < nums[0]) return 0;   //如果比最小值还小，返回0
        if (target > nums[len-1]) return len; //如果比最大值还大，返回数组长度

        int l = 0 , r = len-1 , mid;

        while (l <= r) {
            mid = (l+r)/2;

            if (nums[mid] == target) return mid; //相同则放在相同的位置

            if (nums[mid] > target){
                if (target > nums[mid-1]){
                    return mid;    //如果target比中间值小且比前一个值大，则放在当前位置
                }else {
                    r = mid - 1;  //如果target比中间值小且比前一个值小，则右边界移到中间值的位置前面
                }
            }else {
                if (target < nums[mid+1]){
                    return mid+1;   //如果target比中间值大且比后一个值小，则放在后一个值的位置
                }else {
                    l = mid + 1;    //如果target比中间值大且比后一个值大，则左边界移到中间值的位置后面
                }
            }
        }
        return -1;
    }
}
