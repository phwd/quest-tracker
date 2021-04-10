package com.oculus.downloader.progress;

import com.facebook.inject.RequiresBinding;
import com.oculus.downloader.progress.model.DownloadProgressUnit;
import javax.annotation.Nullable;

@RequiresBinding
public interface DownloadProgressTracker {
    void addDownloadProgressChangeListener(DownloadProgressChangeListener downloadProgressChangeListener);

    @Nullable
    DownloadProgressUnit getDownloadProgress(String str);

    void removeDownloadProgress(String str);

    void removeDownloadProgressChangeListener(DownloadProgressChangeListener downloadProgressChangeListener);

    void startTrackingChangesIfNeeded();
}
