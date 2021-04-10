package com.facebook.mobileconfig.impl;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class MobileConfigExposure {
    public final String exposureType;
    public final String loggingId;
    public final String stackTrace;

    public MobileConfigExposure(String str, String str2, String str3) {
        this.loggingId = str;
        this.exposureType = str2;
        this.stackTrace = str3;
    }
}
