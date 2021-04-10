package com.facebook.jni;

import X.Pa;
import X.g6;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class HybridData {
    @DoNotStrip
    public Destructor mDestructor = new Destructor(this);

    public static class Destructor extends Pa {
        @DoNotStrip
        public volatile long mNativePointer;

        public static native void deleteNative(long j);

        @Override // X.Pa
        public final void destruct() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0;
        }

        public Destructor(Object obj) {
            super(obj);
        }
    }

    static {
        g6.A00();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
