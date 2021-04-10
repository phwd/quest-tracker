package com.oculus.deviceconfigclient.shared;

import java.io.Serializable;

public class StorageParam implements Serializable {
    private static final long serialVersionUID = 1;
    private final boolean mIsSessionless;
    private final String mLoggingId;
    private final String mName;
    private final String mType;
    private final String mValue;

    public StorageParam(String name, String type, String value, String loggingId, boolean isSessionless) {
        this.mName = name;
        this.mType = type;
        this.mValue = value;
        this.mLoggingId = loggingId;
        this.mIsSessionless = isSessionless;
    }

    public String getName() {
        return this.mName;
    }

    public String getType() {
        return this.mType;
    }

    public String getValue() {
        return this.mValue;
    }

    public String getLoggingId() {
        return this.mLoggingId;
    }

    public boolean isSessionless() {
        return this.mIsSessionless;
    }
}
