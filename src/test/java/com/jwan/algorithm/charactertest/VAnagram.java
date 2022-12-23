package com.jwan.algorithm.charactertest;

import com.jwan.algorithm.character.ValidAnagram;
import org.junit.Test;

public class VAnagram {
    @Test
    public void demo01() {
        ValidAnagram validAnagram = new ValidAnagram();
        System.out.println(validAnagram.isAnagram("abcdefg", "abefcd"));
    }
}
