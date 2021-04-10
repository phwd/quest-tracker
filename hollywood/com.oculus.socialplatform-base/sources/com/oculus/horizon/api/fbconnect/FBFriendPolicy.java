package com.oculus.horizon.api.fbconnect;

import com.google.common.base.Strings;
import javax.annotation.Nullable;

public enum FBFriendPolicy {
    AUTO_FRIEND("AUTO_FRIEND"),
    IGNORE_FRIEND("IGNORE_FRIEND");
    
    public final String value;

    public String toString() {
        return this.value;
    }

    /* access modifiers changed from: public */
    FBFriendPolicy(String str) {
        this.value = str;
    }

    @Nullable
    public static FBFriendPolicy fromString(String str) {
        if (!Strings.isNullOrEmpty(str)) {
            FBFriendPolicy[] values = values();
            for (FBFriendPolicy fBFriendPolicy : values) {
                if (str.equals(fBFriendPolicy.value)) {
                    return fBFriendPolicy;
                }
            }
        }
        return null;
    }
}
