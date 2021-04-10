package com.facebook.acra.anr.processmonitor;

public interface ProcessErrorStateListener {
    void onCheckFailed();

    void onErrorCleared();

    boolean onErrorDetectOnOtherProcess(String str, String str2, String str3);

    void onErrorDetected(String str, String str2);

    void onMaxChecksReachedAfterError();

    void onMaxChecksReachedBeforeError();

    void onStart();
}
