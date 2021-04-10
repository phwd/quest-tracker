package com.oculus.autoupdates.database;

public class AutoUpdateRecord {
    private int mAttempts;
    private final String mPackageName;
    private final int mVersionCode;

    public AutoUpdateRecord(String str, int i, int i2) {
        this.mPackageName = str;
        this.mVersionCode = i;
        this.mAttempts = i2;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int getAutoUpdateAttempts() {
        return this.mAttempts;
    }

    public void increaseAttemptCount() {
        this.mAttempts++;
    }
}
