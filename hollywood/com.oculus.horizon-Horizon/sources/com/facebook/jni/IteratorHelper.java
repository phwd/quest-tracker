package com.facebook.jni;

import com.facebook.jni.annotations.DoNotStrip;
import java.util.Iterator;
import javax.annotation.Nullable;

@DoNotStrip
public class IteratorHelper {
    public final Iterator A00;
    @DoNotStrip
    @Nullable
    public Object mElement;

    @DoNotStrip
    public boolean hasNext() {
        Iterator it = this.A00;
        if (it.hasNext()) {
            this.mElement = it.next();
            return true;
        }
        this.mElement = null;
        return false;
    }

    @DoNotStrip
    public IteratorHelper(Iterable iterable) {
        this.A00 = iterable.iterator();
    }

    @DoNotStrip
    public IteratorHelper(Iterator it) {
        this.A00 = it;
    }
}
