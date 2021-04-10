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
public class AppInfo {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(String str, String str2, String str3, String str4, @Nullable String str5, @Nullable String str6);

    @DoNotStrip
    private native boolean nativeEquals(Object obj);

    @DoNotStrip
    public native String getAppDisplayName();

    @DoNotStrip
    public native String getAppID();

    @DoNotStrip
    public native String getClientToken();

    @DoNotStrip
    @Nullable
    public native String getIdfa();

    @DoNotStrip
    @Nullable
    public native String getLocale();

    @DoNotStrip
    public native String getNonHumanReadableAppName();

    public native int hashCode();

    public native String toString();

    static {
        AnonymousClass0l0.A06("msysjniinfranosqlite");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AppInfo)) {
            return false;
        }
        return nativeEquals(obj);
    }

    @DoNotStrip
    public AppInfo(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }

    public AppInfo(String str, String str2, String str3, String str4, @Nullable String str5, @Nullable String str6) {
        if (str == null) {
            throw null;
        } else if (str2 == null) {
            throw null;
        } else if (str3 == null) {
            throw null;
        } else if (str4 != null) {
            this.mNativeHolder = initNativeHolder(str, str2, str3, str4, null, null);
        } else {
            throw null;
        }
    }
}
