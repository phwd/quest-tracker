package com.facebook.jni;

import X.C0431hn;
import X.R0;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class HybridData {
    @DoNotStrip
    public Destructor mDestructor = new Destructor(this);

    public static class Destructor extends R0 {
        @DoNotStrip
        public volatile long mNativePointer;

        public static native void deleteNative(long j);

        @Override // X.R0
        public final void destruct() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0;
        }

        public Destructor(Object obj) {
            super(obj);
        }
    }

    static {
        C0431hn.A00("fbjni");
    }
}
