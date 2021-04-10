package com.facebook.jni;

import X.AnonymousClass0R3;
import X.C03250cX;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class HybridData {
    @DoNotStrip
    public Destructor mDestructor = new Destructor(this);

    public static class Destructor extends AnonymousClass0R3 {
        @DoNotStrip
        public volatile long mNativePointer;

        public static native void deleteNative(long j);

        @Override // X.AnonymousClass0R3
        public final void destruct() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0;
        }

        public Destructor(Object obj) {
            super(obj);
        }
    }

    static {
        C03250cX.A01("fbjni");
    }
}
