package com.google.common.collect;

import X.AbstractC0360Tk;
import java.io.Serializable;

public class ImmutableEntry extends AbstractC0360Tk implements Serializable {
    public static final long serialVersionUID = 0;
    public final Object key;
    public final Object value;

    public ImmutableEntry(Object obj, Object obj2) {
        this.key = obj;
        this.value = obj2;
    }
}
