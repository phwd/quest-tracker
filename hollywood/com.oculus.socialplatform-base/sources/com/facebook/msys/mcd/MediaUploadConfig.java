package com.facebook.msys.mcd;

import X.AnonymousClass0l0;
import android.annotation.SuppressLint;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Immutable
public class MediaUploadConfig {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(String str, @Nullable String str2, String str3, String str4, @Nullable String str5, String str6, @Nullable Map<String, String> map, @Nullable Map<String, Object> map2, @Nullable Map<String, String> map3, @Nullable String str7, @Nullable Map<String, Object> map4, @Nullable Map<String, String> map5);

    @DoNotStrip
    private native boolean nativeEquals(Object obj);

    @DoNotStrip
    @Nullable
    public native Map<String, String> getExtraHeaders();

    @DoNotStrip
    @Nullable
    public native Map<String, Object> getExtraLoggingData();

    @DoNotStrip
    public native String getFileName();

    @DoNotStrip
    public native String getJobId();

    @DoNotStrip
    @Nullable
    public native String getMailboxUserID();

    @DoNotStrip
    @Nullable
    public native String getMediaCreationRequestPath();

    @DoNotStrip
    public native String getMimeType();

    @DoNotStrip
    @Nullable
    public native Map<String, Object> getOptionalConfig();

    @DoNotStrip
    @Nullable
    public native Map<String, String> getParams();

    @DoNotStrip
    public native String getProtocol();

    @DoNotStrip
    @Nullable
    public native String getServerDedupeKey();

    @DoNotStrip
    @Nullable
    public native Map<String, String> getUrlParams();

    public native int hashCode();

    public native String toString();

    static {
        AnonymousClass0l0.A06("msysjni");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MediaUploadConfig)) {
            return false;
        }
        return nativeEquals(obj);
    }

    @DoNotStrip
    public MediaUploadConfig(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
