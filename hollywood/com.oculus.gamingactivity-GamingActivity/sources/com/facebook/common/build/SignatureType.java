package com.facebook.common.build;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum SignatureType {
    DEBUG,
    IN_HOUSE,
    PROD;

    public String getPermission() {
        return String.format("com.facebook.permission.%s.FB_APP_COMMUNICATION", name().toLowerCase());
    }

    public String getReceiverPermission() {
        if (equals(PROD)) {
            return "com.facebook.receiver.permission.ACCESS";
        }
        return String.format("com.facebook.receiver.permission.%s.ACCESS", name().toLowerCase());
    }
}
