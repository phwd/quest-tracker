package com.oculus.horizon.social.model;

public enum PrivacyConcept {
    PROFILE("REAL_WORLD_IDENTITY", true),
    ACTIVITY("CURRENT_ACTIVITY"),
    FRIENDS("FRIENDS");
    
    public final String graphQLName;
    public final boolean usesIdentityInSearch;

    public boolean isIdentityInSearchSelectable(PrivacyState privacyState) {
        if (!this.usesIdentityInSearch) {
            return false;
        }
        if (privacyState == PrivacyState.ANYONE || privacyState == PrivacyState.FRIENDS) {
            return true;
        }
        return false;
    }

    public boolean shouldAlwaysSendIdentityInSearch() {
        return this.usesIdentityInSearch;
    }

    public String toString() {
        return this.graphQLName;
    }

    /* access modifiers changed from: public */
    PrivacyConcept(String str) {
        this(str, false);
    }

    /* access modifiers changed from: public */
    PrivacyConcept(String str, boolean z) {
        this.graphQLName = str;
        this.usesIdentityInSearch = z;
    }
}
