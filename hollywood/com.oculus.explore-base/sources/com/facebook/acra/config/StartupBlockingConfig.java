package com.facebook.acra.config;

public final class StartupBlockingConfig {
    public final long maxTimeSpentBlockedOnUploadMs;
    public final int minNumQueuedReportsToBlockStartup;
    public final int notificationText;
    public final int notificationTitle;
    public final boolean notifyWhileBlockingStartup;
}
