package com.oculus.downloader.progress.model;

import java.util.Map;
import javax.annotation.Nullable;

public class DownloadProgressUnit {
    @Nullable
    public DownloadProgressItem mApkItem;
    @Nullable
    public Map<Long, DownloadProgressItem> mAssetItems;
    @Nullable
    public DownloadProgressItem mObbItem;
    public final String mPackageName;

    public DownloadProgressUnit(String str) {
        this.mPackageName = str;
    }
}
