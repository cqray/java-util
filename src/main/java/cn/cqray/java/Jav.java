package cn.cqray.java;

import cn.cqray.java.util.EmptyUtils;
import cn.cqray.java.util.StringUtils;

public class Jav {

    public static void main(String[] args) {
        int[] ints = new int[2];

        //Object[] objects = (Object[]) ints;

        System.out.println(EmptyUtils.check(ints));
        System.out.println(StringUtils.reserve("123"));
    }
}
