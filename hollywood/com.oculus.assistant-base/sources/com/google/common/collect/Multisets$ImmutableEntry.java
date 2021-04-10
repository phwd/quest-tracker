package com.google.common.collect;

import X.AbstractC1179ua;
import X.C0361Tm;
import java.io.Serializable;

public class Multisets$ImmutableEntry extends AbstractC1179ua implements Serializable {
    public static final long serialVersionUID = 0;
    public final int count;
    public final Object element;

    public Multisets$ImmutableEntry(Object obj, int i) {
        this.element = obj;
        this.count = i;
        C0361Tm.A00(i, "count");
    }
}
