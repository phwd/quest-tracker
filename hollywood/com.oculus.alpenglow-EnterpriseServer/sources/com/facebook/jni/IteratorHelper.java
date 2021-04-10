package com.facebook.jni;

import com.facebook.jni.annotations.DoNotStrip;
import java.util.Iterator;
import javax.annotation.Nullable;

@DoNotStrip
public class IteratorHelper {
    @DoNotStrip
    @Nullable
    public Object mElement;
    public final Iterator mIterator;

    @DoNotStrip
    public boolean hasNext() {
        if (this.mIterator.hasNext()) {
            this.mElement = this.mIterator.next();
            return true;
        }
        this.mElement = null;
        return false;
    }

    @DoNotStrip
    public IteratorHelper(Iterable iterable) {
        this.mIterator = iterable.iterator();
    }

    @DoNotStrip
    public IteratorHelper(Iterator it) {
        this.mIterator = it;
    }
}
