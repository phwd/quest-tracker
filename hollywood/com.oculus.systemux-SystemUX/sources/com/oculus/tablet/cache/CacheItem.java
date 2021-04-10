package com.oculus.tablet.cache;

public class CacheItem {
    private byte[] mBytes;
    private long mExpirationTimeSeconds = -1;

    public CacheItem(byte[] bArr) {
        this.mBytes = bArr;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() / 1000 > this.mExpirationTimeSeconds;
    }

    public byte[] getBytes() {
        return this.mBytes;
    }

    public long getExpirationTime() {
        return this.mExpirationTimeSeconds;
    }

    public void setExpirationTime(long j) {
        this.mExpirationTimeSeconds = j;
    }
}
