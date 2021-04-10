package com.oculus.platform.callback;

import com.oculus.platform.OVR;

public final class OVRErrorOrReleaseCallback {
    public final long mClientErrorCallbackPointer;
    public final long mClientReleaseCallbackPointer;

    public void onClientError(int i, String str) {
        OVR.nativeHttpError(i, str, this.mClientErrorCallbackPointer);
    }

    public void onClientRelease() {
        OVR.nativeHttpClientRelease(this.mClientReleaseCallbackPointer);
    }

    public OVRErrorOrReleaseCallback(long j, long j2) {
        this.mClientReleaseCallbackPointer = j;
        this.mClientErrorCallbackPointer = j2;
    }
}
