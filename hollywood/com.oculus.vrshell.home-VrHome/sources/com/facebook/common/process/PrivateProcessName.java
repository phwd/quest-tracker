package com.facebook.common.process;

import javax.annotation.Nullable;

public class PrivateProcessName {
    public static final PrivateProcessName DEFAULT_PROCESS = new PrivateProcessName("");
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
        } else if ("".equals(name)) {
            return DEFAULT_PROCESS;
        } else {
            return new PrivateProcessName(name);
        }
    }

    public boolean equals(@Nullable Object o) {
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
