package com.jwan.algorithm.charactertest;

import com.jwan.algorithm.character.PalindromicSubstrings;
import org.junit.Test;

public class PalindromicSubstringsTest {
    @Test
    public void demo01 () {
        PalindromicSubstrings palindromicSubstrings = new PalindromicSubstrings();
        System.out.println(palindromicSubstrings.countSubstrings("abcdedg"));
    }
}
