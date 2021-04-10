package com.oculus.downloader.progress;

import com.oculus.downloader.progress.model.DownloadProgressUnit;

public interface DownloadProgressChangeListener {
    void onChange(DownloadProgressUnit downloadProgressUnit);
}
