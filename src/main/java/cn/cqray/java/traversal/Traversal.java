package cn.cqray.java.traversal;

import cn.cqray.java.ExtraTypeManager;
import cn.cqray.java.TypeAdapter;
import cn.cqray.java.util.SizeUtils;

import java.util.List;
import java.util.Map;

/**
 * 遍历辅助工具
 * @author Cqray
 */
@SuppressWarnings("unchecked")
public class Traversal {

    private Object mData;

    public Traversal(Object data) {
        mData = data;
    }

    public <T> TT<T> type(Class<T> cls) {
        return new TT<>(mData, cls);
    }

    public static <T> int positive(Iterable<T> iterable, TraversalCallback<T> callback) {
        int count = 0;
        if (iterable != null) {
            for (T t : iterable) {
                count ++;
                if (callback != null && callback.onCall(t)) {
                    return count;
                }
            }
        }
        return count;
    }

    public static <T> int map(Map<Object, T> map, TraversalCallback<T> callback) {
        int count = 0;
        if (map != null) {
            return positive(map.values(), callback);
        }
        return count;
    }

    public static int positive(byte[] array, TraversalCallback<Byte> callback) {
        return array(array, callback);
    }

    public static int positive(char[] array, TraversalCallback<Character> callback) {
        return array(array, callback);
    }

    public static int positive(boolean[] array, TraversalCallback<Boolean> callback) {
        return array(array, callback);
    }

    public static int positive(short[] array, TraversalCallback<Short> callback) {
        return array(array, callback);
    }

    public static int positive(int[] array, TraversalCallback<Integer> callback) {
        return array(array, callback);
    }

    public static int positive(long[] array, TraversalCallback<Long> callback) {
        return array(array, callback);
    }

    public static int positive(float[] array, TraversalCallback<Float> callback) {
        return array(array, callback);
    }

    public static int positive(double[] array, TraversalCallback<Double> callback) {
        return array(array, callback);
    }

    public static <T> int positive(T[] array, TraversalCallback<T> callback) {
        return array(array, callback);
    }

    private static <T> int array(Object array, TraversalCallback<T> callback) {
        if (array != null) {
            Class<?> cls = array.getClass();
            if (!cls.isArray()) {
                return 0;
            }
            // 获取数据长度
            int length = SizeUtils.get(array);
            // 遍历数据
            for (int i = 0; i < length; i++ ) {
                Object data;
                if (byte[].class == cls) {
                    data = ((byte[]) array)[i];
                } else if (boolean[].class == cls) {
                    data = ((boolean[]) array)[i];
                } else if (char[].class == cls) {
                    data = ((char[]) array)[i];
                } else if (short[].class == cls) {
                    data = ((short[]) array)[i];
                } else if (int[].class == cls) {
                    data = ((int[]) array)[i];
                } else if (long[].class == cls) {
                    data = ((long[]) array)[i];
                } else if (float[].class == cls) {
                    data = ((float[]) array)[i];
                } else if (double[].class == cls) {
                    data = ((double[]) array)[i];
                } else {
                    data = ((Object[]) array)[i];
                }
                if (callback != null && callback.onCall((T) data)) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    private static <T> int positive(Object data, TraversalCallback<T> callback) {
        if (data != null && callback != null) {
            if (data instanceof Iterable) {
                return positive((Iterable<T>) data, callback);
            } else if (data instanceof Map) {
                return map((Map<Object, T>) data, callback);
            } else {
                Class<?> cls = data.getClass();
                if (cls.isArray()) {
                    return array(data, callback);
                } else {
                    List<TypeAdapter<?>> adapters = ExtraTypeManager.getInstance().getTypeAdapters();
                    for (TypeAdapter<?> adapter : adapters) {
                        if (cls == adapter.getTypeClass()) {
                            return adapter.traversal(data, callback);
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static <T> void reverse(T data, TraversalCallback<T> callback) {

    }


    public static class TT<T> {

        private Object mData;
        private Class<T> mClass;

        public TT(Object data, Class<T> cls) {
            mData = data;
            mClass = cls;
        }

        public void positive(TraversalCallback<T> callback) {
            Traversal.positive(mData, callback);
        }

        public void reverse() {

        }
    }
}
