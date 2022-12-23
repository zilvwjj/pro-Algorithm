package com.jwan.algorithm.charactertest;

import org.junit.Test;

public class LongestPalindrome {
    @Test
    public void demo01 () {
        com.jwan.algorithm.character.LongestPalindrome longestPalindrome
                = new com.jwan.algorithm.character.LongestPalindrome("aabbbcccdddeeeeffffggg");

        System.out.println(longestPalindrome.getLongestPalindrome());
        System.out.println(longestPalindrome.getOddChar());
        System.out.println(longestPalindrome.getLength());
        System.out.println(longestPalindrome.getLongestPalindrome().length());
    }
}
