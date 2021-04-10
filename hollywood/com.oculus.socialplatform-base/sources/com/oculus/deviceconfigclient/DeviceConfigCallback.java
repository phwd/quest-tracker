package com.oculus.deviceconfigclient;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigCallback {

    public enum ParamChangedResult {
        UpdateNow,
        UpdateAtNextStart
    }

    public boolean enableAutoPrefetch() {
        return true;
    }

    public void onFailure(String str) {
    }

    public void onPrefetched(String[] strArr) {
    }

    public void onSuccess() {
    }

    @Deprecated
    public void onUpdate(String str) {
    }

    @Deprecated
    public boolean supportUpdates() {
        return false;
    }

    public ParamChangedResult onParamChanged(String str, Object obj, Object obj2) {
        return ParamChangedResult.UpdateAtNextStart;
    }
}
