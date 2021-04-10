package com.facebook.jni;

import java.util.Iterator;
import java.util.Map;

public class MapIteratorHelper {
    private final Iterator mIterator;
    private Object mKey;
    private Object mValue;

    public MapIteratorHelper(Map map) {
        this.mIterator = map.entrySet().iterator();
    }

    /* access modifiers changed from: package-private */
    public boolean hasNext() {
        if (this.mIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) this.mIterator.next();
            this.mKey = entry.getKey();
            this.mValue = entry.getValue();
            return true;
        }
        this.mKey = null;
        this.mValue = null;
        return false;
    }
}
