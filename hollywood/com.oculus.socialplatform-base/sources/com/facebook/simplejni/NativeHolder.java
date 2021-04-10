package com.facebook.simplejni;

import X.AnonymousClass0l0;
import X.AnonymousClass2eK;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class NativeHolder {
    @DoNotStrip
    public final Destructor mDestructor;
    @DoNotStrip
    public final long mNativePointer;

    @DoNotStrip
    public static class Destructor extends AnonymousClass2eK {
        @DoNotStrip
        public long mNativeDestructorFunctionPointer;
        @DoNotStrip
        public long mNativePointer;

        @DoNotStrip
        public static native void deleteNative(long j, long j2);

        static {
            AnonymousClass0l0.A06("simplejni");
        }

        @Override // X.AnonymousClass2eK
        public void destruct() {
            long j = this.mNativePointer;
            if (j != 0) {
                deleteNative(j, this.mNativeDestructorFunctionPointer);
                this.mNativePointer = 0;
            }
        }

        @DoNotStrip
        public Destructor(Object obj, long j, long j2) {
            super(obj);
            this.mNativePointer = j;
            this.mNativeDestructorFunctionPointer = j2;
        }
    }

    static {
        AnonymousClass0l0.A06("simplejni");
    }

    @DoNotStrip
    public NativeHolder(long j, long j2) {
        this.mNativePointer = j;
        this.mDestructor = new Destructor(this, j, j2);
    }
}
