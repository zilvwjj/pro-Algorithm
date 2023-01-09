package com.jwan.algorithm.divideconquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    /**
     * 生成括号 （Different Ways to Add Parentheses）（分治、递归）
     *
     * 题目描述：
     * 给定一个只包含加、减和乘法的数学表达式，求通过加括号可以得到多少种不同的结果。
     *
     * 输入输出样例：
     * 输入是一个字符串，表示数学表达式；输出是一个数组，存储所有不同的加括号结果。
     * Input: "2-1-1"
     * Output: [0, 2]
     * 在这个样例中，有两种加括号结果：((2-1)-1) = 0 和 (2-(1-1)) = 2。
     *
     * 题解：
     * 利用分治思想，我们可以把加括号转化为，对于每个运算符号，先执行处理两侧的数学表达
     * 式，再处理此运算符号。注意边界情况，即字符串内无运算符号，只有数字。
     *
     * @param expression
     * @return
     */
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> ways = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(expression.substring(0,i));
                List<Integer> right = diffWaysToCompute(expression.substring(i+1));
                for (int l: left
                     ) {
                    for (int r: right){
                        switch (c) {
                            case '+' : ways.add(l + r);break;
                            case '-' : ways.add(l - r);break;
                            case '*' : ways.add(l * r);break;
                        }
                    }
                }
            }
        }
        if (ways.isEmpty()) ways.add(Integer.parseInt(expression));
        return ways;
    }


    /**
     * 回溯法
     *
     * Combination Sum 组合数之和 https://leetcode.com/problems/combination-sum/
     *
     * Given an array of distinct integers candidates and a target integer target,
     * return a list of all unique combinations of candidates where the chosen numbers sum to target.
     * You may return the combinations in any order.
     *
     * The same number may be chosen from candidates an unlimited number of times.
     * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
     *
     * The test cases are generated such that the number of unique combinations
     * that sum up to target is less than 150 combinations for the given input.
     *
     * Example 1:
     *
     * Input: candidates = [2,3,6,7], target = 7
     * Output: [[2,2,3],[7]]
     * Explanation:
     * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
     * 7 is a candidate, and 7 = 7.
     * These are the only two combinations.
     * Example 2:
     *
     * Input: candidates = [2,3,5], target = 8
     * Output: [[2,2,2,2],[2,3,3],[3,5]]
     * Example 3:
     *
     * Input: candidates = [2], target = 1
     * Output: []
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int
            target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length == 0) return result;
        ArrayList<Integer> current = new ArrayList<Integer>();
        Arrays.sort(candidates);
        combinationSum(candidates, target, 0, current, result);
        return result;
    }

    public void combinationSum(int[] candidates, int target, int j,
                               ArrayList<Integer> curr, List<List<Integer>> result){
        if(target == 0){
            ArrayList<Integer> temp = new ArrayList<Integer>(curr);
            result.add(temp);
            return; }
        for(int i=j; i<candidates.length; i++){
            if(target < candidates[i])
                return;
            curr.add(candidates[i]);
            combinationSum(candidates, target - candidates[i], i, curr, result);
            curr.remove(curr.size()-1);
        }
    }


    /**
     * Subsets 求子集 （回溯法）
     * Given an integer array nums of unique elements, return all possible
     * subsets
     *  (the power set).
     *
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3]
     * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [[],[0]]
     */
    List<List<Integer>> result;
    public List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList();
        if(nums==null || nums.length==0) return result;

        subsets(nums,new ArrayList<Integer>(), 0);
        return result;
    }

    private void subsets(int[] nums, ArrayList<Integer> temp, int index) {
        // base condition
        if(index >= nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        // main logic
        // case 1 : we pick the element
        temp.add(nums[index]);
        subsets(nums, temp, index+1); // move ahead
        temp.remove(temp.size()-1);

        // case 2 : we don't pick the element ( notice, we did not add the current element in our temporary list
        subsets(nums, temp, index+1); // move ahead
    }


    /**
     * 回溯法：
     * n皇后（n：1～9）
     *
     * 题目描述：
     * 给定一个大小为 n 的正方形国际象棋棋盘，求有多少种方式可以放置 n 个皇后并使得她们互
     * 不攻击，即每一行、列、左斜、右斜最多只有一个皇后。
     *
     * 输入输出样例：
     * 输入是一个整数 n，输出是一个二维字符串数组，表示所有的棋盘表示方法。
     * Input: 4
     * Output: [
     * [".Q..", // Solution 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * ["..Q.", // Solution 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     * 在这个样例中，点代表空白位置，Q 代表皇后。
     *
     * 题解：
     * 类似于在矩阵中寻找字符串，本题也是通过修改状态矩阵来进行回溯。不同的是，我们需要
     * 对每一行、列、左斜、右斜建立访问数组，来记录它们是否存在皇后。
     * 本题有一个隐藏的条件，即满足条件的结果中每一行或列有且仅有一个皇后。这是因为我们
     * 一共只有 n 行和 n 列。所以如果我们通过对每一行遍历来插入皇后，我们就不需要对行建立访问
     * 数组了。
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> Container = new ArrayList<>(); //结果容器

        //棋盘初始化=========================================(
        List<String> chessboard = new ArrayList<>(); //棋盘
        String s = "" ;
        for (int j = 0; j < n; j++) {
            s += ".";
        }
        for (int i = 0; i < n; i++) {
            chessboard.add(s);
        }
        //棋盘初始化=========================================)

        //赋值标记=============================================================(
        int row = 0; // 行标记
        Boolean[] column = new Boolean[n],  //列标记
                ldiag = new Boolean[2*n-1], // 配合容量2*n-1条斜线，左斜线标记 row+column;
                rdiag = new Boolean[2*n-1]; // 配合容量2*n-1条斜线，右斜线标记 row-column+target-1;
        for (int i = 0; i < 2*n-1; i++) {
            if (i < n) column[i] = false;
            ldiag[i] = rdiag[i] = false;
        }
        //赋值标记=============================================================)


        findPosition(n,row,column,ldiag,rdiag,chessboard,Container);
        return Container;
    }
    public List<List<String>> findPosition( int target,
                                            int row ,Boolean[] column,Boolean[] ldiag,Boolean[] rdiag,
                                            List<String> chessboard,List<List<String>> Container){

        //如果能放齐，就加入结果集；
        if (target == row) {
            Container.add(new ArrayList<>(chessboard));
            return Container;
        }

        for (int i = 0; i < target; i++) { //column
            if (column[i] ||ldiag[i+row] || rdiag[row-i-1+target]) continue; //随便一个是true，证明这个位置不适合放置。

            //能放置，就标记上
            column[i] = true ;ldiag[i+row] = true ; rdiag[row-i-1+target] = true;

            //换掉对应位置的字符串
            String cs = "";
            for (int j = 0; j < target; j++) {
                if (j == row) cs += "Q";
                else cs += ".";
            }
            chessboard.set(i,cs);

            //放置下一个
            findPosition(target,row+1,column,ldiag,rdiag,chessboard,Container);

            //回溯，还原原来的字符串
            String s = "" ;
            for (int j = 0; j < target; j++) {
                s += ".";
            }
            chessboard.set(i,s);

            //回溯，取消标记
            column[i] = false ;
            ldiag[i+row] = false ;
            rdiag[row-i-1+target] = false;
        }

        return Container;
    }
}
