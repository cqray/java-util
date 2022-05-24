package cn.cqray.java.traversal;

import java.util.Map;

@SuppressWarnings("unchecked")
public class Traversal {

    private static <T> int iterable(Iterable<T> iterable, TraversalCallback<T> callback) {
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

//    public static <K, V> int traverse(Map<K, V> map, TraversalCallback<Map.Entry<K, V>> callback) {
//        int count = 0;
//        if (!isEmpty(map)) {
//            for (Map.Entry<K, V> entry : map.entrySet()) {
//                if (callback == null || callback.onCall(entry)) {
//                    break;
//                }
//                count++;
//            }
//        }
//        return count;
//    }

    public static <T> int map(Map<Object, T> map, TraversalCallback<T> callback) {
        int count = 0;
        if (map != null) {
            return iterable(map.values(), callback);
        }
        return count;
    }

    public static <T> int positive(T data, TraversalCallback<T> callback) {
        if (data != null && callback != null) {
            if (data instanceof Iterable) {
                return iterable((Iterable<T>) data, callback);
            } else if (data instanceof Map) {
                return map((Map<Object, T>) data, callback);
            }
        }
        return 0;
    }

    public static <T> void reverse(T data, TraversalCallback<T> callback) {

    }

}
