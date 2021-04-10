package com.facebook.common.process;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PrivateProcessName {
    public static final PrivateProcessName DEFAULT_PROCESS = new PrivateProcessName("");
    public static final String DEFAULT_SERIALIZABLE_NAME = "___DEFAULT___";
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

    public static PrivateProcessName createFromSerializableShortName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Invalid name");
        } else if (DEFAULT_SERIALIZABLE_NAME.equals(name)) {
            return DEFAULT_PROCESS;
        } else {
            return new PrivateProcessName(name);
        }
    }

    public String getName() {
        return this.mName;
    }

    public boolean isDefaultProcess() {
        return DEFAULT_PROCESS.equals(this);
    }

    public String getSerializableName() {
        if ("".equals(this.mName)) {
            return DEFAULT_SERIALIZABLE_NAME;
        }
        return this.mName;
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
