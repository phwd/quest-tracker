package com.facebook.wifiscan;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class WifiScanOperationException extends Exception {
    public Type type;

    public enum Type {
        NOT_SUPPORTED,
        USER_DISABLED,
        PERMISSION_DENIED,
        TIMEOUT,
        UNKNOWN_ERROR
    }

    public WifiScanOperationException(Type type2) {
        super("WiFi scan error: " + type2);
        this.type = (Type) Preconditions.checkNotNull(type2);
    }
}
