package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary", "ImprovedNewApi"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class ExecutionIdle {
    public static volatile boolean sInitialized;

    @DoNotStrip
    public static native void nativeInitialize();

    @DoNotStrip
    public static native void nativeStartIdleExecutor();

    static {
        AnonymousClass1Nh.A00();
    }
}
