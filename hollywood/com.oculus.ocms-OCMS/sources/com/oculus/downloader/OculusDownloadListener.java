package com.oculus.downloader;

import com.oculus.downloader.model.DownloadInfo;

public interface OculusDownloadListener {
    void onDownloadComplete(DownloadInfo downloadInfo);
}
