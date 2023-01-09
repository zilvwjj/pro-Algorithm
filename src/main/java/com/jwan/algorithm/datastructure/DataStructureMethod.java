package com.jwan.algorithm.datastructure;

import java.util.*;

public class DataStructureMethod {

    /**
     * (数组)
     * Find All Numbers Disappeared in an Array (Easy)
     * 题目描述:
     * 给定一个长度为 n 的数组，其中包含范围为 1 到 n 的整数，有些整数重复了多次，有些整数 没有出现，求 1 到 n 中没有出现过的整数。
     *
     * 输入输出样例:
     *   输入是一个一维整数数组，输出也是一个一维整数数组，表示输入数组内没出现过的数字。
     * Input: [4,3,2,7,8,2,3,1]
     * Output: [5,6]
     *
     * 题解:
     * 利用数组这种数据结构建立 n 个桶，把所有重复出现的位置进行标记，然后再遍历一遍数组，
     * 即可找到没有出现过的数字。进一步地，我们可以直接对原数组进行标记:把重复出现的数字在
     * 原数组出现的位置设为负数，最后仍然为正数的位置即为没有出现过的数。
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int pos = Math.abs(nums[i]) - 1;
            if (nums[pos] > 0) {
                nums[pos] = - nums[pos];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ans.add(i+1);
            }
        }

        return ans;
    }

    /**
     * Rotate Image (Medium)
     * 题目描述:
     * 给定一个 n × n 的矩阵，求它顺时针旋转 90 度的结果，且必须在原矩阵上修改(in-place)。 怎样能够尽量不创建额外储存空间呢?
     * 输入输出样例
     *   输入和输出都是一个二维整数矩阵。
     * Input:
     * [[1,2,3],
     * [4,5,6],
     *  [7,8,9]]
     * Output:
     * [[7,4,1],
     *  [8,5,2],
     *  [9,6,3]]
     * 题解
     * 每次只考虑四个间隔 90 度的位置，可以进行 O(1) 额外空间的旋转。
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i <= n/2-1; i++) {
            for (int j = i; j < n-2*i-1+i; j++) {
                int temp = matrix[i][j];
                matrix[i][j]         = matrix[n-1-j][i];
                matrix[n-1-j][i]     = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i]     = temp;
            }
        }
    }

    /**
     * Search a 2D Matrix II (Medium)
     * 题目描述:
     * 给定一个二维矩阵，已知每行和每列都是增序的，尝试设计一个快速搜索一个数字是否在矩 阵中存在的算法。
     * 输入输出样例:
     * 输入是一个二维整数矩阵，和一个待搜索整数。输出是一个布尔值，表示这个整数是否存在 于矩阵中。
     * Input: matrix =
     * [ [1, 4, 7, 11, 15],
     * [2, 5, 8, 12, 19],
     * [3, 6, 9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]], target = 5
     * Output: true
     * 题解:
     * 这道题有一个简单的技巧:我们可以从右上角开始查找，若当前值大于待搜索值，我们向左
     * 移动一位;若当前值小于待搜索值，我们向下移动一位。如果最终移动到左下角时仍不等于待搜
     * 索值，则说明待搜索值不存在于矩阵中。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length , column = matrix[0].length;

//        for (int i = 0; i < row; i++) {
//            for (int j = column-1; j >=0 ; j--) {
//                if (target > matrix[i][j]) break;
//                if (target == matrix[i][j]) return true;
//            }
//        }

        int i = 0 , j = column-1;
        while (i < row && j >= 0 ) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] > target) j--;
            else i++;
        }
        return false;
    }

    /**
     * Max Chunks To Make Sorted (Medium)
     *
     * 题目描述:
     * 给定一个含有 0 到 n 整数的数组，每个整数只出现一次，求这个数组最多可以分割成多少个 子数组，
     * 使得对每个子数组进行增序排序后，原数组也是增序的。
     *
     * 输入输出样例:
     *   输入一个一维整数数组，输出一个整数，表示最多的分割数。
     * Input: [1,0,2,3,4]
     * Output: 4
     * 在这个样例中，最多分割是 [1, 0], [2], [3], [4]。
     *
     * 题解:
     * 从左往右遍历，同时记录当前的最大值，每当当前最大值等于数组位置时，我们可以多一次 分割。
     * 为什么可以通过这个算法解决问题呢?如果当前最大值大于数组位置，则说明右边一定有小 于数组位置的数字，
     * 需要把它也加入待排序的子数组;又因为数组只包含不重复的 0 到 n，所以
     * 当前最大值一定不会小于数组位置。所以每当当前最大值等于数组位置时，
     * 假设为 p，我们可以 成功完成一次分割，并且其与上一次分割位置 q 之间的值一定是 q + 1 到 p 的所有数字。
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int ans = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i],max);
            if ( max == i) {
                ans++;
            }

        }
        return ans;
    }


//============栈 队列=======================================================================================

    /** 使用栈实现队列
     * Implement Queue using Stacks (Easy)
     * 题目描述:
     * 尝试使用栈(stack)来实现队列(queue)。
     * 输入输出样例:
     *   以下是数据结构的调用样例。
     * MyQueue queue = new MyQueue(); queue.push(1);
     * queue.push(2);
     * queue.peek(); // returns 1
     * queue.pop(); // returns 1
     * queue.empty(); // returns false
     * 题解:
     * 我们可以用两个栈来实现一个队列:
     * 因为我们需要得到先入先出的结果，所以必定要通过一
     * 个额外栈翻转一次数组。这个翻转过程既可以在插入时完成，也可以在取值时完成。
     */
    class MyQueue {
    Stack<Integer> in ,out;
    public MyQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        in2out();
        return out.pop();
    }

    public int peek() {
        in2out();
        return out.peek();
    }

    private void in2out(){
        if (out.isEmpty()){
            while (!in.isEmpty()){
                out.push(in.pop());
            }
        }
    }

    public boolean empty() {
        return in.isEmpty()&& out.empty();
    }
}

    /**包含min函数的桟
     * Min Stack (Easy)
     * 题目描述
     * 设计一个最小栈，除了需要支持常规栈的操作外，还需要支持在 O(1) 时间内查询栈内最小 值的功能。
     * 输入输出样例
     *   以下是数据结构的调用样例。
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin(); // Returns -3.
     * minStack.pop();
     * minStack.top();   // Returns 0.
     * minStack.getMin(); // Returns -2.
     * 题解
     * 我们可以额外建立一个新栈，栈顶表示原栈里所有值的最小值。每当在原栈里插入一个数字
     * 时，若该数字小于等于新栈栈顶，则表示这个数字在原栈里是最小值，我们将其同时插入新栈内。
     * 每当从原栈里取出一个数字时，若该数字等于新栈栈顶，则表示这个数是原栈里的最小值之一，
     * 我们同时取出新栈栈顶的值。
     * 一个写起来更简单但是时间复杂度略高的方法是，
     * 我们每次插入原栈时，都向新栈插入一次 原栈里所有值的最小值(新栈栈顶和待插入值中小的那一个);每次从原栈里取出数字时，同样
     * 取出新栈的栈顶。这样可以避免判断，但是每次都要插入和取出。我们这里只展示第一种写法。
     */
    class MinStack {
        Stack<Integer> data , min_data;
        public MinStack() {
            data = new Stack<>();
            min_data = new Stack<>();
        }

        public void push(int val) {
            if (min_data.isEmpty()||min_data.peek() >= val) {
                min_data.push(val);
            }
            data.push(val);
        }

        public void pop() {
            if (min_data.peek() == data.peek()){
                data.pop();
                min_data.pop();
            }else {
                data.pop();
            }
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return min_data.peek();
        }
    }

    /**
     * Valid Parentheses (Easy)
     * 题目描述:
     * 给定一个只由左右原括号、花括号和方括号组成的字符串，求这个字符串是否合法。合法的 定义是每一个类型的左括号都有一个右括号一一对应，
     * 且括号内的字符串也满足此要求。
     * 输入输出样例:
     *   输入是一个字符串，输出是一个布尔值，表示字符串是否合法。
     * Input: "{[]}()"
     * Output: true
     * 题解:
     * 括号匹配是典型的使用栈来解决的问题。我们从左往右遍历，每当遇到左括号便放入栈内， 遇到右括号则判断其和栈顶的括号是否是统一类型，
     * 是则从栈内取出左括号，否则说明字符串不 合法。
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> temp = new Stack<>();
        for (char c: s.toCharArray()
             ) {
            if (c == '{' || c == '(' || c == '[')
                temp.push(c);
            else {
                if (temp.isEmpty()) return false;
                switch (c) {
                    case ')':
                        if (temp.peek()=='(') temp.pop();
                        else return false;
                        break;
                    case '}':
                        if (temp.peek()=='{') temp.pop();
                        else return false;
                        break;
                    case ']':
                        if (temp.peek()=='[') temp.pop();
                        else return false;
                        break;
                }
            }
        }

        return temp.isEmpty();
    }

    /**
     * Daily Temperatures (Medium)
     * 题目描述:
     * 给定每天的温度，求对于每一天需要等几天才可以等到更暖和的一天。如果该天之后不存在 更暖和的天气，则记为 0。
     * 输入输出样例:
     * 输入是一个一维整数数组，输出是同样长度的整数数组，表示对于每天需要等待多少天。
     * Input: [73, 74, 75, 71, 69, 72, 76, 73]
     * Output: [1, 1, 4, 2, 1, 1, 0, 0]
     * 题解:
     * 我们可以维持一个单调递减的栈，表示每天的温度;为了方便计算天数差，我们这里存放位 置(即日期)而非温度本身。
     * 我们从左向右遍历温度数组，对于每个日期 p，如果 p 的温度比栈 顶存储位置 q 的温度高，则我们取出 q，
     * 并记录 q 需要等待的天数为 p − q;我们重复这一过程， 直到 p 的温度小于等于栈顶存储位置的温度(或空栈)时，
     * 我们将 p 插入栈顶，然后考虑下一天。 在这个过程中，栈内数组永远保持单调递减，避免了使用排序进行比较。最后若栈内剩余一些日
     * 期，则说明它们之后都没有出现更暖和的日期。
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Stack<Integer> data = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {

            if (data.isEmpty() || temperatures[i] <= temperatures[data.peek()]) {
                data.push(i);
            }else {
                while (!data.isEmpty() && temperatures[i] > temperatures[data.peek()]){

                        int d = data.pop();
                        ans[d] = i - d;

                }
                data.push(i);
            }
        }

        while (!data.isEmpty()) {
            ans[data.pop()] = 0 ;
        }
        return ans;
    }

//    使用队列实现栈 https://leetcode.com/problems/implement-stack-using-queues/
class MyStack {
        private Queue<Integer> queueIn ;
        private Queue<Integer> queueOut;
    public MyStack() {
        queueIn = new LinkedList<>();
        queueOut = new LinkedList<>();
    }

    public void push(int x) {
        queueIn.add(x);
    }

    public int pop() {
        findLast();
        return queueOut.poll();
    }

    public int top() {
        findLast();
        int x = queueOut.poll();
        queueIn.add(x);
        return x;
    }

    public boolean empty() {
        return queueIn.size()==0;
    }

    private void findLast(){

        while (queueIn.size() > 1) {
            queueOut.add(queueIn.poll());
        }
        Queue<Integer> temp = queueIn;
        queueIn = queueOut;
        queueOut = temp;

    }
}

    /**
     * 简单的计算器 https://leetcode.com/problems/basic-calculator/
     *
     *
     * Given a string s representing a valid expression,
     * implement a basic calculator to evaluate it, and return the result of the evaluation.
     *
     * Note: You are not allowed to use any built-in function
     * which evaluates strings as mathematical expressions, such as eval().
     *
     *
     *
     * Example 1:
     *
     * Input: s = "1 + 1"
     * Output: 2
     * Example 2:
     *
     * Input: s = " 2-1 + 2 "
     * Output: 3
     * Example 3:
     *
     * Input: s = "(1+(4+5+2)-3)+(6+8)"
     * Output: 23
     *
     * 解题：
     * 1、从左到右开始计算：使用栈从右向左遍历，出栈进行计算
     * 2、当遇到)是预先计算（）；
     * @param s
     * @return
     */
    public int calculate(String s) {
        int ans = 0;
        ans = cal(s,0,s.length()-1);
        return ans;
    }

    public int cal(String s,int left,int right){
        Stack<Integer> nums = new Stack<>();
        Stack<Character> chars = new Stack<>();
        int ans = 0;

        char[] sc = s.toCharArray();
        for (int i = right; i >= left; i--) {
            if (sc[i] == '+' || sc[i]=='-'){
                chars.push(sc[i]);
            }
            if (sc[i] >= '0' && sc[i] <= '9') {
                int j = i , temp = sc[i]-'0';
                while (j-1 >= left && sc[j-1] >= '0' && sc[j-1] <= '9'){
                    j--;
                }
                if (j < i){
                    temp = sc[j]-'0';
                    for (int k = j; k < i; k++) {
                        temp = temp*10 + sc[k+1]-'0';
                    }
                }
                nums.push(temp);
                i = j;
            }
            if (sc[i] == ')') {
                int j = i;
                while (j-1 >= left && sc[j-1] != '('){
                    j--;
                }
                nums.push(cal(s,j,i-1));
                i = j;
            }
        }

        while (!chars.isEmpty()) {
            switch (chars.pop()) {
                case '+' : ans = nums.pop() + nums.pop() ; break;
                case '-' :
                    ans = nums.pop() - nums.pop() ; break;
            }
            nums.push(ans);
        }
        return nums.pop();
    }


    /**
     * 寻找中位数 https://leetcode.com/problems/median-of-two-sorted-arrays/
     * Given two sorted arrays nums1 and nums2 of size m and n respectively,
     * return the median of the two sorted arrays.
     *
     * The overall run time complexity should be O(log (m+n)).
     *
     *
     *
     * Example 1:
     *
     * Input: nums1 = [1,3], nums2 = [2]
     * Output: 2.00000
     * Explanation: merged array = [1,2,3] and median is 2.
     * Example 2:
     *
     * Input: nums1 = [1,2], nums2 = [3,4]
     * Output: 2.50000
     * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
     *
     * 题解：
     * 用栈储存到中间数值；
     * 计算：如果两个数组的总数量是2的倍数，则加算栈前两位的平均值；
     * 如果两个数组的总数量不是2的倍数，中位数就是栈顶的数字。
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
         double ans = 0;
         int length  = nums1.length+nums2.length;
         int middleIndex = length/2+1 ;
         Stack<Integer> tank = new Stack<>();
         int i = 0,j = 0;
         while (tank.size() < middleIndex){
             if (i < nums1.length && j < nums2.length){
                 if (nums1[i] < nums2[j]){
                     tank.push(nums1[i++]);
                 }else {
                     tank.push(nums2[j++]);
                 }
             }else if (i < nums1.length) {
                 tank.push(nums1[i++]);
             }else if (j < nums2.length) {
                 tank.push(nums2[j++]);
             }
         };

         if (length % 2 == 0) {
             ans = (tank.pop() + tank.pop()+0.0) / 2;
         } else {
             ans = tank.pop();
         }
         return ans;
    }
}
