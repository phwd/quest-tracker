package com.facebook.msys.mci;

import X.AnonymousClass0l0;
import android.annotation.SuppressLint;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Immutable
public class NetworkMultiPartParam {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(@Nullable String str, @Nullable String str2, byte[] bArr);

    @DoNotStrip
    private native boolean nativeEquals(Object obj);

    @DoNotStrip
    public native byte[] getData();

    @DoNotStrip
    @Nullable
    public native String getFileName();

    @DoNotStrip
    @Nullable
    public native String getMimeType();

    public native int hashCode();

    public native String toString();

    static {
        AnonymousClass0l0.A06("msysjniinfranosqlite");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NetworkMultiPartParam)) {
            return false;
        }
        return nativeEquals(obj);
    }

    @DoNotStrip
    public NetworkMultiPartParam(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
