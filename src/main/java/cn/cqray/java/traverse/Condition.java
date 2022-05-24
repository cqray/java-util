package cn.cqray.java.traverse;

/**
 * 匹配条件
 * @author Cqray
 * @param <T> 泛型
 */
public interface Condition<T> {

    /**
     * 是否匹配
     * @param val 数据
     * @return true 匹配
     */
    boolean isMatch(T val);
}
