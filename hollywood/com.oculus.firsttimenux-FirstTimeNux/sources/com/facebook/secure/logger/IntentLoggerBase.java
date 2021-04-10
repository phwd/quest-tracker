package com.facebook.secure.logger;

import android.content.Intent;
import javax.annotation.Nullable;

public abstract class IntentLoggerBase implements IntentLogger {
    @Override // com.facebook.secure.logger.IntentLogger
    public void logIntent(String endpointName, @Nullable Intent intent) {
        logIntent(endpointName, null, null, intent);
    }
}
