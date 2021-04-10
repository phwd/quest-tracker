package com.oculus.horizon.linkedaccounts.model;

import com.google.common.base.Strings;
import javax.annotation.Nullable;

public enum ServiceProvider {
    DROPBOX("DROPBOX"),
    FACEBOOK("FACEBOOK"),
    GOOGLE("GOOGLE"),
    INSTAGRAM("INSTAGRAM"),
    REMOTE_MEDIA("REMOTE_MEDIA"),
    UNKNOWN("");
    
    public final String mName;

    /* access modifiers changed from: public */
    ServiceProvider(String str) {
        this.mName = str;
    }

    public static ServiceProvider fromString(@Nullable String str) {
        if (!Strings.isNullOrEmpty(str)) {
            ServiceProvider[] values = values();
            for (ServiceProvider serviceProvider : values) {
                if (serviceProvider.mName.equalsIgnoreCase(str)) {
                    return serviceProvider;
                }
            }
        }
        return UNKNOWN;
    }

    public String toString() {
        return this.mName;
    }
}
