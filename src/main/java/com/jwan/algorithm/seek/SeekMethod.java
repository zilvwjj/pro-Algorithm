package com.jwan.algorithm.seek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SeekMethod {

    /**
     * 深度优先搜索
     * Max Area of Island (Easy)?
     *
     * 题目描述
     * 给定一个二维的 0-1 矩阵，其中 0 表示海洋，1 表示陆地。单独的或相邻的陆地可以形成岛
     * 屿，每个格子只与其上下左右四个格子相邻。求最大的岛屿面积。
     *
     * 输入输出样例
     * 输入是一个二维数组，输出是一个整数，表示最大的岛屿面积。
     * Input:
     * [[1,0,1,1,0,1,0,1],
     * [1,0,1,1,0,1,1,1],
     * [0,0,0,0,0,0,0,1]]
     * Output: 6
     * 最大的岛屿面积为 6，位于最右侧
     *
     * @param map
     * @return
     */
    public int maxAreaOfIsland (int[][] map) {
        int [] direction = new int[] {-1,0,1,0,-1}; // 移动方向
        int x , y ; //坐标
        int column = map[0].length, row = map.length; //地图范围
        int maxArea = 0 ,curArea = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (map[i][j] == 1) {
                    curArea = 1;
                    map[i][j] = 0 ; //标记为已记录；
                    Stack<int[]> land= new Stack<>();
                    land.push(new int[]{i,j});  //i <=> row <=> y ; j <=> column<=> x
                    while (!land.isEmpty()){
                        int[] curCoordinate = land.pop();
                        for (int k = 0; k < 4; k++) {
                            x = curCoordinate[1] + direction[k] ; //j <=> column<=> x
                            y = curCoordinate[0] + direction[k+1]; //i <=> row <=> y
                            if (x >=0 && x < column &&   //j <=> column<=> x
                                y >=0 && y < row &&  //i <=> row <=> y
                                map[y][x] == 1) {
                                map[y][x] = 0;
                                curArea++;
                                land.push(new int[]{y,x}); //i <=> row <=> y ; j <=> column<=> x
                            }

                        }
                    }
                }
                maxArea = (maxArea > curArea) ? maxArea : curArea;
            }

        }

        return maxArea;
    }

    /**
     * 46. Permutations (Medium) （回溯法）
     * 题目描述：
     * 给定一个无重复数字的整数数组，求其所有的排列方式。
     *
     * 输入输出样例：
     * 输入是一个一维整数数组，输出是一个二维数组，表示输入数组的所有排列方式。
     * Input: [1,2,3]
     * Output: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
     * 可以以任意顺序输出，只要包含了所有排列方式即可。
     *
     * 题解：
     * 怎样输出所有的排列方式呢？对于每一个当前位置 i，我们可以将其于之后的任意位置交换，
     * 然后继续处理位置 i+1，直到处理到最后一位。为了防止我们每此遍历时都要新建一个子数组储
     * 存位置 i 之前已经交换好的数字，我们可以利用回溯法，只对原数组进行修改，在递归完成后再
     * 修改回来。
     * 我们以样例[1,2,3]为例，按照这种方法，我们输出的数组顺序为[[1,2,3], [1,3,2], [2,1,3], [2,3,1],
     * [3,1,2], [3,2,1]]，可以看到所有的排列在这个算法中都被考虑到了。
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return null;
        ArrayList<Integer> numList = new ArrayList<>();
        for (int n: nums
             ) {
            numList.add(n);
        }
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(numList,0,ans);
        System.out.println(numList);
        return ans;
    }

    private void backtracking(ArrayList<Integer> numList, int level, List<List<Integer>> ans) {
        if (level == numList.size()-1){
            ans.add(new ArrayList<>(numList));
            return;
        }
        for (int i = level; i < numList.size(); i++) {
            int temp = numList.get(i);
            numList.set(i,numList.get(level));
            numList.set(level,temp);
            backtracking(numList,level+1,ans);
            temp = numList.get(i);
            numList.set(i,numList.get(level));
            numList.set(level,temp);
        }

    }



}
