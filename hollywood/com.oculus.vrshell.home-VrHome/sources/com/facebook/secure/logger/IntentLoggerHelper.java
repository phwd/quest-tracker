package com.facebook.secure.logger;

import android.content.Intent;
import javax.annotation.Nullable;

public final class IntentLoggerHelper {
    public static final IntentLogger DO_NOTHING = new IntentLoggerBase() {
        /* class com.facebook.secure.logger.IntentLoggerHelper.AnonymousClass1 */

        @Override // com.facebook.secure.logger.IntentLogger
        public void logIntent(String endpointName, @Nullable String subEndpointName, @Nullable String status, @Nullable Intent intent) {
        }
    };
}
