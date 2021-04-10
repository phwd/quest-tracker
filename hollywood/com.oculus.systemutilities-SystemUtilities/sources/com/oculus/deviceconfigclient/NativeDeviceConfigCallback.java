package com.oculus.deviceconfigclient;

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

    public NativeDeviceConfigCallback(long nativePtr) {
        this.mNativePtr = nativePtr;
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
    public void onFailure(String error) {
        nativeOnFailure(this.mNativePtr, error);
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
    public void onUpdate(String paramName) {
        nativeOnUpdate(this.mNativePtr, paramName);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
    public void onPrefetched(String[] paramNames) {
        nativeOnPrefetched(this.mNativePtr, paramNames);
    }
}
