package cn.cqray.java.util;

import java.util.Map;

/**
 * 空判断工具
 * @author Cqray
 */
public class EmptyUtils {

    public static boolean check(CharSequence sequence) {
        return sequence == null || sequence.length() == 0;
    }

    public static boolean check(Iterable<?> iterable) {
        return iterable == null || !iterable.iterator().hasNext();
    }

    public static boolean check(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean check(byte[] array) {
        return SizeUtils.get(array) == 0;
    }

    public static boolean check(boolean[] array) {
        return SizeUtils.get(array) == 0;
    }

    public static boolean check(char[] array) {
        return SizeUtils.get(array) == 0;
    }

    public static boolean check(short[] array) {
        return SizeUtils.get(array) == 0;
    }

    public static boolean check(int[] array) {
        return SizeUtils.get(array) == 0;
    }

    public static boolean check(long[] array) {
        return SizeUtils.get(array) == 0;
    }

    public static boolean check(float[] array) {
        return SizeUtils.get(array) == 0;
    }

    public static boolean check(double[] array) {
        return SizeUtils.get(array) == 0;
    }

    public static boolean check(Object[] array) {
        return SizeUtils.get(array) == 0;
    }

    public static boolean check(Object data) {
        if (data == null) {
            return true;
        } else if (data instanceof CharSequence) {
            return check((CharSequence) data);
        } else if (data instanceof Iterable) {
            return check((Iterable<?>) data);
        } else if (data instanceof Map) {
            return check((Map<?, ?>) data);
        } else {
            return SizeUtils.get(data) == 0;
        }
    }
}
