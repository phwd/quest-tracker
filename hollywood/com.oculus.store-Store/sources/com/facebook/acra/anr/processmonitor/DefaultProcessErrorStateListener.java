package com.facebook.acra.anr.processmonitor;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DefaultProcessErrorStateListener implements ProcessErrorStateListener {
    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onStart() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onErrorDetected(@Nullable String msg, @Nullable String tag) {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onErrorCleared() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onMaxChecksReachedBeforeError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onMaxChecksReachedAfterError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onCheckFailed() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onCheckPerformed() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public boolean onErrorDetectOnOtherProcess(String processName, @Nullable String msg, @Nullable String tag) {
        return true;
    }
}
