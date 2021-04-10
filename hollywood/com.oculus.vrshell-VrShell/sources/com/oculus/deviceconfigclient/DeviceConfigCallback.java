package com.oculus.deviceconfigclient;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigCallback {

    enum ParamChangedResult {
        UpdateNow,
        UpdateAtNextStart
    }

    /* access modifiers changed from: protected */
    public boolean enableAutoPrefetch() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onFailure(String str) {
    }

    /* access modifiers changed from: protected */
    public void onPrefetched(String[] strArr) {
    }

    /* access modifiers changed from: protected */
    public void onSuccess() {
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void onUpdate(String str) {
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public boolean supportUpdates() {
        return false;
    }

    /* access modifiers changed from: protected */
    public ParamChangedResult onParamChanged(String str, Object obj, Object obj2) {
        return ParamChangedResult.UpdateAtNextStart;
    }
}
