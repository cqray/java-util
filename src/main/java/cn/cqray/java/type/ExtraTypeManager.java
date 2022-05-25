package cn.cqray.java.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 支撑SizeUtils、EmptyUtils、Traverse的额外数据类型
 * @author Cqray
 */
public class ExtraTypeManager {

    private static volatile ExtraTypeManager mInstance;
    private List<TypeAdapter<?>> mAdapters;

    private ExtraTypeManager() {
        mAdapters = Collections.synchronizedList(new ArrayList<>());
    }

    public static ExtraTypeManager getInstance() {
        if (mInstance == null) {
            synchronized (ExtraTypeManager.class) {
                if (mInstance == null) {
                    mInstance = new ExtraTypeManager();
                }
            }
        }
        return mInstance;
    }

    public void addTypeAdapter(TypeAdapter<?> adapter) {
        mAdapters.add(adapter);
    }

    public List<TypeAdapter<?>> getTypeAdapters() {
        return mAdapters;
    }

}
