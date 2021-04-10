package com.oculus.horizon.social.model;

import com.oculus.horizoncontent.social.SocialDestinationDeeplinkabilityType;

public enum IdentityInSearchPrivacyState {
    ENABLED(SocialDestinationDeeplinkabilityType.ENABLED),
    DISABLED(SocialDestinationDeeplinkabilityType.DISABLED),
    IRRELEVANT("IRRELEVANT");
    
    public final String graphQLName;

    /* access modifiers changed from: public */
    IdentityInSearchPrivacyState(String str) {
        this.graphQLName = str;
    }
}
