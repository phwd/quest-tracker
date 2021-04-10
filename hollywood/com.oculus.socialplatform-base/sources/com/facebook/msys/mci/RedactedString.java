package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Immutable
public class RedactedString {
    @DoNotStrip
    public NativeHolder mNativeHolder;

    @DoNotStrip
    private native boolean equalsNative(Object obj);

    @DoNotStrip
    public static native NativeHolder initNativeHolder(String str);

    @DoNotStrip
    public static native NativeHolder initNativeHolder(String str, int i);

    @DoNotStrip
    public native String getOriginalString();

    @DoNotStrip
    public native int hashCode();

    @DoNotStrip
    public native int leakAllowance();

    @DoNotStrip
    public native String toString();

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof RedactedString)) {
            return false;
        }
        return equalsNative(obj);
    }

    static {
        AnonymousClass1Nh.A00();
    }

    @DoNotStrip
    public RedactedString(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }

    public RedactedString(String str) {
        if (str != null) {
            this.mNativeHolder = initNativeHolder(str);
            return;
        }
        throw null;
    }
}
