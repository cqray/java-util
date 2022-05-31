package cn.cqray.java.traverse;

/**
 * 遍历回调
 * @author Cqray
 */
public interface TraverseCallback<T> {

    /**
     * 遍历子项回调
     * @param t 子项
     * @return 是否拦截
     */
    boolean onCall(T t);
}
