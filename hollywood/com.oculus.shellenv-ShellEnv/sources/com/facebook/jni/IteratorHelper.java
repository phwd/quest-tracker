package com.facebook.jni;

import java.util.Iterator;

public class IteratorHelper {
    private final Iterator a;
    private Object mElement;

    public IteratorHelper(Iterable iterable) {
        this.a = iterable.iterator();
    }

    public IteratorHelper(Iterator it) {
        this.a = it;
    }

    /* access modifiers changed from: package-private */
    public boolean hasNext() {
        if (this.a.hasNext()) {
            this.mElement = this.a.next();
            return true;
        }
        this.mElement = null;
        return false;
    }
}
