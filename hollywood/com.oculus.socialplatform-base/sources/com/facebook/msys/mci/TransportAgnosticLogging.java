package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class TransportAgnosticLogging {
    public static native void logTalEventInNative(PrivacyContext privacyContext, int i, int i2, Object[] objArr);

    static {
        AnonymousClass1Nh.A00();
    }
}
