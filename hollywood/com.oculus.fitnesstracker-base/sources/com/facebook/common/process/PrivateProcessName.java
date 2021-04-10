package com.facebook.common.process;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.common.build.BuildConfig;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class PrivateProcessName {
    public static final PrivateProcessName DEFAULT_PROCESS = new PrivateProcessName(BuildConfig.PROVIDER_SUFFIX);
    private final String mName;

    private PrivateProcessName(String str) {
        if (str == null || str.contains(":")) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.mName = str;
    }

    public static PrivateProcessName createFromShortName(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Invalid name");
        } else if (BuildConfig.PROVIDER_SUFFIX.equals(str)) {
            return DEFAULT_PROCESS;
        } else {
            return new PrivateProcessName(str);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.mName.equals(((PrivateProcessName) obj).mName);
    }

    public final int hashCode() {
        return this.mName.hashCode();
    }

    public final String toString() {
        return this.mName;
    }
}
