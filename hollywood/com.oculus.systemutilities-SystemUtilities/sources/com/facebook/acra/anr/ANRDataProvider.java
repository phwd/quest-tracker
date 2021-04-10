package com.facebook.acra.anr;

public abstract class ANRDataProvider implements ANRReportProvider {
    public void provideStats() {
    }

    public void provideLooperProfileInfo() {
    }

    public void provideDexStatus() {
    }

    public void provideLooperMonitorInfo() {
    }

    public boolean shouldANRDetectorRun() {
        return false;
    }
}
