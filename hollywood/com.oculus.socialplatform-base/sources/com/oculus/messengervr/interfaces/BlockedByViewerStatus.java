package com.oculus.messengervr.interfaces;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum BlockedByViewerStatus {
    UNBLOCKED,
    MESSAGE_BLOCKED,
    FULLY_BLOCKED
}
