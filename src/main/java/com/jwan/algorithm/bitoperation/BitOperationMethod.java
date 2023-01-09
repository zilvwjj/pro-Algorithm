package com.jwan.algorithm.bitoperation;

import java.util.HashMap;
import java.util.Map;

public class BitOperationMethod {
    /**
     * Hamming Distance (Easy)
     * 题目描述
     * 给定两个十进制数字，求它们二进制表示的汉明距离(Hamming distance，即不同位的个数)。 输入输出样例
     *   输入是两个十进制整数，输出是一个十进制整数，表示两个输入数字的汉明距离。
     * Input: x = 1, y = 4
     * Output: 2
     * 在这个样例中，1 的二进制是 0001，4 的二进制是 0100，一共有两位不同。
     * 题解
     * 对两个数进行按位异或操作，统计有多少个 1 即可。
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int diff = x ^ y, ans = 0;
        while (diff > 0) {
            ans += diff & 1;
            diff >>= 1; }
        return ans; }

    /**
     * Single Number (Easy)
     * 题目描述:
     * 给定一个整数数组，这个数组里只有一个数次出现了一次，其余数字出现了两次，求这个只 出现一次的数字。
     * 输入输出样例:
     *   输入是一个一维整数数组，输出是该数组内的一个整数。
     *   Input: [4,1,2,1,2]
     *   Output: 4
     *
     * 题解:
     * 我们可以利用 x ∧ x = 0 和 x ∧ 0 = x 的特点，将数组内所有的数字进行按位异或。
     * 出现两次 的所有数字按位异或的结果是 0，0 与出现一次的数字异或可以得到这个数字本身。
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp ^= nums[i];
        }
        return temp;
    }

    /**
     * Power of Four (Easy)
     *
     * 题目描述:
     * 给定一个整数，判断它是否是 4 的次方。 输入输出样例
     *   输入是一个整数，输出是一个布尔值，表示判断结果。
     * Input: 16
     * Output: true
     * 在这个样例中，16 是 4 的二次方，因此返回值为真。
     *
     * 题解:
     * 首先我们考虑一个数字是不是 2 的(整数)次方:如果一个数字 n 是 2 的整数次方，
     * 那么它 的二进制一定是 0...010...0 这样的形式;考虑到 n − 1 的二进制是 0...001...1，
     * 这两个数求按位与 的结果一定是 0。因此如果 n & (n - 1) 为 0，那么这个数是 2 的次方。
     * 如果这个数也是 4 的次方，那二进制表示中 1 的位置必须为奇数位。
     * 我们可以把 n 和二进制 的 10101...101(即十进制下的 1431655765)做按位与，如果结果不为 0，那么说明这个数是 4 的 次方。
     * @param n
     * @return
     */

    public boolean isPowerOfFour(int n) {
        int fourOperation = 1431655765;
        return n > 0 && (n & (n-1)) == 0 && (n & fourOperation) != 0;
    }

    /**
     * Maximum Product of Word Lengths (Medium)
     *
     * 题目描述:
     * 给定多个字母串，求其中任意两个字母串的长度乘积的最大值，且这两个字母串不能含有相 同字母。
     *
     * 输入输出样例:
     *   输入一个包含多个字母串的一维数组，输出一个整数，表示长度乘积的最大值。
     * Input: ["a","ab","abc","d","cd","bcd","abcd"]
     * Output: 4
     * 在这个样例中，一种最优的选择是“ab”和“cd”。
     *
     * 题解:
     * 怎样快速判断两个字母串是否含有重复数字呢?可以为每个字母串建立一个长度为 26 的二进制数字，每个位置表示是否存在该字母。
     * 如果两个字母串含有重复数字，那它们的二进制表示 的按位与不为 0。
     * 同时，我们可以建立一个哈希表来存储字母串(在数组的位置)到二进制数字 的映射关系，方便查找调用。
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        int ans = 0;
        for ( String word: words
             ) {
            int mark = 0,size = word.length();
            for (char c: word.toCharArray()
                 ) {
                mark |= 1 << (c-'a'); //记录字符串有几种字符
            }

            //如果字符串的种类个数相同，就存下最长的字符串个数；
            hashMap.put(mark,Math.max(size,hashMap.get(mark)!=null?hashMap.get(mark):size));
            for (Map.Entry<Integer,Integer> entry:hashMap.entrySet()
                 ) {
                if ((entry.getKey() & mark) == 0) { //如果没有相同的字符就进行下一步
                    ans = Math.max(ans,size*entry.getValue());
                }
            }
        }

        return ans;
    }

    /**
     * Counting Bits (Medium)
     * 题目描述:
     * 给定一个非负整数 n，求从 0 到 n 的所有数字的二进制表达中，分别有多少个 1。
     *
     * 输入输出样例:
     * 输入是一个非负整数 n，输出是长度为 n + 1 的非负整数数组，每个位置 m 表示 m 的二进制 里有多少个 1。
     * Input: 5
     * Output: [0,1,1,2,1,2]
     *
     * 题解:
     * 本题可以利用动态规划和位运算进行快速的求解。定义一个数组 dp，其中 dp[i] 表示数字 i 的二进制含有 1 的个数。
     * 对于第 i 个数字，如果它二进制的最后一位为 1，那么它含有 1 的个数 则为 dp[i-1] + 1;如果它二进制的最后一位为 0，
     * 那么它含有 1 的个数和其算术右移结果相同，即 dp[i>>1]。
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] ans = new int[n+1];
        for (int i = 1; i < ans.length; i++) {
            if ((i & 1) == 1) {
                ans[i] = ans[i-1]+1;
            }else {
                ans[i] = ans[i>>1];
            }
        }
        return ans;
    }
}
