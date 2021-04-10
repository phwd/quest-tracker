package com.facebook.messengervr.msys;

import X.AnonymousClass0l0;
import android.annotation.SuppressLint;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@SuppressLint({"MissingNativeLoadLibrary"})
public final class MessengerVrTableToProcedureNameMapRegisterer {
    @DoNotStrip
    public native void registerMappings();

    static {
        AnonymousClass0l0.A06("messengervrTableToCqlProcRegistration-jni");
    }
}
