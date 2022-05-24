package cn.cqray.java.traverse;

public interface TraverseCallback<T> {

    /**
     * 遍历子项回调
     * @param item 子项
     * @return 是否拦截
     */
    boolean onCall(T item);
}
