package com.facebook.acra.sender;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ReportSenderException extends Exception {
    public ReportSenderException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
