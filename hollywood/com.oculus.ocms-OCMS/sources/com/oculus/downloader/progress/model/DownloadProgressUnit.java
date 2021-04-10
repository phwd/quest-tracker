package com.oculus.downloader.progress.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class DownloadProgressUnit {
    @Nullable
    private DownloadProgressItem mApkItem;
    @Nullable
    private Map<Long, DownloadProgressItem> mAssetItems;
    @Nullable
    private DownloadProgressItem mObbItem;
    private final String mPackageName;

    public DownloadProgressUnit(String str) {
        this.mPackageName = str;
    }

    public synchronized void setApkItem(@Nullable DownloadProgressItem downloadProgressItem) {
        if (downloadProgressItem == null) {
            this.mApkItem = null;
            return;
        }
        this.mApkItem = new DownloadProgressItem(downloadProgressItem.getPackageName(), downloadProgressItem.getUpdateId());
        this.mApkItem.setState(downloadProgressItem.getDownloadId(), downloadProgressItem.getStatus(), downloadProgressItem.getReason(), downloadProgressItem.getTotalBytes(), downloadProgressItem.getCurrentBytes());
    }

    public synchronized void setObbItem(@Nullable DownloadProgressItem downloadProgressItem) {
        if (downloadProgressItem == null) {
            this.mObbItem = null;
            return;
        }
        this.mObbItem = new DownloadProgressItem(downloadProgressItem.getPackageName(), downloadProgressItem.getUpdateId());
        this.mObbItem.setState(downloadProgressItem.getDownloadId(), downloadProgressItem.getStatus(), downloadProgressItem.getReason(), downloadProgressItem.getTotalBytes(), downloadProgressItem.getCurrentBytes());
    }

    public synchronized void setAssetItems(@Nullable Map<Long, DownloadProgressItem> map) {
        if (map == null) {
            this.mAssetItems = null;
        } else {
            this.mAssetItems = new HashMap(map);
        }
    }

    @Nullable
    public synchronized DownloadProgressItem getApkItem() {
        if (this.mApkItem == null) {
            return null;
        }
        DownloadProgressItem downloadProgressItem = new DownloadProgressItem(this.mApkItem.getPackageName(), this.mApkItem.getUpdateId());
        downloadProgressItem.setState(this.mApkItem.getDownloadId(), this.mApkItem.getStatus(), this.mApkItem.getReason(), this.mApkItem.getTotalBytes(), this.mApkItem.getCurrentBytes());
        return downloadProgressItem;
    }

    @Nullable
    public synchronized DownloadProgressItem getObbItem() {
        if (this.mObbItem == null) {
            return null;
        }
        DownloadProgressItem downloadProgressItem = new DownloadProgressItem(this.mObbItem.getPackageName(), this.mObbItem.getUpdateId());
        downloadProgressItem.setState(this.mObbItem.getDownloadId(), this.mObbItem.getStatus(), this.mObbItem.getReason(), this.mObbItem.getTotalBytes(), this.mObbItem.getCurrentBytes());
        return downloadProgressItem;
    }

    @Nullable
    public synchronized Map<Long, DownloadProgressItem> getAssetItems() {
        if (this.mAssetItems == null) {
            return null;
        }
        return new HashMap(this.mAssetItems);
    }

    public synchronized long getCurrentBytes() {
        long j;
        j = 0;
        if (this.mApkItem != null) {
            j = 0 + this.mApkItem.getCurrentBytes();
        }
        if (this.mObbItem != null) {
            j += this.mObbItem.getCurrentBytes();
        }
        if (this.mAssetItems != null) {
            for (DownloadProgressItem downloadProgressItem : this.mAssetItems.values()) {
                j += downloadProgressItem.getCurrentBytes();
            }
        }
        return j;
    }

    public synchronized long getTotalBytes() {
        long j;
        j = 0;
        if (this.mApkItem != null) {
            j = 0 + this.mApkItem.getTotalBytes();
        }
        if (this.mObbItem != null) {
            j += this.mObbItem.getTotalBytes();
        }
        if (this.mAssetItems != null) {
            for (DownloadProgressItem downloadProgressItem : this.mAssetItems.values()) {
                j += downloadProgressItem.getTotalBytes();
            }
        }
        return j;
    }

    public synchronized String getPackageName() {
        return this.mPackageName;
    }

    public synchronized int getStatus() {
        int i;
        i = 16;
        if (this.mApkItem != null && this.mApkItem.getStatus() < 16) {
            i = this.mApkItem.getStatus();
        }
        if (this.mObbItem != null && this.mObbItem.getStatus() < i) {
            i = this.mObbItem.getStatus();
        }
        if (this.mAssetItems != null) {
            for (DownloadProgressItem downloadProgressItem : this.mAssetItems.values()) {
                if (downloadProgressItem.getStatus() < i) {
                    i = downloadProgressItem.getStatus();
                }
            }
        }
        return i;
    }
}
