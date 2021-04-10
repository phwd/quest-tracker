package com.facebook.msys.mci;

import X.AnonymousClass0l0;
import android.annotation.SuppressLint;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Immutable
public class AuthData {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(@Nullable RedactedString redactedString, @Nullable String str, String str2, String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable List<String> list, @Nullable String str9, int i);

    @DoNotStrip
    private native boolean nativeEquals(Object obj);

    @DoNotStrip
    @Nullable
    public native RedactedString getAccessToken();

    @DoNotStrip
    @Nullable
    public native String getAnalyticsClaim();

    @DoNotStrip
    public native int getAuthType();

    @DoNotStrip
    public native String getDeviceID();

    @DoNotStrip
    @Nullable
    public native String getFacebookUserID();

    @DoNotStrip
    public native String getFamilyDeviceID();

    @DoNotStrip
    @Nullable
    public native String getFirstName();

    @DoNotStrip
    @Nullable
    public native String getFullName();

    @DoNotStrip
    @Nullable
    public native String getMachineID();

    @DoNotStrip
    @Nullable
    public native List<String> getSessionCookies();

    @DoNotStrip
    @Nullable
    public native String getUnderlyingAdminUserID();

    @DoNotStrip
    @Nullable
    public native String getUsername();

    public native int hashCode();

    public native String toString();

    static {
        AnonymousClass0l0.A06("msysjniinfranosqlite");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthData)) {
            return false;
        }
        return nativeEquals(obj);
    }

    public AuthData(@Nullable RedactedString redactedString, @Nullable String str, String str2, String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable List<String> list, @Nullable String str9, int i) {
        if (str2 != null) {
            this.mNativeHolder = initNativeHolder(redactedString, null, str2, "", str4, null, null, null, null, null, null, 0);
            return;
        }
        throw null;
    }

    @DoNotStrip
    public AuthData(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
