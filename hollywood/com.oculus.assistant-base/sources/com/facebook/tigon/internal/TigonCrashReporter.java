package com.facebook.tigon.internal;

public class TigonCrashReporter {
    public void crashReport(String str, Throwable th) {
        throw new NullPointerException("softReport");
    }
}
