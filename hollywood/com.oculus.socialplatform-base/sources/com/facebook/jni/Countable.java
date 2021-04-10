package com.facebook.jni;

import X.AnonymousClass0lD;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class Countable {
    @DoNotStrip
    public long mInstance = 0;

    public native void dispose();

    static {
        AnonymousClass0lD.A01("fb");
    }

    public void finalize() throws Throwable {
        dispose();
        super.finalize();
    }
}
