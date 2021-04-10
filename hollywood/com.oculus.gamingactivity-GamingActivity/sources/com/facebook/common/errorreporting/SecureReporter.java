package com.facebook.common.errorreporting;

import com.facebook.secure.logger.Reporter;
import javax.annotation.Nullable;

public class SecureReporter implements Reporter {
    public static final String SECURE_PENDING_INTENT_TAG = "SecurePendingIntent";
    private final String mDefaultCategory;
    private final FbErrorReporter mErrorReporter;

    public SecureReporter(FbErrorReporter errorReporter, String defaultCategory) {
        this.mErrorReporter = errorReporter;
        this.mDefaultCategory = defaultCategory;
    }

    @Override // com.facebook.secure.logger.Reporter
    public void report(String message) {
        this.mErrorReporter.softReport(this.mDefaultCategory, message);
    }

    @Override // com.facebook.secure.logger.Reporter
    public void report(String category, String message, @Nullable Throwable cause) {
        this.mErrorReporter.softReport(category, message, cause);
    }
}
