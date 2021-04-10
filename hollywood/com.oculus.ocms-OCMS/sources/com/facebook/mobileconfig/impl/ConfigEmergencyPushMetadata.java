package com.facebook.mobileconfig.impl;

import java.util.Set;

public class ConfigEmergencyPushMetadata {
    public final String configName;
    public boolean existInCache;
    public final boolean forceRefresh;
    public Set<Long> params;
    public final boolean requestingRestart;
    public final int restartTime;
    public final int version;

    public ConfigEmergencyPushMetadata(String str, int i, boolean z, int i2, boolean z2) {
        this.configName = str;
        this.version = i;
        this.restartTime = i2;
        this.requestingRestart = z;
        this.forceRefresh = z2;
    }
}
