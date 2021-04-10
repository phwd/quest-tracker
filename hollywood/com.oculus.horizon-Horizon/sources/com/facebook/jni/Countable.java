package com.facebook.jni;

import X.C03250cX;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class Countable {
    @DoNotStrip
    public long mInstance = 0;

    public native void dispose();

    static {
        C03250cX.A01("fb");
    }

    public void finalize() throws Throwable {
        dispose();
        super.finalize();
    }
}
