package cn.cqray.java.type;

import cn.cqray.java.traverse.TraverseCallback;

/**
 * 类型适配器
 * @author Cqray
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class TypeAdapter<T> {

    public final int getSize(Object data) {
        return size((T) data);
    }

    public final void traversal(Object data, boolean key, boolean reserve, TraverseCallback<?> callback) {
        onTraversal((T) data, key, reserve, (TraverseCallback<Object>) callback);
    }

    /**
     * 类型Class
     * @return Class
     */
    public abstract Class<T> getTypeClass();

    /**
     * 数据个数计算
     * @param data 数据
     * @return 个数
     */
    public abstract int size(T data);

    /**
     * 遍历数值实现
     * @param data 数据
     * @param reserve 是否倒序
     * @param callback 回调
     */
    public abstract void onTraversal(T data, boolean key, boolean reserve, TraverseCallback<Object> callback);
}
