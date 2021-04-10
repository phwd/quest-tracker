package com.facebook.acra.config;

import android.content.Context;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.sender.FlexibleReportSender;

public interface AcraReportingConfig {
    String crashReportUrl();

    IANRDetector createANRDetector(int i, ANRDetectorConfig aNRDetectorConfig, int i2);

    FlexibleReportSender createReportSender();

    Context getApplicationContext();

    String getSessionId();

    String getUserAgent();

    boolean isInternalBuild();

    boolean isZeroCrashlogBlocked();

    String[] logcatArguments(boolean z);

    boolean shouldStartANRDetector();
}
