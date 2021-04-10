package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@SuppressLint({"MissingNativeLoadLibrary"})
public class Connectivity {
    @DoNotStrip
    public static native void nativeInitialize();

    @DoNotStrip
    public static synchronized int getMqttState() {
        synchronized (Connectivity.class) {
            throw new IllegalStateException("Connectivity is not initialized");
        }
    }

    @DoNotStrip
    public static synchronized int getNetworkState() {
        synchronized (Connectivity.class) {
            throw new IllegalStateException("Connectivity is not initialized");
        }
    }

    static {
        AnonymousClass1Nh.A00();
    }
}
