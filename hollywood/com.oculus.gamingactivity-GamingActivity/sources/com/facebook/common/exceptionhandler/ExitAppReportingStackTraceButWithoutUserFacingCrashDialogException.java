package com.facebook.common.exceptionhandler;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ExitAppReportingStackTraceButWithoutUserFacingCrashDialogException extends RuntimeException {
    public ExitAppReportingStackTraceButWithoutUserFacingCrashDialogException(String message) {
        super(message);
    }
}
