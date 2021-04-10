package com.facebook.msys.mcs;

import X.AnonymousClass1NZ;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class Presence {
    @DoNotStrip
    public static native void setPresenceEnabledNative(SyncHandler syncHandler, boolean z);

    static {
        AnonymousClass1NZ.A00();
    }
}
