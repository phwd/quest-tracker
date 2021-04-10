package com.oculus.downloader.progress.model;

import com.google.common.base.MoreObjects;
import com.oculus.downloader.util.OculusDownloadManagerUtils;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DownloadProgressItem {
    private final AtomicLong mCurrentBytes = new AtomicLong();
    private final AtomicLong mDownloadId = new AtomicLong();
    private final String mPackageName;
    private final AtomicInteger mReason = new AtomicInteger();
    private final AtomicInteger mStatus = new AtomicInteger();
    private final AtomicLong mTotalBytes = new AtomicLong();
    private final long mUpdateId;

    public DownloadProgressItem(String str, long j) {
        this.mPackageName = str;
        this.mUpdateId = j;
    }

    public synchronized void setState(long j, int i, int i2, long j2, long j3) {
        this.mDownloadId.set(j);
        this.mStatus.set(i);
        this.mReason.set(i2);
        this.mTotalBytes.set(j2);
        this.mCurrentBytes.set(j3);
    }

    public synchronized long getDownloadId() {
        return this.mDownloadId.get();
    }

    public synchronized int getStatus() {
        return this.mStatus.get();
    }

    public synchronized int getReason() {
        return this.mReason.get();
    }

    public synchronized long getTotalBytes() {
        return this.mTotalBytes.get();
    }

    public synchronized long getCurrentBytes() {
        return this.mCurrentBytes.get();
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public long getUpdateId() {
        return this.mUpdateId;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Class<?>) DownloadProgressItem.class).add("id", this.mDownloadId.get()).add("status", OculusDownloadManagerUtils.stringifyStatus(this.mStatus.get())).add("reason", OculusDownloadManagerUtils.stringifyReason(this.mReason.get())).add("currentBytes", this.mCurrentBytes.get()).add("totalBytes", this.mTotalBytes.get()).add("packageName", this.mPackageName).add("update id", this.mUpdateId).toString();
    }
}
