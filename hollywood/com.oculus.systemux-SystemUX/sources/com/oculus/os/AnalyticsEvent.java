package com.oculus.os;

import android.os.PersistableBundle;

public class AnalyticsEvent {
    public static final String IS_ARRAY_OF_BUNDLES = "utl_array_of_bundles";

    public AnalyticsEvent(String str) {
        throw new RuntimeException("Stub!");
    }

    public AnalyticsEvent(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public AnalyticsEvent(String str, PersistableBundle persistableBundle) {
        throw new RuntimeException("Stub!");
    }

    public AnalyticsEvent(String str, String str2, PersistableBundle persistableBundle, PersistableBundle persistableBundle2) {
        throw new RuntimeException("Stub!");
    }

    public String getModuleName() {
        throw new RuntimeException("Stub!");
    }

    public String getName() {
        throw new RuntimeException("Stub!");
    }

    public PersistableBundle getExtras() {
        throw new RuntimeException("Stub!");
    }

    public PersistableBundle getInternalUseParams() {
        throw new RuntimeException("Stub!");
    }

    public <T> AnalyticsEvent setExtra(String str, T t) {
        throw new RuntimeException("Stub!");
    }

    public <T> AnalyticsEvent setInternalUseParam(String str, T t) {
        throw new RuntimeException("Stub!");
    }

    public void setTime(long j) {
        throw new RuntimeException("Stub!");
    }

    public void setUslTag() {
        throw new RuntimeException("Stub!");
    }

    public void setReactNativeTag() {
        throw new RuntimeException("Stub!");
    }

    public void setNtTag() {
        throw new RuntimeException("Stub!");
    }

    public void setXplatTag() {
        throw new RuntimeException("Stub!");
    }

    public void setHasDownloadedSamplingConfigTag() {
        throw new RuntimeException("Stub!");
    }

    public void setIsEventInSamplingConfigTag() {
        throw new RuntimeException("Stub!");
    }
}
