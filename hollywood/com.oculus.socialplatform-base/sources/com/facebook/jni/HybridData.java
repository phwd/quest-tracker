package com.facebook.jni;

import X.AnonymousClass0S5;
import X.AnonymousClass0lD;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class HybridData {
    @DoNotStrip
    public Destructor mDestructor = new Destructor(this);

    public static class Destructor extends AnonymousClass0S5 {
        @DoNotStrip
        public volatile long mNativePointer;

        public static native void deleteNative(long j);

        @Override // X.AnonymousClass0S5
        public final void destruct() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0;
        }

        public Destructor(Object obj) {
            super(obj);
        }
    }

    static {
        AnonymousClass0lD.A01("fbjni");
    }
}
