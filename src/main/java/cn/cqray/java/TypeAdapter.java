package cn.cqray.java;

import cn.cqray.java.traversal.TraversalCallback;

@SuppressWarnings("unchecked")
public abstract class TypeAdapter<T> {

    public final int getSize(Object data) {
        return size((T) data);
    }

    public final int traversal(Object data, TraversalCallback<?> callback) {
        return onTraversal((T) data, (TraversalCallback<T>) callback);
    }

    public abstract int size(T data);

    public abstract int onTraversal(T data, TraversalCallback<T> callback);

    public abstract Class<T> getTypeClass();
}
