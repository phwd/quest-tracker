package com.oculus.security.basecomponent;

import com.facebook.debug.log.BLog;
import com.facebook.secure.logger.Reporter;
import javax.annotation.Nullable;

public class BLogDebugReporter implements Reporter {
    private String mTag;

    public BLogDebugReporter(String str) {
        this.mTag = str;
    }

    @Override // com.facebook.secure.logger.Reporter
    public void report(String str) {
        BLog.d(this.mTag, str);
    }

    @Override // com.facebook.secure.logger.Reporter
    public void report(String str, String str2, @Nullable Throwable th) {
        if (th != null) {
            BLog.d(this.mTag, th, "[%s] %s", str, str2);
            return;
        }
        BLog.d(this.mTag, "[%s] %s", str, str2);
    }
}
