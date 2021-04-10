package com.oculus.platform.callback;

import com.oculus.platform.OVR;

public final class OVRStringCallback {
    public final long mClientErrorCallbackPointer;
    public final long mClientSuccessCallbackPointer;

    public void onClientError(int i, String str) {
        OVR.nativeHttpError(i, str, this.mClientErrorCallbackPointer);
    }

    public void onSuccess(String str) {
        OVR.nativeHttpSuccess(str, this.mClientSuccessCallbackPointer);
    }

    public OVRStringCallback(long j, long j2) {
        this.mClientSuccessCallbackPointer = j;
        this.mClientErrorCallbackPointer = j2;
    }
}
