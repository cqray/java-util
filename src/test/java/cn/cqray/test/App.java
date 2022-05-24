package cn.cqray.test;

import cn.cqray.java.traversal.Traversal;
import cn.cqray.java.traversal.TraversalCallback;
import cn.cqray.java.util.StringUtils;

public class App {
    public static void main(String[] args) {
        System.out.println(StringUtils.isEmpty("44"));

        int[] ints = new int[]{10, 11};
//        Traversal.<String>positive(((Object) ints), new TraversalCallback<String>() {
//            @Override
//            public boolean onCall(String item) {
//                return false;
//            }
//        });
        new Traversal(ints)
                .type(int.class)
                .positive(item -> {
                    System.out.println(item);
                    return false;
                });
    }
}
