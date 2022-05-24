package cn.cqray.java;

import cn.cqray.java.traverse.TraverseCallback;

@SuppressWarnings("unchecked")
public abstract class TypeAdapter<T> {

    public final int getSize(Object data) {
        return size((T) data);
    }

    public final void traversal(Object data, boolean reserve, TraverseCallback<?> callback) {
        onTraversal((T) data, reserve, (TraverseCallback<T>) callback);
    }

    public abstract Class<T> getTypeClass();

    public abstract int size(T data);

    public abstract void onTraversal(T data, boolean reserve, TraverseCallback<T> callback);
}
