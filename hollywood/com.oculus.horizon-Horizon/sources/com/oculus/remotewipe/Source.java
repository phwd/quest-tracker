package com.oculus.remotewipe;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum Source {
    UNKNOWN,
    PUSH_NOTIFICATION,
    ENTERPRISE_PUSH_NOTIFICATION,
    COLD_START
}
