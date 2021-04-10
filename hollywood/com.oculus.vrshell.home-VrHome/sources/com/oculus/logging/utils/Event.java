package com.oculus.logging.utils;

public interface Event {
    Event addExtra(String str, double d);

    Event addExtra(String str, int i);

    Event addExtra(String str, long j);

    Event addExtra(String str, String str2);

    Event addExtra(String str, boolean z);

    Event appendStorageInformation();

    void logAndRelease();

    void setEventInDownloadedSamplingTag();

    void setEventInSessionlessSamplingConfigTag();

    void setHasDownloadedSamplingConfigTag();

    void setNtTagForInternalUse();

    void setReactNativeTagForInternalUse();

    void setTime(long j);

    void setUslTagForInternalUse();

    void setXplatTagForInternalUse();
}
