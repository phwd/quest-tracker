package com.oculus.deviceconfigclient.shared;

import java.io.Serializable;

public class StorageParam implements Serializable {
    public static final long serialVersionUID = 1;
    public final boolean mIsSessionless;
    public final String mLoggingId;
    public final String mName;
    public final String mType;
    public final String mValue;

    public StorageParam(String str, String str2, String str3, String str4, boolean z) {
        this.mName = str;
        this.mType = str2;
        this.mValue = str3;
        this.mLoggingId = str4;
        this.mIsSessionless = z;
    }
}
