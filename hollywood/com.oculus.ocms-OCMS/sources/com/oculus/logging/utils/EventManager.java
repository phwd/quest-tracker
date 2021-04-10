package com.oculus.logging.utils;

import javax.annotation.Nullable;

public interface EventManager {
    Event createEvent(String str);

    Event createEvent(String str, String str2);

    Event createEvent(String str, @Nullable String str2, boolean z);

    FunnelData createFunnelData();

    void endFunnel(String str);

    void endFunnel(String str, Long l);

    void registerFunnel(String str, int i);

    void reportFunnelAction(String str, String str2, Long l, @Nullable String str3, FunnelData funnelData);

    void reportFunnelAction(String str, String str2, @Nullable String str3, FunnelData funnelData);

    void startFunnel(String str);

    void startFunnel(String str, Long l);
}
