package com.facebook.acra.anr.processmonitor;

public class DefaultProcessErrorStateListener implements ProcessErrorStateListener {
    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onCheckFailed() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onCheckPerformed() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onErrorCleared() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public boolean onErrorDetectOnOtherProcess(String str, String str2, String str3) {
        return true;
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onErrorDetected(String str, String str2) {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onMaxChecksReachedAfterError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onMaxChecksReachedBeforeError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onStart() {
    }
}
