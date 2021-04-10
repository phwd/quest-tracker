package com.facebook.common.perftest.base;

import X.CQ;

public class PerfTestConfigBase {
    public static boolean A00;
    public static boolean A01;

    public static PerfTestConfigBase getInstance() {
        return CQ.A00;
    }

    public void setAllowMainTabActivityKillingOnBackPressHandler(boolean z) {
    }

    public void setAlwaysLogComponentsMemory(boolean z) {
    }

    public void setAlwaysLogComponentsPerf(boolean z) {
    }

    public void setAlwaysLogDraweePerf(boolean z) {
    }

    public void setAlwaysLogImagePipelinePerf(boolean z) {
    }

    public void setDisableAnalyticsLogging(boolean z) {
        A00 = z;
    }

    public void setDisableNewsFeedAutoRefresh(boolean z) {
    }

    public void setDisablePerfLogging(boolean z) {
        A01 = z;
    }

    public void setDisablePrefetchControllerMemoryCacheFastpath(boolean z) {
    }

    public void setFeedImagePreloaderDisabled(boolean z) {
    }

    public void setForceRefreshNewsFeedOnResume(boolean z) {
    }

    public void setPerfTestInfo(String str) {
    }

    public void setPlacePickerFlowsEnabled(boolean z) {
    }

    public void setPlacePickerForceMockedLocation(boolean z) {
    }

    public void setPlacePickerSuppressLocationSourceDialog(boolean z) {
    }

    public void setPlacePickerTimeoutMs(long j) {
    }

    public void setSychronousPerfLoggerEvents(boolean z) {
    }

    public void setUseApiRequestCache(boolean z) {
    }
}
