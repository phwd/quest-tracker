package com.oculus.horizon.api.fbconnect;

import com.google.common.base.Strings;
import javax.annotation.Nullable;

public enum FBFriendPolicy {
    AUTO_FRIEND("AUTO_FRIEND"),
    IGNORE_FRIEND("IGNORE_FRIEND");
    
    private final String value;

    private FBFriendPolicy(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }

    @Nullable
    public static FBFriendPolicy fromString(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return null;
        }
        FBFriendPolicy[] values = values();
        for (FBFriendPolicy fBFriendPolicy : values) {
            if (str.equals(fBFriendPolicy.value)) {
                return fBFriendPolicy;
            }
        }
        return null;
    }
}
