package cn.cqray.java.util;

import cn.cqray.java.traverse.Traverse;

/**
 * 字符串工具类
 * @author Cqray
 */
public class StringUtils {

    public static boolean isEmpty(CharSequence text) {
        return EmptyUtils.check(text);
    }

    public static boolean isBlack(CharSequence text) {
        if (isEmpty(text)) {
            return true;
        }
        return isEmpty(text.toString().trim().replaceAll(" ", ""));
    }

    public static String join(Iterable<?> iterable, String join) {
        return join((Object) iterable, join);
    }

    public static String join(byte[] array, String join) {
        return join((Object) array, join);
    }

    public static String join(char[] array, String join) {
        return join((Object) array, join);
    }

    public static String join(boolean[] array, String join) {
        return join((Object) array, join);
    }

    public static String join(short[] array, String join) {
        return join((Object) array, join);
    }

    public static String join(int[] array, String join) {
        return join((Object) array, join);
    }

    public static String join(long[] array, String join) {
        return join((Object) array, join);
    }

    public static String join(float[] array, String join) {
        return join((Object) array, join);
    }

    public static String join(double[] array, String join) {
        return join((Object) array, join);
    }

    public static String join(Object[] array, String join) {
        return join((Object) array, join);
    }

    public static String join(Object data, String join) {
        if (data != null) {
            int size = SizeUtils.get(data);
            if (size <= 0) {
                return data.toString();
            }
            String newJoin = join == null ? "" : join;
            StringBuilder builder = new StringBuilder();
            Traverse.with(data)
                    .valType(Object.class)
                    .positive(object -> {
                        builder.append(object).append(newJoin);
                        return false;
                    });
            builder.setLength(builder.length() - newJoin.length());
            return builder.toString();
        }
        return "";
    }

    public static CharSequence reserve(CharSequence text) {
        if (isEmpty(text)) {
            return text;
        }
        StringBuilder builder = new StringBuilder(text);
        builder.reverse();
        return builder.toString();
    }
}
