package com.oculus.nux.ota;

public class HighPriApp {
    private final String mAppId;
    private long mDownloadedBytes = 0;
    private boolean mIsInstalled = false;
    private long mTotalBytes = 0;

    public HighPriApp(String str) {
        this.mAppId = str;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public long getDownloadedBytes() {
        return this.mDownloadedBytes;
    }

    public void setDownloadedBytes(long j) {
        this.mDownloadedBytes = j;
    }

    public boolean getIsInstalled() {
        return this.mIsInstalled;
    }

    public void setIsInstalled(boolean z) {
        this.mIsInstalled = z;
    }

    public long getTotalBytes() {
        return this.mTotalBytes;
    }

    public void setTotalBytes(long j) {
        this.mTotalBytes = j;
    }
}
