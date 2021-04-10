package com.facebook.acra.config;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class StartupBlockingConfig {
    public static final long BLOCKING_UPLOAD_MAX_WAIT_MILLIS = 10000;
    public final long maxTimeSpentBlockedOnUploadMs;
    public final int minNumQueuedReportsToBlockStartup;
    public final int notificationText;
    public final int notificationTitle;
    public final boolean notifyWhileBlockingStartup;

    public StartupBlockingConfig(boolean notifyWhileBlockingStartup2, int minNumQueuedReportsToBlockStartup2, long maxTimeSpentBlockedOnUploadMs2, int notificationTitle2, int notificationText2) {
        this.notifyWhileBlockingStartup = notifyWhileBlockingStartup2;
        this.minNumQueuedReportsToBlockStartup = minNumQueuedReportsToBlockStartup2;
        this.maxTimeSpentBlockedOnUploadMs = maxTimeSpentBlockedOnUploadMs2;
        this.notificationTitle = notificationTitle2;
        this.notificationText = notificationText2;
    }
}
