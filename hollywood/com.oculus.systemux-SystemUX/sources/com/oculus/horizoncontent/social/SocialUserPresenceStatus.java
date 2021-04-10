package com.oculus.horizoncontent.social;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum SocialUserPresenceStatus {
    ONLINE("ONLINE"),
    OFFLINE("OFFLINE");
    
    private static final Map<String, SocialUserPresenceStatus> ENUM_MAP;
    private String mStatus;

    static {
        HashMap hashMap = new HashMap();
        SocialUserPresenceStatus[] values = values();
        for (SocialUserPresenceStatus socialUserPresenceStatus : values) {
            hashMap.put(socialUserPresenceStatus.getStatus(), socialUserPresenceStatus);
        }
        ENUM_MAP = Collections.unmodifiableMap(hashMap);
    }

    private SocialUserPresenceStatus(String str) {
        this.mStatus = str;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public static SocialUserPresenceStatus get(String str) {
        return ENUM_MAP.get(str);
    }

    public String toString() {
        return this.mStatus;
    }
}
