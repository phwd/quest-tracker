package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class Proxies {
    public static boolean sConfigured;

    public static native void configureInternal(ProxyProvider proxyProvider);

    public static synchronized void configure(ProxyProvider proxyProvider) {
        synchronized (Proxies.class) {
            if (!sConfigured) {
                sConfigured = true;
                configureInternal(proxyProvider);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    static {
        AnonymousClass1Nh.A00();
    }
}
