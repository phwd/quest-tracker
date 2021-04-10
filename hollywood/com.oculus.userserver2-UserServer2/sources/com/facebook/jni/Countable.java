package com.facebook.jni;

import X.g6;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class Countable {
    @DoNotStrip
    public long mInstance = 0;

    public native void dispose();

    static {
        g6.A00();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public void finalize() throws Throwable {
        dispose();
        super.finalize();
    }
}
