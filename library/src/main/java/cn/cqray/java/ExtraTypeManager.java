package cn.cqray.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
