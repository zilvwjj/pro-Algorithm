package com.jwan.algorithm.dynamicprogramming;

import java.util.List;

public class DynamicProgramming {

    /**
     * 爬楼梯（一维）
     *
     * 题目描述：
     * 给定 n 节台阶，每次可以走一步或走两步，求一共有多少种方式可以走完这些台阶。
     * 输入输出样例：
     * 输入是一个数字，表示台阶数量；输出是爬台阶的总方式。
     * Input: 3
     * Output: 3
     *
     * 在这个样例中，一共有三种方法走完这三节台阶：每次走一步；先走一步，再走两步；先走
     * 两步，再走一步。
     * 题解：
     * 这是十分经典的斐波那契数列题。定义一个数组 dp，dp[i] 表示走到第 i 阶的方法数。因为
     * 我们每次可以走一步或者两步，所以第 i 阶可以从第 i-1 或 i-2 阶到达。换句话说，走到第 i 阶的
     * 方法数即为走到第 i-1 阶的方法数加上走到第 i-2 阶的方法数。这样我们就得到了状态转移方程
     * dp[i] = dp[i-1] + dp[i-2]。注意边界条件的处理。
     *
     * 斐波那契数 1 1 2 3 5 8 13 21
     * @param n
     * @return
     */
    public int climbStairs (int n) {
        if (n <= 2) return n;
        int[] dp = new int[n+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2] ;
        }
        return dp[n];

    }

    /**
     * 进一步的，我们可以对动态规划进行空间压缩。因为 dp[i] 只与 dp[i-1] 和 dp[i-2] 有关，因此
     * 可以只用两个变量来存储 dp[i-1] 和 dp[i-2]，使得原来的 O(n) 空间复杂度优化为 O(1) 复杂度。
     * @param n
     * @return
     */

    public int climbStairs2 (int n) {
        if (n <= 2) return n;
        int pre2 = 1, pre1 = 2, cur = 0;
        for (int i = 2; i < n; i++) {
            cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }

    /**
     * 最小路径和（二维）
     *
     * 题目描述：
     * 给定一个 m × n 大小的非负整数矩阵，求从左上角开始到右下角结束的、经过的数字的和最
     * 小的路径。每次只能向右或者向下移动。
     * 
     * 输入输出样例：
     * 输入是一个二维数组，输出是最优路径的数字和。
     * Input:
     * [[1,3,1],
     * [1,5,1],
     * [4,2,1]]
     * Output: 7
     * 在这个样例中，最短路径为 1->3->1->1->1。
     * 
     * 题解：
     * 我们可以定义一个同样是二维的 dp 数组，其中 dp[i][j] 表示从左上角开始到 (i, j) 位置的最
     * 优路径的数字和。因为每次只能向下或者向右移动，我们可以很容易得到状态转移方程 dp[i][j] =
     * min(dp[i-1][j], dp[i][j-1]) + grid[i][j]，其中 grid 表示原数组。
     * 
     * @param grid
     * @return
     */
    public int minPathSum (int[][] grid) {
        int m = grid.length , n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) { //起点
                    dp[i][j] = grid[i][j];
                } else if (i == 0) { //沿着上边
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                } else if (j == 0) { //沿着左边
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                } else { //中间
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }


    /**
     * 最长递增子序列 Longest Increasing Subsequence (Medium) （自序列问题）
     *
     * 题目描述： 给定一个未排序的整数数组，求最长的递增子序列。
     * 注意：按照 LeetCode 的习惯，子序列（subsequence）不必连续，子数组（subarray）或子字符串
     * （substring）必须连续。
     *
     * 输入输出样例：
     * 输入是一个一维数组，输出是一个正整数，表示最长递增子序列的长度。
     * Input: [10,9,2,5,3,7,101,18]
     * Output: 4
     * 在这个样例中，最长递增子序列之一是 [2,3,7,18]。
     *
     * 题解：
     * 对于子序列问题，第一种动态规划方法是，定义一个 dp 数组，其中 dp[i] 表示以 i 结尾的子
     * 序列的性质。在处理好每个位置后，统计一遍各个位置的结果即可得到题目要求的结果。
     * 在本题中，dp[i] 可以表示以 i 结尾的、最长子序列长度。对于每一个位置 i，如果其之前的某
     * 个位置 j 所对应的数字小于位置 i 所对应的数字，则我们可以获得一个以 i 结尾的、长度为 dp[j]
     * + 1 的子序列。为了遍历所有情况，我们需要 i 和 j 进行两层循环，其时间复杂度为 O(n2)。
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS (int[] nums) {
        int maxLength = 0 , n = nums.length;
        if (n <= 1) return n;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) { //当前的值和之前的值逐一比较
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            maxLength = Math.max(dp[i],maxLength);
        }
        return maxLength;
    }


    /**
     * 找零钱（背包问题）
     *
     * 题目描述：
     * 给定一些硬币的面额，求最少可以用多少颗硬币组成给定的金额。
     *
     * 输入输出样例：
     * 输入一个一维整数数组，表示硬币的面额；以及一个整数，表示给定的金额。输出一个整数，
     * 表示满足条件的最少的硬币数量。若不存在解，则返回-1。
     * Input: coins = [1, 2, 5], amount = 11
     * Output: 3
     * 在这个样例中，最少的组合方法是 11 = 5 + 5 + 1。
     *
     * 题解：
     * 因为每个硬币可以用无限多次，这道题本质上是完全背包。我们直接展示二维空间压缩为一
     * 维的写法。
     * 这里注意，我们把 dp 数组初始化为 amount + 2 而不是-1 的原因是，在动态规划过程中有求
     * 最小值的操作，如果初始化成-1 则会导致结果始终为-1。至于为什么取这个值，是因为 i 最大可
     * 以取 amount + 1，而最多的组成方式是只用 1 元硬币，因此 amount + 2 一定大于所有可能的组合
     * 方式，取最小值时一定不会是它。在动态规划完成后，若结果仍然是此值，则说明不存在满足条
     * 件的组合方法，返回-1。
     * @param coins
     * @param amount
     * @return
     */
    int coinChange(int[] coins, int amount) {
        if (coins == null) return -1;
        int[] dp = new int[amount+1] ;
        for (int i = 0; i < amount; i++) {
            dp[i+1] = amount+2;
        }

        for (int i = 1; i <= amount; i++) {
            for (int coin: coins
                 ) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }

        return dp[amount] == amount + 2 ? -1: dp[amount];
    }

    /**
     * 最大字段和 （https://leetcode.com/problems/maximum-subarray/description/）（一维）
     * Given an integer array nums, find the
     * subarray
     *  with the largest sum, and return its sum.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6
     * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
     * Example 2:
     *
     * Input: nums = [1]
     * Output: 1
     * Explanation: The subarray [1] has the largest sum 1.
     * Example 3:
     *
     * Input: nums = [5,4,-1,7,8]
     * Output: 23
     * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE, sum = 0;

        for(int i=0;i<n;i++){
            sum += nums[i];
            max = Math.max(sum,max);

            if(sum<0) sum = 0; //为负数时，单单一个数已经和最大
        }

        return max;
    }

    /**
     * 三角形 （https://leetcode.com/problems/triangle/）（二维）
     *
     * Given a triangle array, return the minimum path sum from top to bottom.
     *
     * For each step, you may move to an adjacent number of the row below. More formally,
     * if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
     *
     *
     *
     * Example 1:
     *
     * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
     * Output: 11
     * Explanation: The triangle looks like:
     *    2
     *   3 4
     *  6 5 7
     * 4 1 8 3
     * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
     * Example 2:
     *
     * Input: triangle = [[-10]]
     * Output: -10
     * @param triangle
     * @return
     */

    public int minimumTotal(List<List<Integer>> triangle) { //由下往上计算最小值
        for (int i = triangle.size()-2; i >= 0 ; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j,Math.min(triangle.get(i+1).get(j),triangle.get(i+1).get(j+1))+
                        triangle.get(i).get(j));
            }
        }
        return triangle.get(0).get(0);
    }

    public int minimumTotal2(List<List<Integer>> triangle) { // 由上往下计算最小值
        int row = triangle.size();
        if (row <=1 ) return triangle.get(0).get(0);
        int[][] minSum = new int[row][row];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == 0 && j == 0) minSum[i][j] = triangle.get(i).get(j);
                if (i == 1 ) minSum[i][j] = minSum[0][0] + triangle.get(i).get(j);
                if (i > 1 ) {
                    if (j == 0) minSum[i][j] = minSum[i-1][j] + triangle.get(i).get(j);
                    else if (j == triangle.get(i).size()-1)
                        minSum[i][j] = minSum[i-1][j-1] + triangle.get(i).get(j);
                    else
                    minSum[i][j] = Math.min(minSum[i-1][j-1],minSum[i-1][j]) + triangle.get(i).get(j);

                }
            }
        }
        for (int i = 0; i < row; i++) {
            min = Math.min(min,minSum[row-1][i]);
        }
        return min;
    }
}
