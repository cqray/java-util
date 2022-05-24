package cn.cqray.java.traversal;

public interface TraversalCallback<T> {

    /**
     * 遍历子项回调
     * @param item 子项
     * @return 是否拦截
     */
    boolean onCall(T item);
}
