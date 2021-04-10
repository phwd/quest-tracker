package com.facebook.jni;

import X.KV;

public class Countable {
    public long mInstance = 0;

    public native void dispose();

    static {
        KV.A01("fb");
    }

    public void finalize() {
        dispose();
        super.finalize();
    }
}
