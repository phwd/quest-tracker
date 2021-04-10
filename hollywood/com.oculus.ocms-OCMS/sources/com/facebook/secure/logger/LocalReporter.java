package com.facebook.secure.logger;

import android.util.Log;
import javax.annotation.Nullable;

public class LocalReporter implements Reporter {
    static final String DEFAULT_CATEGORY = "Security-LocalReporter";

    @Override // com.facebook.secure.logger.Reporter
    public void report(String str) {
        Log.e(DEFAULT_CATEGORY, str);
    }

    @Override // com.facebook.secure.logger.Reporter
    public void report(String str, String str2, @Nullable Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append("category=");
        sb.append(str);
        sb.append(", message=");
        sb.append(str2);
        if (th != null) {
            sb.append(", cause=");
            sb.append(th.toString());
        }
        report(sb.toString());
    }
}
