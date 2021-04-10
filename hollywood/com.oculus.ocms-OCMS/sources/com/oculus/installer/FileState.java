package com.oculus.installer;

/* access modifiers changed from: package-private */
public enum FileState {
    MISSING,
    MISSING_REQUIRED,
    CHECKSUM_NULL,
    CHECKSUM_MISMATCH,
    ERROR,
    OK
}
