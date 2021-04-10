package com.facebook.jni;

import X.C0431hn;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class Countable {
    @DoNotStrip
    public long mInstance = 0;

    public native void dispose();

    static {
        C0431hn.A00("fb");
    }

    public void finalize() throws Throwable {
        dispose();
        super.finalize();
    }
}
