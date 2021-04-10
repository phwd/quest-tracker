package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class RestrictiveLoggingStatus {
    private final String mStatus;

    public RestrictiveLoggingStatus(String str) {
        this.mStatus = str;
    }

    public String status() {
        return this.mStatus;
    }
}
