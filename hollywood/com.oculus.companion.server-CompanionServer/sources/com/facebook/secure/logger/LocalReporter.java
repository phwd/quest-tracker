package com.facebook.secure.logger;

import android.util.Log;

public class LocalReporter implements Reporter {
    @Override // com.facebook.secure.logger.Reporter
    public void report(String str) {
        Log.e("Security-LocalReporter", str);
    }

    @Override // com.facebook.secure.logger.Reporter
    public void report(String str, String str2, Throwable th) {
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
