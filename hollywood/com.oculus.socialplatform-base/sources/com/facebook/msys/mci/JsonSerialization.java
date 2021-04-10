package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class JsonSerialization {
    public static boolean sInitialized;

    @DoNotStrip
    public static native void nativeInitialize();

    static {
        AnonymousClass1Nh.A00();
    }
}
