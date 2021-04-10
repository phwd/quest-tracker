package com.facebook.acra.anr.processmonitor;

public interface ProcessMonitorFailureCause {
    public static final int CHECK_FAILED = 3;
    public static final int MAX_ATTEMPTS_AFTER_ERROR_REACHED = 2;
    public static final int MAX_ATTEMPTS_BEFORE_ERROR_REACHED = 1;
}
