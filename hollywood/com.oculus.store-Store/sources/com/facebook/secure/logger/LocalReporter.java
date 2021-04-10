package com.facebook.secure.logger;

import android.util.Log;
import javax.annotation.Nullable;

public class LocalReporter implements Reporter {
    static final String DEFAULT_CATEGORY = "Security-LocalReporter";

    @Override // com.facebook.secure.logger.Reporter
    public void report(String message) {
        Log.e(DEFAULT_CATEGORY, message);
    }

    @Override // com.facebook.secure.logger.Reporter
    public void report(String category, String message, @Nullable Throwable cause) {
        StringBuilder sb = new StringBuilder();
        sb.append("category=");
        sb.append(category);
        sb.append(", message=");
        sb.append(message);
        if (cause != null) {
            sb.append(", cause=");
            sb.append(cause.toString());
        }
        report(sb.toString());
    }
}
