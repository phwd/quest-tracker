package com.oculus.tablet.cache;

public class CacheItem {
    public byte[] mBytes;
    public long mExpirationTimeSeconds = -1;

    public byte[] getBytes() {
        return this.mBytes;
    }

    public long getExpirationTime() {
        return this.mExpirationTimeSeconds;
    }

    public CacheItem(byte[] bArr) {
        this.mBytes = bArr;
    }

    public boolean isExpired() {
        if (System.currentTimeMillis() / 1000 > this.mExpirationTimeSeconds) {
            return true;
        }
        return false;
    }

    public void setExpirationTime(long j) {
        this.mExpirationTimeSeconds = j;
    }
}
