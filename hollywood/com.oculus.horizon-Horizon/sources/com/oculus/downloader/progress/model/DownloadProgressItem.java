package com.oculus.downloader.progress.model;

import com.google.common.base.MoreObjects;
import com.oculus.downloader.util.OculusDownloadManagerUtils;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DownloadProgressItem {
    public final AtomicLong mCurrentBytes = new AtomicLong();
    public final AtomicLong mDownloadId = new AtomicLong();
    public final String mPackageName;
    public final AtomicInteger mReason = new AtomicInteger();
    public final AtomicInteger mStatus = new AtomicInteger();
    public final AtomicLong mTotalBytes = new AtomicLong();
    public final long mUpdateId;

    public final synchronized int A00() {
        return this.mReason.get();
    }

    public final synchronized int A01() {
        return this.mStatus.get();
    }

    public final synchronized long A02() {
        return this.mCurrentBytes.get();
    }

    public final synchronized long A03() {
        return this.mDownloadId.get();
    }

    public final synchronized long A04() {
        return this.mTotalBytes.get();
    }

    public final synchronized void A05(long j, int i, int i2, long j2, long j3) {
        this.mDownloadId.set(j);
        this.mStatus.set(i);
        this.mReason.set(i2);
        this.mTotalBytes.set(j2);
        this.mCurrentBytes.set(j3);
    }

    public final String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(DownloadProgressItem.class);
        stringHelper.add("id", this.mDownloadId.get());
        stringHelper.add("status", OculusDownloadManagerUtils.A01(this.mStatus.get()));
        stringHelper.add("reason", OculusDownloadManagerUtils.A00(this.mReason.get()));
        stringHelper.add("currentBytes", this.mCurrentBytes.get());
        stringHelper.add("totalBytes", this.mTotalBytes.get());
        stringHelper.add("packageName", this.mPackageName);
        stringHelper.add("update id", this.mUpdateId);
        return stringHelper.toString();
    }

    public DownloadProgressItem(String str, long j) {
        this.mPackageName = str;
        this.mUpdateId = j;
    }
}
