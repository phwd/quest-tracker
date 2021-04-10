package com.facebook.common.propertybag;

import javax.annotation.Nullable;

public interface PropertyBag {
    @Nullable
    Object getProperty(Object obj);

    void setProperty(Object obj, @Nullable Object obj2);
}
