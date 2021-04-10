package com.facebook.acra.config;

public final class StartupBlockingConfig {
    public static final long BLOCKING_UPLOAD_MAX_WAIT_MILLIS = 10000;
    public final long maxTimeSpentBlockedOnUploadMs;
    public final int minNumQueuedReportsToBlockStartup;
    public final int notificationText;
    public final int notificationTitle;
    public final boolean notifyWhileBlockingStartup;

    public StartupBlockingConfig(boolean z, int i, long j, int i2, int i3) {
        this.notifyWhileBlockingStartup = z;
        this.minNumQueuedReportsToBlockStartup = i;
        this.maxTimeSpentBlockedOnUploadMs = j;
        this.notificationTitle = i2;
        this.notificationText = i3;
    }
}
