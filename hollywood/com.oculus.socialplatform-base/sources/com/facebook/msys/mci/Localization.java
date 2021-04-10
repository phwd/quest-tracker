package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class Localization {
    @DoNotStrip
    public static native void nativeInitialize();

    @DoNotStrip
    @Nullable
    public static String getLocalizedString(String str, String str2, String[] strArr) {
        throw new NullPointerException("getLocalizedString");
    }

    static {
        AnonymousClass1Nh.A00();
    }
}
