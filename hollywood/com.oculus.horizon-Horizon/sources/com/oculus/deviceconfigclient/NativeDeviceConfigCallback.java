package com.oculus.deviceconfigclient;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class NativeDeviceConfigCallback extends DeviceConfigCallback {
    public final long mNativePtr;

    private native boolean nativeEnableAutoPrefetch(long j);

    private native void nativeOnFailure(long j, String str);

    private native void nativeOnPrefetched(long j, String[] strArr);

    private native void nativeOnSuccess(long j);

    private native void nativeOnUpdate(long j, String str);

    private native boolean nativeSupportUpdates(long j);

    static {
        System.loadLibrary("deviceconfigclient-jni");
    }
}
