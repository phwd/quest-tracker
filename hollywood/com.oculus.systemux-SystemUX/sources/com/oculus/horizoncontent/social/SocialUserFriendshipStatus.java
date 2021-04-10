package com.oculus.horizoncontent.social;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum SocialUserFriendshipStatus {
    ARE_FRIENDS("ARE_FRIENDS"),
    CANNOT_REQUEST("CANNOT_REQUEST"),
    CAN_REQUEST("CAN_REQUEST"),
    INCOMING_REQUEST("INCOMING_REQUEST"),
    OUTGOING_REQUEST("OUTGOING_REQUEST"),
    BLOCKED("BLOCKED"),
    UNKNOWN("UNKNOWN");
    
    private static final Map<String, SocialUserFriendshipStatus> ENUM_MAP;
    private String mStatus;

    static {
        HashMap hashMap = new HashMap();
        SocialUserFriendshipStatus[] values = values();
        for (SocialUserFriendshipStatus socialUserFriendshipStatus : values) {
            hashMap.put(socialUserFriendshipStatus.getStatus(), socialUserFriendshipStatus);
        }
        ENUM_MAP = Collections.unmodifiableMap(hashMap);
    }

    private SocialUserFriendshipStatus(String str) {
        this.mStatus = str;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public static SocialUserFriendshipStatus get(String str) {
        return ENUM_MAP.get(str);
    }

    public String toString() {
        return this.mStatus;
    }
}
