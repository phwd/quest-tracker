package com.oculus.deviceconfigclient;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class NativeDeviceConfigCallback extends DeviceConfigCallback {
    protected long mNativePtr;

    private native boolean nativeEnableAutoPrefetch(long j);

    private native void nativeOnFailure(long j, String str);

    private native void nativeOnPrefetched(long j, String[] strArr);

    private native void nativeOnSuccess(long j);

    private native void nativeOnUpdate(long j, String str);

    private native boolean nativeSupportUpdates(long j);

    static {
        System.loadLibrary("deviceconfigclient-jni");
    }

    public NativeDeviceConfigCallback(long j) {
        this.mNativePtr = j;
    }

    public void destroy() {
        this.mNativePtr = 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
    public void onSuccess() {
        nativeOnSuccess(this.mNativePtr);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
    public void onFailure(String str) {
        nativeOnFailure(this.mNativePtr, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
    public boolean enableAutoPrefetch() {
        return nativeEnableAutoPrefetch(this.mNativePtr);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
    public boolean supportUpdates() {
        return nativeSupportUpdates(this.mNativePtr);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
    public void onUpdate(String str) {
        nativeOnUpdate(this.mNativePtr, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
    public void onPrefetched(String[] strArr) {
        nativeOnPrefetched(this.mNativePtr, strArr);
    }
}
