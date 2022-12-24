package com.jwan.algorithm.charactertest;

import com.jwan.algorithm.character.IsomorphicStrings;
import org.junit.Test;

public class IsomorphicStringsTest {
    @Test
    public void demo01 () {
        IsomorphicStrings isomorphicStrings = new IsomorphicStrings();
        System.out.println(isomorphicStrings.isIsomorphic("hellooord", "helloword"));
    }
}
