package com.facebook.common.propertybag;

import com.google.common.base.Preconditions;
import java.util.IdentityHashMap;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class PropertyBagHelper {
    @GuardedBy("this")
    private IdentityHashMap<Object, Object> mProperties;

    public synchronized void setProperty(Object obj, Object obj2) {
        Preconditions.checkNotNull(obj);
        Preconditions.checkNotNull(obj2);
        if (this.mProperties == null) {
            this.mProperties = new IdentityHashMap<>();
        }
        this.mProperties.put(obj, obj2);
    }

    public synchronized Object getProperty(Object obj) {
        Preconditions.checkNotNull(obj);
        return this.mProperties != null ? this.mProperties.get(obj) : null;
    }
}
