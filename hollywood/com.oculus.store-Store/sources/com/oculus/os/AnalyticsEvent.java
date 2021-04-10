package com.oculus.os;

import android.os.PersistableBundle;

public class AnalyticsEvent {
    public static final String IS_ARRAY_OF_BUNDLES = "utl_array_of_bundles";

    public AnalyticsEvent(String name) {
        throw new RuntimeException("Stub!");
    }

    public AnalyticsEvent(String moduleName, String name) {
        throw new RuntimeException("Stub!");
    }

    public AnalyticsEvent(String name, PersistableBundle extras) {
        throw new RuntimeException("Stub!");
    }

    public AnalyticsEvent(String moduleName, String name, PersistableBundle extras, PersistableBundle internalUseParams) {
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

    public <T> AnalyticsEvent setExtra(String key, T t) {
        throw new RuntimeException("Stub!");
    }

    public <T> AnalyticsEvent setInternalUseParam(String key, T t) {
        throw new RuntimeException("Stub!");
    }

    public void setTime(long time) {
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
