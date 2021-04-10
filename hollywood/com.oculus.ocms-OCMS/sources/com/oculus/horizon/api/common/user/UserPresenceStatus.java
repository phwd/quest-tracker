package com.oculus.horizon.api.common.user;

public enum UserPresenceStatus {
    ONLINE("ONLINE"),
    OFFLINE("OFFLINE");
    
    public final String graphQLName;

    private UserPresenceStatus(String str) {
        this.graphQLName = str;
    }
}
