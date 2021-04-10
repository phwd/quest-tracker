package com.facebook.jni;

import X.AnonymousClass0Rv;
import X.C05400jG;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class HybridData {
    @DoNotStrip
    public Destructor mDestructor = new Destructor(this);

    public static class Destructor extends AnonymousClass0Rv {
        @DoNotStrip
        public volatile long mNativePointer;

        public static native void deleteNative(long j);

        @Override // X.AnonymousClass0Rv
        public final void destruct() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0;
        }

        public Destructor(Object obj) {
            super(obj);
        }
    }

    static {
        C05400jG.A00("fbjni");
    }
}
