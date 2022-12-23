package com.jwan.algorithm.charactertest;

import com.jwan.algorithm.character.CycleShift;
import com.jwan.algorithm.character.MyStringMove;
import org.junit.Test;

public class Shift {

    @Test
    public void demo01(){
        CycleShift cycleShift = new CycleShift();
        System.out.println(cycleShift.shiftContain("AABdCD", "CDA"));
    }

    @Test
    public void demo02() {
        char c = 500;
        System.out.println(c);
    }
}
