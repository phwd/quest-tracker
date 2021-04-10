package com.facebook.msys.util;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class Environment {
    @DoNotStrip
    public static native synchronized boolean setenvNative(String str, String str2, boolean z);

    static {
        AnonymousClass1Nh.A00();
    }
}
