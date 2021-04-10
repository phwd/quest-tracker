package com.facebook;

public enum AccessTokenSource {
    NONE(false),
    FACEBOOK_APPLICATION_WEB(true),
    FACEBOOK_APPLICATION_NATIVE(true),
    FACEBOOK_APPLICATION_SERVICE(true),
    WEB_VIEW(false),
    TEST_USER(true),
    CLIENT_TOKEN(true);
    
    public final boolean canExtendToken;

    /* access modifiers changed from: public */
    AccessTokenSource(boolean z) {
        this.canExtendToken = z;
    }

    public boolean canExtendToken() {
        return this.canExtendToken;
    }
}
