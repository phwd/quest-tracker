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
public class MediaUploadJobDetail {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(MediaUploadContentSource mediaUploadContentSource, MediaUploadConfig mediaUploadConfig, String str);

    @DoNotStrip
    private native boolean nativeEquals(Object obj);

    @DoNotStrip
    public native MediaUploadConfig getConfig();

    @DoNotStrip
    public native MediaUploadContentSource getContentSource();

    @DoNotStrip
    public native String getToken();

    public native int hashCode();

    public native String toString();

    static {
        AnonymousClass0l0.A06("msysjni");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MediaUploadJobDetail)) {
            return false;
        }
        return nativeEquals(obj);
    }

    @DoNotStrip
    public MediaUploadJobDetail(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
