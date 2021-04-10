package com.facebook.common.process;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PrivateProcessName {
    public static final PrivateProcessName DEFAULT_PROCESS = new PrivateProcessName("");
    public static final String DEFAULT_SERIALIZABLE_NAME = "___DEFAULT___";
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
        } else if ("".equals(str)) {
            return DEFAULT_PROCESS;
        } else {
            return new PrivateProcessName(str);
        }
    }

    public static PrivateProcessName createFromSerializableShortName(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Invalid name");
        } else if (DEFAULT_SERIALIZABLE_NAME.equals(str)) {
            return DEFAULT_PROCESS;
        } else {
            return new PrivateProcessName(str);
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

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.mName.equals(((PrivateProcessName) obj).mName);
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public String toString() {
        return this.mName;
    }
}
