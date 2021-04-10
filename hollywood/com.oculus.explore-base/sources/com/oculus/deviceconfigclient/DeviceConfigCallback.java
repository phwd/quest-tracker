package com.oculus.deviceconfigclient;

public class DeviceConfigCallback {

    enum ParamChangedResult {
        UpdateNow,
        UpdateAtNextStart
    }

    /* access modifiers changed from: protected */
    public void onSuccess() {
    }

    /* access modifiers changed from: protected */
    public void onFailure(String error) {
    }

    /* access modifiers changed from: protected */
    public boolean enableAutoPrefetch() {
        return true;
    }

    /* access modifiers changed from: protected */
    public ParamChangedResult onParamChanged(String paramName, Object previousValue, Object newValue) {
        return ParamChangedResult.UpdateAtNextStart;
    }

    /* access modifiers changed from: protected */
    public void onPrefetched(String[] paramNamesPrefetched) {
    }
}
