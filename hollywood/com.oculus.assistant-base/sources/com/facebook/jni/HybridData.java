package com.facebook.jni;

import X.F5;
import X.KV;

public class HybridData {
    public Destructor mDestructor = new Destructor(this);

    public class Destructor extends F5 {
        public volatile long mNativePointer;

        public static native void deleteNative(long j);

        @Override // X.F5
        public final void destruct() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0;
        }

        public Destructor(Object obj) {
            super(obj);
        }
    }

    static {
        KV.A01("fbjni");
    }
}
