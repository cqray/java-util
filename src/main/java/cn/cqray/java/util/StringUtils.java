package cn.cqray.java.util;

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

    public static void join(Iterable<?> iterable, String str) {
        
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
