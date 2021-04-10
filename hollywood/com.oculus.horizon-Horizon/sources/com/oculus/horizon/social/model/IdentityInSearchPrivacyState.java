package com.oculus.horizon.social.model;

import com.oculus.library.utils.AppAutoUpdateConverter;

public enum IdentityInSearchPrivacyState {
    ENABLED(AppAutoUpdateConverter.ENABLED),
    DISABLED("DISABLED"),
    IRRELEVANT("IRRELEVANT");
    
    public final String graphQLName;

    /* access modifiers changed from: public */
    IdentityInSearchPrivacyState(String str) {
        this.graphQLName = str;
    }
}
