package com.oculus.horizon.social.model;

import com.oculus.panelapp.socialsettings.graphql.SocialSettingsGraphQL;

public enum PrivacyState {
    ANYONE(SocialSettingsGraphQL.ANYONE),
    FRIENDS("FRIENDS"),
    SPECIFIC_FRIENDS("SPECIFIC_FRIENDS", true),
    SELF(SocialSettingsGraphQL.SELF);
    
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
