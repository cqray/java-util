package cn.cqray.test;

import cn.cqray.java.traverse.Traverse;
import cn.cqray.java.util.EmptyUtils;
import cn.cqray.java.util.SizeUtils;
import cn.cqray.java.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(StringUtils.isEmpty("44"));

        int[] ints = new int[]{10, 11};

        Traverse.with(ints)
                .valType(int.class)
                .positive(item -> {
                    System.out.println(item);
                    return false;
                });

        int index = Traverse.with(ints)
                .valType(int.class)
                .find(val -> val == 11);

        SizeUtils.get(new Object());
        EmptyUtils.check(new Object());

        List<Integer> list = Arrays.asList(1,2,3);

        System.out.println(StringUtils.join(list, ","));

        ListIterator<Integer> iterator = list.listIterator();

        while (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }

    }
}
