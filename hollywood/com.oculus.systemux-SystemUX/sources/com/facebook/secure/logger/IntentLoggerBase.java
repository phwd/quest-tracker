package com.facebook.secure.logger;

import android.content.Intent;
import javax.annotation.Nullable;

public abstract class IntentLoggerBase implements IntentLogger {
    @Override // com.facebook.secure.logger.IntentLogger
    public void logIntent(String str, @Nullable Intent intent) {
        logIntent(str, null, null, intent);
    }
}
