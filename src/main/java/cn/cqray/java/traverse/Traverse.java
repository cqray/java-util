package cn.cqray.java.traverse;

import cn.cqray.java.type.ExtraTypeManager;
import cn.cqray.java.type.TypeAdapter;
import cn.cqray.java.util.SizeUtils;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * 遍历辅助工具
 * @author Cqray
 */
@SuppressWarnings("unchecked")
public class Traverse {

    /** 要遍历的数据 **/
    private Object mData;

    private Traverse(Object data) {
        mData = data;
    }

    /**
     * 设置键类型，并遍历键
     * @param cls 类
     * @param <T> 泛型
     * @return 遍历内部实现
     */
    public <T> TraversalKit<T> keyType(Class<T> cls) {
        return new TraversalKit<>(mData, true);
    }

    /**
     * 设置值类型，并遍历值
     * @param cls 类
     * @param <T> 泛型
     * @return 遍历内部实现
     */
    public <T> TraversalKit<T> valType(Class<T> cls) {
        return new TraversalKit<>(mData, false);
    }

    /**
     * 关联数据
     * @param data 要遍历的数据
     * @return 遍历对象
     */
    @NotNull
    public static Traverse with(Object data) {
        return new Traverse(data);
    }

    /**
     * 遍历迭代器
     * @param iterable 迭代器
     * @param reverse 倒序
     * @param callback 回调
     * @param <T> 泛型
     */
    private static <T> void iterable(Iterable<T> iterable, boolean reverse, TraverseCallback<T> callback) {
        if (iterable != null) {
            if (reverse) {
                // 倒序
                List<T> list;
                // 转换成List
                if (iterable instanceof List) {
                    list = (List<T>) iterable;
                } else {
                    list = new ArrayList<>();
                    for (T t : iterable) {
                        list.add(t);
                    }
                }
                // 倒序遍历
                for (int i = list.size() - 1; i >= 0; i--) {
                    if (callback != null && callback.onCall(list.get(i))) {
                        break;
                    }
                }
            } else {
                // 正序
                for (T t : iterable) {
                    if (callback != null && callback.onCall(t)) {
                        break;
                    }
                }
            }

        }
    }

    /**
     * 遍历Map
     * @param map Map集合
     * @param reverse 倒序
     * @param callback 回调
     * @param <T> 泛型
     */
    private static <T> void map(Map<Object, T> map, boolean reverse, TraverseCallback<T> callback) {
        if (map != null) {
            iterable(map.values(), reverse, callback);
        }
    }

    /**
     * 遍历数组
     * @param array 数组
     * @param reverse 倒序
     * @param callback 回调
     * @param <T> 泛型
     */
    private static <T> void array(Object array, boolean reverse, TraverseCallback<T> callback) {
        if (array != null) {
            Class<?> cls = array.getClass();
            if (!cls.isArray()) {
                return;
            }
            // 获取数据长度
            int length = SizeUtils.get(array);
            if (reverse) {
                // 倒序遍历
                for (int i = length - 1; i >= 0; i--) {
                    Object item = getItem(array, i);
                    if (callback != null && callback.onCall((T) item)) {
                        break;
                    }
                }
            } else {
                // 顺序遍历
                for (int i = 0; i < length; i++ ) {
                    Object item = getItem(array, i);
                    if (callback != null && callback.onCall((T) item)) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * 从数组中获取指定位置项
     * @param array 数组数据
     * @param index 索引
     */
    private static Object getItem(@NotNull Object array, int index) {
        Class<?> cls = array.getClass();
        Object data;
        if (byte[].class == cls) {
            data = ((byte[]) array)[index];
        } else if (boolean[].class == cls) {
            data = ((boolean[]) array)[index];
        } else if (char[].class == cls) {
            data = ((char[]) array)[index];
        } else if (short[].class == cls) {
            data = ((short[]) array)[index];
        } else if (int[].class == cls) {
            data = ((int[]) array)[index];
        } else if (long[].class == cls) {
            data = ((long[]) array)[index];
        } else if (float[].class == cls) {
            data = ((float[]) array)[index];
        } else if (double[].class == cls) {
            data = ((double[]) array)[index];
        } else {
            data = ((Object[]) array)[index];
        }
        return data;
    }

    /**
     * 遍历
     * @param data 要遍历的数据
     * @param key 是否遍历Key
     * @param reverse true 倒序 false正序
     * @param callback 回调
     * @param <T> 泛型
     */
    private static <T> void traverse(Object data, boolean key, boolean reverse, TraverseCallback<T> callback) {
        if (data != null && callback != null) {
            if (data instanceof Iterable) {
                // 链表遍历Key-Value均一致
                iterable((Iterable<T>) data, reverse, callback);
            } else if (data instanceof Map) {
                Map map = (Map) data;
                if (key) {
                    iterable(map.keySet(), reverse, callback);
                } else {
                    iterable(map.values(), reverse, callback);
                }
            } else {
                Class<?> cls = data.getClass();
                if (cls.isArray()) {
                    // 数组遍历Key-Value均一致
                    array(data, reverse, callback);
                } else {
                    List<TypeAdapter<?>> adapters = ExtraTypeManager.getInstance().getTypeAdapters();
                    for (TypeAdapter<?> adapter : adapters) {
                        if (cls == adapter.getTypeClass()) {
                            adapter.traversal(data, key, reverse, callback);
                        }
                    }
                }
            }
        }
    }

    /**
     * 遍历内部实现
     * @param <T> 泛型
     */
    public static final class TraversalKit<T> {

        /** 要遍历数据 **/
        private Object mData;
        /** 遍历key **/
        private boolean mKey;

        public TraversalKit(Object data, boolean key) {
            mData = data;
            mKey = key;
        }

        /**
         * 正序遍历
         * @param callback 遍历回调
         */
        public void positive(TraverseCallback<T> callback) {
            Traverse.traverse(mData, mKey, false, callback);
        }

        /**
         * 倒序遍历
         * @param callback 遍历回调
         */
        public void reverse(TraverseCallback<T> callback) {
            Traverse.traverse(mData, mKey, true, callback);
        }

        /**
         * 遍历查找
         * @param condition 比对条件
         */
        public T find(Condition<T> condition) {
            if (condition == null) {
                return null;
            }
            List<T> list = new ArrayList<>();
            positive(item -> {
                if (condition.isMatch(item)) {
                    list.add(item);
                    return true;
                }
                return false;
            });
            return list.isEmpty() ? null : list.get(0);
        }

        /**
         * 遍历查找索引
         * @param condition 比对条件
         */
        public int findIndex(Condition<T> condition) {
            if (condition == null) {
                return -1;
            }
            int[] index = new int[] {0, -1};
            positive(item -> {
                if (condition.isMatch(item)) {
                    index[1] = index[0];
                    return true;
                }
                index[0] = index[0] + 1;
                return false;
            });
            return index[1];
        }
    }
}
