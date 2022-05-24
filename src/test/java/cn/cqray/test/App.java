package cn.cqray.test;

import cn.cqray.java.traverse.Traverse;
import cn.cqray.java.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class App {
    public static void main(String[] args) {
        System.out.println(StringUtils.isEmpty("44"));

        int[] ints = new int[]{10, 11};
        
        Traverse.with(ints)
                .type(int.class)
                .reverse(item -> {
                    System.out.println(item);
                    return false;
                });

        int index = Traverse.with(ints)
                .type(int.class)
                .find(val -> val == 11);

        System.out.println("位置：" + index);

        List<Integer> list = Arrays.asList(1,2,3);

        ListIterator<Integer> iterator = list.listIterator();

        while (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }

    }
}
