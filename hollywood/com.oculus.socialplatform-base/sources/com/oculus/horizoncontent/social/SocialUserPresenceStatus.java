package com.oculus.horizoncontent.social;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum SocialUserPresenceStatus {
    ONLINE("ONLINE"),
    OFFLINE("OFFLINE");
    
    public static final Map<String, SocialUserPresenceStatus> ENUM_MAP;
    public String mStatus;

    /* access modifiers changed from: public */
    static {
        HashMap hashMap = new HashMap();
        SocialUserPresenceStatus[] values = values();
        for (SocialUserPresenceStatus socialUserPresenceStatus : values) {
            hashMap.put(socialUserPresenceStatus.getStatus(), socialUserPresenceStatus);
        }
        ENUM_MAP = Collections.unmodifiableMap(hashMap);
    }

    public static SocialUserPresenceStatus get(String str) {
        return ENUM_MAP.get(str);
    }

    public String getStatus() {
        return this.mStatus;
    }

    public String toString() {
        return this.mStatus;
    }

    /* access modifiers changed from: public */
    SocialUserPresenceStatus(String str) {
        this.mStatus = str;
    }
}
