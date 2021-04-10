package com.facebook.common.process;

import com.oculus.common.build.BuildConfig;

public class PrivateProcessName {
    public static final PrivateProcessName DEFAULT_PROCESS = new PrivateProcessName(BuildConfig.PROVIDER_SUFFIX);
    private final String mName;

    private PrivateProcessName(String name) {
        if (name == null || name.contains(":")) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.mName = name;
    }

    public static PrivateProcessName createFromShortName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Invalid name");
        } else if (BuildConfig.PROVIDER_SUFFIX.equals(name)) {
            return DEFAULT_PROCESS;
        } else {
            return new PrivateProcessName(name);
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mName.equals(((PrivateProcessName) o).mName);
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public String toString() {
        return this.mName;
    }
}
