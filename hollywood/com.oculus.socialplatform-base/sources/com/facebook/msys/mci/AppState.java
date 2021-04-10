package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AppState {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder();

    @DoNotStrip
    private native void notifyAppEnterBackgroundNative(NotificationCenter notificationCenter);

    @DoNotStrip
    private native void notifyAppEnterForegroundNative(NotificationCenter notificationCenter);

    static {
        AnonymousClass1Nh.A00();
    }

    public AppState() {
        this.mNativeHolder = initNativeHolder();
    }

    @DoNotStrip
    public AppState(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
