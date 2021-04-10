package com.facebook.msys.mci.network.common;

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
public class NetworkUtils {
    @DoNotStrip
    @Nullable
    public static native String getSandboxDomain();

    @DoNotStrip
    public static native synchronized void setSandboxDomain(@Nullable String str);

    static {
        AnonymousClass1Nh.A00();
    }
}
