package com.oculus.deviceconfigclient;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigCallback {

    public enum ParamChangedResult {
        UpdateNow,
        UpdateAtNextStart
    }
}
