package com.oculus.horizon.cloudstorage2;

public abstract class Reportable implements Reporter {
    public Reporter mReporter;

    public Reportable(Reporter reporter) {
        this.mReporter = reporter;
    }
}
