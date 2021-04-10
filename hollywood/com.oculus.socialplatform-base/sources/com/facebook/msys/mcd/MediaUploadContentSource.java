package com.facebook.msys.mcd;

import X.AnonymousClass0l0;
import android.annotation.SuppressLint;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Immutable
public class MediaUploadContentSource {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(@Nullable byte[] bArr, @Nullable String str, @Nullable String str2);

    @DoNotStrip
    private native boolean nativeEquals(Object obj);

    @DoNotStrip
    @Nullable
    public native String getDiskCacheKey();

    @DoNotStrip
    @Nullable
    public native String getFilePath();

    @DoNotStrip
    @Nullable
    public native byte[] getUploadData();

    public native int hashCode();

    public native String toString();

    static {
        AnonymousClass0l0.A06("msysjni");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MediaUploadContentSource)) {
            return false;
        }
        return nativeEquals(obj);
    }

    @DoNotStrip
    public MediaUploadContentSource(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
