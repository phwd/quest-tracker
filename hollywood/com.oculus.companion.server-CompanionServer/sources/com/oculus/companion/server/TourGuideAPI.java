package com.oculus.companion.server;

public class TourGuideAPI {
    public native boolean initializeRuntimeIPCApi(Object obj);

    public native boolean isGuardianSetupActive();

    public native boolean resetHeadsetView();

    public TourGuideAPI(Object obj) {
        initializeRuntimeIPCApi(obj);
    }

    /* access modifiers changed from: protected */
    public boolean resetHeadsetViewCall() {
        return resetHeadsetView();
    }

    /* access modifiers changed from: protected */
    public boolean isGuardianSetupActiveCall() {
        return isGuardianSetupActive();
    }

    static {
        System.loadLibrary("tourguideapi");
    }
}
