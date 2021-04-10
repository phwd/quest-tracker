package com.facebook.common.errorreporting;

import com.facebook.secure.logger.Reporter;
import javax.annotation.Nullable;

public class SecureReporter implements Reporter {
    public static final String SECURE_PENDING_INTENT_TAG = "SecurePendingIntent";
    private final String mDefaultCategory;
    private final FbErrorReporter mErrorReporter;

    public SecureReporter(FbErrorReporter fbErrorReporter, String str) {
        this.mErrorReporter = fbErrorReporter;
        this.mDefaultCategory = str;
    }

    @Override // com.facebook.secure.logger.Reporter
    public void report(String str) {
        this.mErrorReporter.softReport(this.mDefaultCategory, str);
    }

    @Override // com.facebook.secure.logger.Reporter
    public void report(String str, String str2, @Nullable Throwable th) {
        this.mErrorReporter.softReport(str, str2, th);
    }
}
