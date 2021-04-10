package com.facebook.jni;

import X.C05400jG;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class Countable {
    @DoNotStrip
    public long mInstance = 0;

    public native void dispose();

    static {
        C05400jG.A00("fb");
    }

    public void finalize() throws Throwable {
        dispose();
        super.finalize();
    }
}
