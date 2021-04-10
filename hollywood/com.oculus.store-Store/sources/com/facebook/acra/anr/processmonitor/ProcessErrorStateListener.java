package com.facebook.acra.anr.processmonitor;

import javax.annotation.Nullable;

public interface ProcessErrorStateListener {
    void onCheckFailed();

    void onCheckPerformed();

    void onErrorCleared();

    boolean onErrorDetectOnOtherProcess(String str, @Nullable String str2, @Nullable String str3);

    void onErrorDetected(@Nullable String str, @Nullable String str2);

    void onMaxChecksReachedAfterError();

    void onMaxChecksReachedBeforeError();

    void onStart();
}
