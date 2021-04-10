package com.facebook.common.propertybag;

import com.google.common.base.Preconditions;
import java.util.IdentityHashMap;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class PropertyBagHelper {
    @GuardedBy("this")
    private IdentityHashMap<Object, Object> mProperties;

    public synchronized void setProperty(Object identityBasedKey, Object value) {
        Preconditions.checkNotNull(identityBasedKey);
        Preconditions.checkNotNull(value);
        if (this.mProperties == null) {
            this.mProperties = new IdentityHashMap<>();
        }
        this.mProperties.put(identityBasedKey, value);
    }

    public synchronized Object getProperty(Object identityBasedKey) {
        Preconditions.checkNotNull(identityBasedKey);
        return this.mProperties != null ? this.mProperties.get(identityBasedKey) : null;
    }
}
