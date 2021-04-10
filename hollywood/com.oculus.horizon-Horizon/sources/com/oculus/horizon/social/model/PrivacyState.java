package com.oculus.horizon.social.model;

public enum PrivacyState {
    ANYONE("ANYONE"),
    FRIENDS("FRIENDS"),
    SPECIFIC_FRIENDS("SPECIFIC_FRIENDS", true),
    SELF("SELF");
    
    public final String graphQLName;
    public final boolean usesIncludedFriends;

    public String toString() {
        return this.graphQLName;
    }

    /* access modifiers changed from: public */
    PrivacyState(String str) {
        this(str, false);
    }

    /* access modifiers changed from: public */
    PrivacyState(String str, boolean z) {
        this.graphQLName = str;
        this.usesIncludedFriends = z;
    }
}
