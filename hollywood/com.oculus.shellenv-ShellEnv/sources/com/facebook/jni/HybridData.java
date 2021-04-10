package com.facebook.jni;

import com.facebook.jni.a;

public class HybridData {
    private Destructor mDestructor = new Destructor(this);

    public static class Destructor extends a.AbstractC0000a {
        private volatile long mNativePointer;

        Destructor(Object obj) {
            super(obj);
        }

        static native void deleteNative(long j);

        /* access modifiers changed from: protected */
        @Override // com.facebook.jni.a.AbstractC0000a
        public final void a() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0;
        }
    }

    static {
        com.facebook.soloader.a.a.a("fbjni");
    }
}
