package com.oculus.logging.utils;

public interface Event {
    Event addExtra(String str, int i);

    Event addExtra(String str, String str2);

    Event addExtra(String str, boolean z);

    void logAndRelease();

    void setXplatTagForInternalUse();
}
