package com.facebook.secure.context;

import com.facebook.secure.logger.LocalReporter;
import com.facebook.secure.logger.Reporter;

public class DelegatingReporter implements Reporter {
    private Reporter mReporter;

    @Override // com.facebook.secure.logger.Reporter
    public void report(String str, String str2, Throwable th) {
        getReporter().report(str, str2, th);
    }

    public DelegatingReporter() {
        this(new LocalReporter());
    }

    public DelegatingReporter(Reporter reporter) {
        setReporter(reporter);
    }

    public synchronized Reporter getReporter() {
        return this.mReporter;
    }

    public synchronized void setReporter(Reporter reporter) {
        this.mReporter = reporter;
    }
}
