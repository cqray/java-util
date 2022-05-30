package cn.cqray.java.util;

import cn.cqray.java.type.ExtraTypeManager;
import cn.cqray.java.type.TypeAdapter;

import java.util.List;
import java.util.Map;

/**
 * 数据个数工具类
 * @author Cqray
 */
public class SizeUtils {

    public static int get(CharSequence text) {
        return text == null ? 0 : text.length();
    }

    public static int get(Iterable<?> iterable) {
        int size = 0;
        if (iterable != null) {
            for (Object ignore : iterable) {
                size++;
            }
        }
        return size;
    }

    public static int get(Map<?, ?> map) {
        return map == null ? 0 : map.size();
    }

    public static int get(byte[] array) {
        return array == null ? 0 : array.length;
    }

    public static int get(boolean[] array) {
        return array == null ? 0 : array.length;
    }

    public static int get(char[] array) {
        return array == null ? 0 : array.length;
    }

    public static int get(short[] array) {
        return array == null ? 0 : array.length;
    }

    public static int get(int[] array) {
        return array == null ? 0 : array.length;
    }

    public static int get(long[] array) {
        return array == null ? 0 : array.length;
    }

    public static int get(float[] array) {
        return array == null ? 0 : array.length;
    }

    public static int get(double[] array) {
        return array == null ? 0 : array.length;
    }

    public static int get(Object[] array) {
        return array == null ? 0 : array.length;
    }

    public static int get(Object data) {
        if (data == null) {
            return 0;
        } else if (data instanceof CharSequence) {
            return get((CharSequence) data);
        } else if (data instanceof Iterable) {
            return get((Iterable<?>) data);
        } else if (data instanceof Map) {
            return get((Map<?, ?>) data);
        } else {
            Class<?> cls = data.getClass();
            if (byte[].class == cls) {
                return get((byte[]) data);
            } else if (boolean[].class == cls) {
                return get((boolean[]) data);
            } else if (char[].class == cls) {
                return get((char[]) data);
            } else if (short[].class == cls) {
                return get((short[]) data);
            } else if (int[].class == cls) {
                return get((int[]) data);
            } else if (long[].class == cls) {
                return get((long[]) data);
            } else if (float[].class == cls) {
                return get((float[]) data);
            } else if (double[].class == cls) {
                return get((double[]) data);
            } else if (cls.isArray()) {
                return get((Object[]) data);
            } else {
                List<TypeAdapter<?>> adapters = ExtraTypeManager.getInstance().getTypeAdapters();
                for (TypeAdapter<?> adapter : adapters) {
                    if (cls.getName().equals(adapter.getClassType().getTypeName())) {
                        return adapter.getSize(data);
                    }
                }
            }
        }
        return -1;
    }
}
