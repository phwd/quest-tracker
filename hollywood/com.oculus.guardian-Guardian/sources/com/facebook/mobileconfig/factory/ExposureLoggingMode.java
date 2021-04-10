package com.facebook.mobileconfig.factory;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ExposureLoggingMode {
    public static final int CAPTURE_STACK_TRACE_BIT = 128;
    public static final int DISABLED = 0;
    public static final int HAS_LOGGING_ID_BIT = 1;
}
